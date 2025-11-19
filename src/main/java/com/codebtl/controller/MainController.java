package com.codebtl.controller;

import com.codebtl.model.Encounter;
import com.codebtl.model.EncounterDAO;
import com.codebtl.model.Medicine;
import com.codebtl.model.MedicineDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class MainController {

    // Encounter Tab Components
    @FXML
    private TableView<Encounter> tableEncounter;
    @FXML
    private TableColumn<Encounter, String> colEncounterID;
    @FXML
    private TableColumn<Encounter, String> colAppointmentID;
    @FXML
    private TableColumn<Encounter, String> colChiefComplaint;
    @FXML
    private TableColumn<Encounter, String> colDiagnosis;
    @FXML
    private TableColumn<Encounter, String> colNotes;
    @FXML
    private TableColumn<Encounter, String> colStartTime;
    @FXML
    private TableColumn<Encounter, String> colEndTime;
    @FXML
    private TableColumn<Encounter, String> colFee;

    @FXML
    private TextField txtEncounterID;
    @FXML
    private TextField txtAppointmentID;
    @FXML
    private TextField txtChiefComplaint;
    @FXML
    private TextField txtDiagnosis;
    @FXML
    private TextField txtNotes;
    @FXML
    private TextField txtStartTime;
    @FXML
    private TextField txtEndTime;
    @FXML
    private TextField txtFee;

    @FXML
    private Button btnAddEncounter;
    @FXML
    private Button btnUpdateEncounter;
    @FXML
    private Button btnDeleteEncounter;
    @FXML
    private Button btnSearchEncounter;

    // Medicine Tab Components
    @FXML
    private TableView<Medicine> tableMedicine;
    @FXML
    private TableColumn<Medicine, String> colMedicineID;
    @FXML
    private TableColumn<Medicine, String> colName;
    @FXML
    private TableColumn<Medicine, String> colStrength;
    @FXML
    private TableColumn<Medicine, String> colUnit;

    @FXML
    private TextField txtMedicineID;
    @FXML
    private TextField txtMedicineName;
    @FXML
    private TextField txtStrength;
    @FXML
    private TextField txtUnit;
    @FXML
    private TextField txtMedicineFee;

    @FXML
    private Button btnAddMedicine;
    @FXML
    private Button btnUpdateMedicine;
    @FXML
    private Button btnDeleteMedicine;
    @FXML
    private Button btnSearchMedicine;

    private final EncounterDAO encounterDAO = new EncounterDAO();
    private final MedicineDAO medicineDAO = new MedicineDAO();
    private final ObservableList<Encounter> encounters = FXCollections.observableArrayList();
    private final ObservableList<Medicine> medicines = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialize Encounter Table
        colEncounterID.setCellValueFactory(new PropertyValueFactory<>("encounterId"));
        colAppointmentID.setCellValueFactory(new PropertyValueFactory<>("appId"));
        colChiefComplaint.setCellValueFactory(new PropertyValueFactory<>("symptom"));
        colDiagnosis.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
        colNotes.setCellValueFactory(new PropertyValueFactory<>("notes"));
        colStartTime.setCellValueFactory(new PropertyValueFactory<>("startTimeString"));
        colEndTime.setCellValueFactory(new PropertyValueFactory<>("endTimeString"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("status"));
        tableEncounter.setItems(encounters);

        // Initialize Medicine Table
        colMedicineID.setCellValueFactory(new PropertyValueFactory<>("medicineId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colStrength.setCellValueFactory(new PropertyValueFactory<>("strength"));
        colUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        tableMedicine.setItems(medicines);

        // Encounter selection listener
        tableEncounter.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                txtEncounterID.setText(newSel.getEncounterId());
                txtAppointmentID.setText(newSel.getAppId());
                txtChiefComplaint.setText(newSel.getSymptom());
                txtDiagnosis.setText(newSel.getDiagnosis());
                txtNotes.setText(newSel.getNotes());
                txtStartTime.setText(newSel.getStartTimeString());
                txtEndTime.setText(newSel.getEndTimeString());
                txtFee.setText(newSel.getStatus());
            }
        });

        // Medicine selection listener
        tableMedicine.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                txtMedicineID.setText(newSel.getMedicineId());
                txtMedicineName.setText(newSel.getName());
                txtStrength.setText(newSel.getStrength());
                txtUnit.setText(newSel.getUnit());
            }
        });

        // Set button actions
        btnAddEncounter.setOnAction(e -> onAddEncounter());
        btnUpdateEncounter.setOnAction(e -> onUpdateEncounter());
        btnDeleteEncounter.setOnAction(e -> onDeleteEncounter());
        btnSearchEncounter.setOnAction(e -> onSearchEncounter());

        btnAddMedicine.setOnAction(e -> onAddMedicine());
        btnUpdateMedicine.setOnAction(e -> onUpdateMedicine());
        btnDeleteMedicine.setOnAction(e -> onDeleteMedicine());
        btnSearchMedicine.setOnAction(e -> onSearchMedicine());

        // Load initial data
        loadEncounterTable();
        loadMedicineTable();
    }

    // Encounter Methods
    private void onAddEncounter() {
        String encounterId = txtEncounterID.getText();
        String appId = txtAppointmentID.getText();
        String symptom = txtChiefComplaint.getText();
        String diagnosis = txtDiagnosis.getText();
        String notes = txtNotes.getText();
        String startTimeStr = txtStartTime.getText();
        String endTimeStr = txtEndTime.getText();
        String status = txtFee.getText();

        if (encounterId == null || encounterId.isBlank()) {
            showError("Vui lòng nhập Encounter ID");
            return;
        }
        if (appId == null || appId.isBlank()) {
            showError("Vui lòng nhập Appointment ID");
            return;
        }

        Timestamp startTime = null;
        Timestamp endTime = null;
        
        try {
            if (startTimeStr != null && !startTimeStr.isBlank()) {
                startTime = Timestamp.valueOf(startTimeStr.trim());
            }
            if (endTimeStr != null && !endTimeStr.isBlank()) {
                endTime = Timestamp.valueOf(endTimeStr.trim());
            }
        } catch (IllegalArgumentException e) {
            showError("Định dạng thời gian không hợp lệ. Sử dụng: yyyy-MM-dd HH:mm:ss");
            return;
        }

        Encounter encounter = new Encounter(
                encounterId.trim(), appId.trim(), startTime, endTime,
                diagnosis != null ? diagnosis.trim() : "",
                symptom != null ? symptom.trim() : "", 
                notes != null ? notes.trim() : "",
                status != null ? status.trim() : "", ""
        );

        boolean success = encounterDAO.insertEncounter(encounter);
        if (!success) {
            showError("Encounter ID đã tồn tại! Vui lòng nhập mã khác.");
            return;
        }

        clearEncounterForm();
        loadEncounterTable();
        showInfo("Thêm encounter thành công!");
    }

    private void onUpdateEncounter() {
        Encounter selected = tableEncounter.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Hãy chọn một encounter trong bảng để cập nhật");
            return;
        }

        String encounterId = txtEncounterID.getText();
        String appId = txtAppointmentID.getText();
        String symptom = txtChiefComplaint.getText();
        String diagnosis = txtDiagnosis.getText();
        String notes = txtNotes.getText();
        String startTimeStr = txtStartTime.getText();
        String endTimeStr = txtEndTime.getText();
        String status = txtFee.getText();

        if (encounterId == null || encounterId.isBlank()) {
            showError("Vui lòng nhập Encounter ID");
            return;
        }
        if (appId == null || appId.isBlank()) {
            showError("Vui lòng nhập Appointment ID");
            return;
        }

        Timestamp startTime = null;
        Timestamp endTime = null;
        
        try {
            if (startTimeStr != null && !startTimeStr.isBlank()) {
                startTime = Timestamp.valueOf(startTimeStr.trim());
            }
            if (endTimeStr != null && !endTimeStr.isBlank()) {
                endTime = Timestamp.valueOf(endTimeStr.trim());
            }
        } catch (IllegalArgumentException e) {
            showError("Định dạng thời gian không hợp lệ. Sử dụng: yyyy-MM-dd HH:mm:ss");
            return;
        }

        selected.setEncounterId(encounterId.trim());
        selected.setAppId(appId.trim());
        selected.setSymptom(symptom != null ? symptom.trim() : "");
        selected.setDiagnosis(diagnosis != null ? diagnosis.trim() : "");
        selected.setNotes(notes != null ? notes.trim() : "");
        selected.setStartTime(startTime);
        selected.setEndTime(endTime);
        selected.setStatus(status != null ? status.trim() : "");

        encounterDAO.updateEncounter(selected);
        clearEncounterForm();
        loadEncounterTable();
        showInfo("Cập nhật encounter thành công!");
    }

    private void onDeleteEncounter() {
        Encounter selected = tableEncounter.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Hãy chọn một encounter trong bảng để xóa");
            return;
        }

        encounterDAO.deleteEncounter(selected.getEncounterId());
        clearEncounterForm();
        loadEncounterTable();
        showInfo("Xóa encounter thành công!");
    }

    private void onSearchEncounter() {
        String keyword = txtEncounterID.getText();
        if (keyword == null || keyword.isBlank()) {
            loadEncounterTable();
        } else {
            encounters.setAll(encounterDAO.searchEncounters(keyword.trim()));
        }
    }

    private void loadEncounterTable() {
        encounters.setAll(encounterDAO.getAllEncounters());
    }

    private void clearEncounterForm() {
        txtEncounterID.clear();
        txtAppointmentID.clear();
        txtChiefComplaint.clear();
        txtDiagnosis.clear();
        txtNotes.clear();
        txtStartTime.clear();
        txtEndTime.clear();
        txtFee.clear();
        tableEncounter.getSelectionModel().clearSelection();
    }

    // Medicine Methods
    private void onAddMedicine() {
        String medicineId = txtMedicineID.getText();
        String name = txtMedicineName.getText();
        String strength = txtStrength.getText();
        String unit = txtUnit.getText();

        if (medicineId == null || medicineId.isBlank()) {
            showError("Vui lòng nhập Medicine ID");
            return;
        }
        if (name == null || name.isBlank()) {
            showError("Vui lòng nhập Name");
            return;
        }

        Medicine medicine = new Medicine(
                medicineId.trim(), name.trim(),
                strength != null ? strength.trim() : "",
                unit != null ? unit.trim() : ""
        );

        boolean success = medicineDAO.insertMedicine(medicine);
        if (!success) {
            showError("Medicine ID đã tồn tại! Vui lòng nhập mã khác.");
            return;
        }

        clearMedicineForm();
        loadMedicineTable();
        showInfo("Thêm medicine thành công!");
    }

    private void onUpdateMedicine() {
        Medicine selected = tableMedicine.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Hãy chọn một medicine trong bảng để cập nhật");
            return;
        }

        String medicineId = txtMedicineID.getText();
        String name = txtMedicineName.getText();
        String strength = txtStrength.getText();
        String unit = txtUnit.getText();

        if (medicineId == null || medicineId.isBlank()) {
            showError("Vui lòng nhập Medicine ID");
            return;
        }
        if (name == null || name.isBlank()) {
            showError("Vui lòng nhập Name");
            return;
        }

        selected.setMedicineId(medicineId.trim());
        selected.setName(name.trim());
        selected.setStrength(strength != null ? strength.trim() : "");
        selected.setUnit(unit != null ? unit.trim() : "");

        medicineDAO.updateMedicine(selected);
        clearMedicineForm();
        loadMedicineTable();
        showInfo("Cập nhật medicine thành công!");
    }

    private void onDeleteMedicine() {
        Medicine selected = tableMedicine.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Hãy chọn một medicine trong bảng để xóa");
            return;
        }

        medicineDAO.deleteMedicine(selected.getMedicineId());
        clearMedicineForm();
        loadMedicineTable();
        showInfo("Xóa medicine thành công!");
    }

    private void onSearchMedicine() {
        String keyword = txtMedicineID.getText();
        if (keyword == null || keyword.isBlank()) {
            loadMedicineTable();
        } else {
            medicines.setAll(medicineDAO.searchMedicines(keyword.trim()));
        }
    }

    private void loadMedicineTable() {
        medicines.setAll(medicineDAO.getAllMedicines());
    }

    private void clearMedicineForm() {
        txtMedicineID.clear();
        txtMedicineName.clear();
        txtStrength.clear();
        txtUnit.clear();
        txtMedicineFee.clear();
        tableMedicine.getSelectionModel().clearSelection();
    }

    // Utility Methods
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Lỗi");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
