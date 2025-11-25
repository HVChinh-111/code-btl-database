package com.codebtl.model;

import com.codebtl.util.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DoctorPerformanceDAO {
    
    /**
     * Get doctor performance report for a specific time period
     * @param startDate Start date of the period
     * @param endDate End date of the period
     * @return List of DoctorPerformance objects
     */
    public List<DoctorPerformance> getDoctorPerformanceReport(LocalDate startDate, LocalDate endDate) {
        List<DoctorPerformance> performances = new ArrayList<>();
        
        String sql = """
            SELECT 
                d.d_person_id AS doctor_id,
                p.name AS doctor_name,
                p.dob AS date_of_birth,
                COUNT(DISTINCT a.p_person_id) AS number_of_patients,
                COUNT(CASE 
                    WHEN a.status = 'COMPLETED' THEN a.app_id 
                    ELSE NULL 
                END) AS number_of_completed_examinations,
                COALESCE(SUM(pay.amount), 0) AS total_revenue
            FROM doctors d
            INNER JOIN persons p ON d.d_person_id = p.person_id
            LEFT JOIN time_slots ts ON d.d_person_id = ts.d_person_id
            LEFT JOIN appointments a ON ts.slot_id = a.slot_id 
                AND ts.start_time >= ? 
                AND ts.start_time <= ?
            LEFT JOIN encounters enc ON a.app_id = enc.app_id
            LEFT JOIN payments pay ON enc.encounter_id = pay.encounter_id
            GROUP BY d.d_person_id, p.name, p.dob
            ORDER BY total_revenue DESC, doctor_name ASC
            """;
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Set parameters: start date at 00:00:00 and end date at 23:59:59
            stmt.setTimestamp(1, Timestamp.valueOf(startDate.atStartOfDay()));
            stmt.setTimestamp(2, Timestamp.valueOf(endDate.atTime(23, 59, 59)));
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    DoctorPerformance performance = new DoctorPerformance();
                    performance.setDoctorId(rs.getInt("doctor_id"));
                    performance.setDoctorName(rs.getString("doctor_name"));
                    
                    Date dob = rs.getDate("date_of_birth");
                    performance.setDateOfBirth(dob != null ? dob.toLocalDate() : null);
                    
                    performance.setNumberOfPatients(rs.getInt("number_of_patients"));
                    performance.setNumberOfCompletedExaminations(rs.getInt("number_of_completed_examinations"));
                    
                    BigDecimal revenue = rs.getBigDecimal("total_revenue");
                    performance.setTotalRevenue(revenue != null ? revenue : BigDecimal.ZERO);
                    
                    performances.add(performance);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return performances;
    }
    
    /**
     * Calculate total revenue from all doctors in the report
     * @param performances List of doctor performances
     * @return Total revenue
     */
    public BigDecimal calculateTotalRevenue(List<DoctorPerformance> performances) {
        return performances.stream()
                .map(DoctorPerformance::getTotalRevenue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
