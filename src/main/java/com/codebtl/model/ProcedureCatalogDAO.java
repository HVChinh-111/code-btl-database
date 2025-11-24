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
        String sql = "SELECT procedure_id, name, type, description, default_price FROM procedure_catalogs ORDER BY procedure_id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ProcedureCatalog procedure = new ProcedureCatalog(
                        rs.getString("procedure_id"),
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
        String sql = "INSERT INTO procedure_catalogs(procedure_id, name, type, description, default_price) VALUES(?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, procedure.getProcedureId());
            ps.setString(2, procedure.getName());
            ps.setString(3, procedure.getType());
            ps.setString(4, procedure.getDescription());
            ps.setBigDecimal(5, procedure.getDefaultPrice());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate entry")) {
                return false;
            }
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
            ps.setString(5, procedure.getProcedureId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update procedure catalog", e);
        }
    }

    public boolean deleteProcedureCatalog(String procedureId) {
        String sql = "DELETE FROM procedure_catalogs WHERE procedure_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, procedureId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete procedure catalog", e);
        }
    }

    public List<ProcedureCatalog> searchProcedureCatalogs(String keyword) {
        List<ProcedureCatalog> procedures = new ArrayList<>();
        String sql = "SELECT procedure_id, name, type, description, default_price FROM procedure_catalogs " +
                     "WHERE procedure_id LIKE ? OR name LIKE ? OR type LIKE ? OR description LIKE ? " +
                     "ORDER BY procedure_id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            String searchPattern = "%" + keyword + "%";
            ps.setString(1, searchPattern);
            ps.setString(2, searchPattern);
            ps.setString(3, searchPattern);
            ps.setString(4, searchPattern);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProcedureCatalog procedure = new ProcedureCatalog(
                        rs.getString("procedure_id"),
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
