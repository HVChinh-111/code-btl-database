package com.codebtl.model;

import com.codebtl.util.DBConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProcedureCatalogDAO {

    public ProcedureCatalogDAO() {
    }

    public List<ProcedureCatalog> getAllProcedureCatalogs() {
        List<ProcedureCatalog> procedures = new ArrayList<>();
        String sql = "SELECT procedure_id, name, type, description, default_price FROM procedure_catalogs WHERE is_active = TRUE ORDER BY procedure_id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ProcedureCatalog procedure = new ProcedureCatalog(
                        rs.getInt("procedure_id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getString("description"),
                        rs.getBigDecimal("default_price")
                );
                procedures.add(procedure);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch procedure catalogs", e);
        }
        return procedures;
    }

    public boolean insertProcedureCatalog(ProcedureCatalog procedure) {
        String sql = "INSERT INTO procedure_catalogs(name, type, description, default_price) VALUES(?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, procedure.getName());
            ps.setString(2, procedure.getType());
            ps.setString(3, procedure.getDescription());
            ps.setBigDecimal(4, procedure.getDefaultPrice());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to insert procedure catalog", e);
        }
    }

    public boolean updateProcedureCatalog(ProcedureCatalog procedure) {
        String sql = "UPDATE procedure_catalogs SET name = ?, type = ?, description = ?, default_price = ? WHERE procedure_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, procedure.getName());
            ps.setString(2, procedure.getType());
            ps.setString(3, procedure.getDescription());
            ps.setBigDecimal(4, procedure.getDefaultPrice());
            ps.setInt(5, procedure.getProcedureId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update procedure catalog", e);
        }
    }

    public boolean deleteProcedureCatalog(int procedureId) {
        // Soft delete: chỉ đánh dấu is_active = false thay vì xóa hẳn
        String sql = "UPDATE procedure_catalogs SET is_active = FALSE WHERE procedure_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, procedureId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete procedure catalog", e);
        }
    }

    /**
     * Tìm kiếm procedure catalog theo các thuộc tính (không bao gồm ID)
     * Logic:
     * - Bỏ qua các trường tìm kiếm rỗng (null hoặc blank)
     * - Nếu nhiều trường được điền, tất cả phải khớp (AND logic)
     * - Mỗi trường khớp khi chuỗi nhập là substring của giá trị trong DB
     * 
     * @param name Tên thủ thuật (có thể null)
     * @param type Loại thủ thuật (có thể null)
     * @param description Mô tả (có thể null)
     * @param defaultPrice Giá mặc định (có thể null)
     * @return Danh sách procedure catalog khớp với tất cả điều kiện đã điền
     */
    public List<ProcedureCatalog> searchProcedureCatalogs(String name, String type, String description, String defaultPrice) {
        List<ProcedureCatalog> procedures = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT procedure_id, name, type, description, default_price FROM procedure_catalogs WHERE is_active = TRUE");
        List<String> params = new ArrayList<>();
        
        // Chỉ thêm điều kiện cho các trường không rỗng
        if (name != null && !name.isBlank()) {
            sql.append(" AND name LIKE ?");
            params.add("%" + name.trim() + "%");
        }
        if (type != null && !type.isBlank()) {
            sql.append(" AND type LIKE ?");
            params.add("%" + type.trim() + "%");
        }
        if (description != null && !description.isBlank()) {
            sql.append(" AND description LIKE ?");
            params.add("%" + description.trim() + "%");
        }
        if (defaultPrice != null && !defaultPrice.isBlank()) {
            sql.append(" AND CAST(default_price AS CHAR) LIKE ?");
            params.add("%" + defaultPrice.trim() + "%");
        }
        
        sql.append(" ORDER BY procedure_id");
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            
            // Set parameters
            for (int i = 0; i < params.size(); i++) {
                ps.setString(i + 1, params.get(i));
            }
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProcedureCatalog procedure = new ProcedureCatalog(
                        rs.getInt("procedure_id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getString("description"),
                        rs.getBigDecimal("default_price")
                );
                procedures.add(procedure);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to search procedure catalogs", e);
        }
        return procedures;
    }
}
