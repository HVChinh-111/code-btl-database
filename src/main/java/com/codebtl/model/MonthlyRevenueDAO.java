package com.codebtl.model;

import com.codebtl.util.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MonthlyRevenueDAO {
    
    /**
     * Get monthly revenue report for all months in the database
     * Revenue is calculated from payments table only
     * 
     * @return List of MonthlyRevenue objects sorted by year DESC, month DESC
     */
    public List<MonthlyRevenue> getMonthlyRevenueReport() {
        List<MonthlyRevenue> revenues = new ArrayList<>();
        
        String sql = """
            SELECT 
                YEAR(pay_time) AS year,
                MONTH(pay_time) AS month,
                SUM(amount) AS total_revenue
            FROM payments
            WHERE pay_time IS NOT NULL
            GROUP BY YEAR(pay_time), MONTH(pay_time)
            HAVING total_revenue > 0
            ORDER BY year DESC, month DESC
            """;
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                MonthlyRevenue revenue = new MonthlyRevenue();
                revenue.setYear(rs.getInt("year"));
                revenue.setMonth(rs.getInt("month"));
                
                BigDecimal totalRevenue = rs.getBigDecimal("total_revenue");
                revenue.setTotalRevenue(totalRevenue != null ? totalRevenue : BigDecimal.ZERO);
                
                revenues.add(revenue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return revenues;
    }
    
    /**
     * Calculate grand total revenue from all months
     * @param revenues List of monthly revenues
     * @return Total revenue
     */
    public BigDecimal calculateGrandTotal(List<MonthlyRevenue> revenues) {
        return revenues.stream()
                .map(MonthlyRevenue::getTotalRevenue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
