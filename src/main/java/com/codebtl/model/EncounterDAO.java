package com.codebtl.model;

import com.codebtl.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class EncounterDAO {

    public EncounterDAO() {
    }

    public List<Encounter> getAllEncounters() {
        List<Encounter> encounters = new ArrayList<>();
        String sql = "SELECT encounter_id, app_id, start_time, end_time, diagnosis, symtom, notes, status, d_person " +
                     "FROM encounters ORDER BY encounter_id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Encounter encounter = new Encounter(
                        rs.getString("encounter_id"),
                        rs.getString("app_id"),
                        rs.getTimestamp("start_time"),
                        rs.getTimestamp("end_time"),
                        rs.getString("diagnosis"),
                        rs.getString("symtom"),
                        rs.getString("notes"),
                        rs.getString("status"),
                        rs.getString("d_person")
                );
                encounters.add(encounter);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch encounters", e);
        }
        return encounters;
    }

    public boolean insertEncounter(Encounter encounter) {
        String sql = "INSERT INTO encounters(encounter_id, app_id, start_time, end_time, diagnosis, symtom, notes, status, d_person) " +
                     "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, encounter.getEncounterId());
            ps.setString(2, encounter.getAppId());
            ps.setTimestamp(3, encounter.getStartTime());
            ps.setTimestamp(4, encounter.getEndTime());
            ps.setString(5, encounter.getDiagnosis());
            ps.setString(6, encounter.getSymptom());
            ps.setString(7, encounter.getNotes());
            ps.setString(8, encounter.getStatus());
            ps.setString(9, encounter.getDPerson());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate entry")) {
                return false;
            }
            throw new RuntimeException("Failed to insert encounter", e);
        }
    }

    public boolean updateEncounter(Encounter encounter) {
        String sql = "UPDATE encounters SET app_id = ?, start_time = ?, end_time = ?, diagnosis = ?, " +
                     "symtom = ?, notes = ?, status = ?, d_person = ? WHERE encounter_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, encounter.getAppId());
            ps.setTimestamp(2, encounter.getStartTime());
            ps.setTimestamp(3, encounter.getEndTime());
            ps.setString(4, encounter.getDiagnosis());
            ps.setString(5, encounter.getSymptom());
            ps.setString(6, encounter.getNotes());
            ps.setString(7, encounter.getStatus());
            ps.setString(8, encounter.getDPerson());
            ps.setString(9, encounter.getEncounterId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update encounter", e);
        }
    }

    public boolean deleteEncounter(String encounterId) {
        String sql = "DELETE FROM encounters WHERE encounter_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, encounterId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete encounter", e);
        }
    }

    public List<Encounter> searchEncounters(String keyword) {
        List<Encounter> encounters = new ArrayList<>();
        String sql = "SELECT encounter_id, app_id, start_time, end_time, diagnosis, symtom, notes, status, d_person " +
                     "FROM encounters WHERE encounter_id LIKE ? OR app_id LIKE ? OR diagnosis LIKE ? OR symtom LIKE ? " +
                     "ORDER BY encounter_id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            String searchPattern = "%" + keyword + "%";
            ps.setString(1, searchPattern);
            ps.setString(2, searchPattern);
            ps.setString(3, searchPattern);
            ps.setString(4, searchPattern);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Encounter encounter = new Encounter(
                        rs.getString("encounter_id"),
                        rs.getString("app_id"),
                        rs.getTimestamp("start_time"),
                        rs.getTimestamp("end_time"),
                        rs.getString("diagnosis"),
                        rs.getString("symtom"),
                        rs.getString("notes"),
                        rs.getString("status"),
                        rs.getString("d_person")
                );
                encounters.add(encounter);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to search encounters", e);
        }
        return encounters;
    }
}

