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
        String sql = "SELECT medicine_id, name, strength, unit FROM medicines WHERE is_active = TRUE ORDER BY medicine_id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Medicine medicine = new Medicine(
                        rs.getInt("medicine_id"),
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
        String sql = "INSERT INTO medicines(name, strength, unit) VALUES(?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, medicine.getName());
            ps.setString(2, medicine.getStrength());
            ps.setString(3, medicine.getUnit());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
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
            ps.setInt(4, medicine.getMedicineId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update medicine", e);
        }
    }

    public boolean deleteMedicine(int medicineId) {
        // Soft delete: chỉ đánh dấu is_active = false thay vì xóa hẳn
        String sql = "UPDATE medicines SET is_active = FALSE WHERE medicine_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, medicineId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete medicine", e);
        }
    }

    /**
     * Tìm kiếm medicine theo các thuộc tính (không bao gồm ID)
     * Logic: 
     * - Bỏ qua các trường tìm kiếm rỗng (null hoặc blank)
     * - Nếu nhiều trường được điền, tất cả phải khớp (AND logic)
     * - Mỗi trường khớp khi chuỗi nhập là substring của giá trị trong DB
     * 
     * @param name Tên thuốc (có thể null)
     * @param strength Liều lượng (có thể null)
     * @param unit Đơn vị (có thể null)
     * @return Danh sách medicine khớp với tất cả điều kiện đã điền
     */
    public List<Medicine> searchMedicines(String name, String strength, String unit) {
        List<Medicine> medicines = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT medicine_id, name, strength, unit FROM medicines WHERE is_active = TRUE");
        List<String> params = new ArrayList<>();
        
        // Chỉ thêm điều kiện cho các trường không rỗng
        if (name != null && !name.isBlank()) {
            sql.append(" AND name LIKE ?");
            params.add("%" + name.trim() + "%");
        }
        if (strength != null && !strength.isBlank()) {
            sql.append(" AND strength LIKE ?");
            params.add("%" + strength.trim() + "%");
        }
        if (unit != null && !unit.isBlank()) {
            sql.append(" AND unit LIKE ?");
            params.add("%" + unit.trim() + "%");
        }
        
        sql.append(" ORDER BY medicine_id");
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            
            // Set parameters
            for (int i = 0; i < params.size(); i++) {
                ps.setString(i + 1, params.get(i));
            }
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Medicine medicine = new Medicine(
                        rs.getInt("medicine_id"),
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
