package com.codebtl.model;

import com.codebtl.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicineDAO {

    public MedicineDAO() {
    }

    public List<Medicine> getAllMedicines() {
        List<Medicine> medicines = new ArrayList<>();
        String sql = "SELECT medicine_id, name, strength, unit FROM medicines ORDER BY medicine_id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Medicine medicine = new Medicine(
                        rs.getString("medicine_id"),
                        rs.getString("name"),
                        rs.getString("strength"),
                        rs.getString("unit")
                );
                medicines.add(medicine);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch medicines", e);
        }
        return medicines;
    }

    public boolean insertMedicine(Medicine medicine) {
        String sql = "INSERT INTO medicines(medicine_id, name, strength, unit) VALUES(?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, medicine.getMedicineId());
            ps.setString(2, medicine.getName());
            ps.setString(3, medicine.getStrength());
            ps.setString(4, medicine.getUnit());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate entry")) {
                return false;
            }
            throw new RuntimeException("Failed to insert medicine", e);
        }
    }

    public boolean updateMedicine(Medicine medicine) {
        String sql = "UPDATE medicines SET name = ?, strength = ?, unit = ? WHERE medicine_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, medicine.getName());
            ps.setString(2, medicine.getStrength());
            ps.setString(3, medicine.getUnit());
            ps.setString(4, medicine.getMedicineId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update medicine", e);
        }
    }

    public boolean deleteMedicine(String medicineId) {
        String sql = "DELETE FROM medicines WHERE medicine_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, medicineId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete medicine", e);
        }
    }

    public List<Medicine> searchMedicines(String keyword) {
        List<Medicine> medicines = new ArrayList<>();
        String sql = "SELECT medicine_id, name, strength, unit FROM medicines " +
                     "WHERE medicine_id LIKE ? OR name LIKE ? OR strength LIKE ? OR unit LIKE ? " +
                     "ORDER BY medicine_id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            String searchPattern = "%" + keyword + "%";
            ps.setString(1, searchPattern);
            ps.setString(2, searchPattern);
            ps.setString(3, searchPattern);
            ps.setString(4, searchPattern);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Medicine medicine = new Medicine(
                        rs.getString("medicine_id"),
                        rs.getString("name"),
                        rs.getString("strength"),
                        rs.getString("unit")
                );
                medicines.add(medicine);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to search medicines", e);
        }
        return medicines;
    }
}
