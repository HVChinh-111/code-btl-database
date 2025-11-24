package com.codebtl.controller;

import com.codebtl.model.Medicine;
import com.codebtl.model.MedicineDAO;
import com.codebtl.model.ProcedureCatalog;
import com.codebtl.model.ProcedureCatalogDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;

public class MainController {

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
    private Button btnAddMedicine;
    @FXML
    private Button btnUpdateMedicine;
    @FXML
    private Button btnDeleteMedicine;
    @FXML
    private Button btnSearchMedicine;

    // Procedure Catalog Tab Components
    @FXML
    private TableView<ProcedureCatalog> tableProcedureCatalog;
    @FXML
    private TableColumn<ProcedureCatalog, String> colProcedureID;
    @FXML
    private TableColumn<ProcedureCatalog, String> colProcedureName;
    @FXML
    private TableColumn<ProcedureCatalog, String> colType;
    @FXML
    private TableColumn<ProcedureCatalog, String> colDescription;
    @FXML
    private TableColumn<ProcedureCatalog, BigDecimal> colDefaultPrice;

    @FXML
    private TextField txtProcedureID;
    @FXML
    private TextField txtProcedureName;
    @FXML
    private TextField txtType;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtDefaultPrice;

    @FXML
    private Button btnAddProcedure;
    @FXML
    private Button btnUpdateProcedure;
    @FXML
    private Button btnDeleteProcedure;
    @FXML
    private Button btnSearchProcedure;

    private final MedicineDAO medicineDAO = new MedicineDAO();
    private final ProcedureCatalogDAO procedureCatalogDAO = new ProcedureCatalogDAO();
    private final ObservableList<Medicine> medicines = FXCollections.observableArrayList();
    private final ObservableList<ProcedureCatalog> procedures = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialize Medicine Table
        colMedicineID.setCellValueFactory(new PropertyValueFactory<>("medicineId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colStrength.setCellValueFactory(new PropertyValueFactory<>("strength"));
        colUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        tableMedicine.setItems(medicines);

        // Initialize Procedure Catalog Table
        colProcedureID.setCellValueFactory(new PropertyValueFactory<>("procedureId"));
        colProcedureName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDefaultPrice.setCellValueFactory(new PropertyValueFactory<>("defaultPrice"));
        tableProcedureCatalog.setItems(procedures);

        // Medicine selection listener
        tableMedicine.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                txtMedicineID.setText(newSel.getMedicineId());
                txtMedicineName.setText(newSel.getName());
                txtStrength.setText(newSel.getStrength());
                txtUnit.setText(newSel.getUnit());
            }
        });

        // Procedure Catalog selection listener
        tableProcedureCatalog.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                txtProcedureID.setText(newSel.getProcedureId());
                txtProcedureName.setText(newSel.getName());
                txtType.setText(newSel.getType());
                txtDescription.setText(newSel.getDescription());
                txtDefaultPrice.setText(newSel.getDefaultPrice() != null ? newSel.getDefaultPrice().toString() : "");
            }
        });

        // Set button actions
        btnAddMedicine.setOnAction(e -> onAddMedicine());
        btnUpdateMedicine.setOnAction(e -> onUpdateMedicine());
        btnDeleteMedicine.setOnAction(e -> onDeleteMedicine());
        btnSearchMedicine.setOnAction(e -> onSearchMedicine());

        btnAddProcedure.setOnAction(e -> onAddProcedure());
        btnUpdateProcedure.setOnAction(e -> onUpdateProcedure());
        btnDeleteProcedure.setOnAction(e -> onDeleteProcedure());
        btnSearchProcedure.setOnAction(e -> onSearchProcedure());

        // Load initial data
        loadMedicineTable();
        loadProcedureCatalogTable();
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
        tableMedicine.getSelectionModel().clearSelection();
    }

    // Procedure Catalog Methods
    private void onAddProcedure() {
        String procedureId = txtProcedureID.getText();
        String name = txtProcedureName.getText();
        String type = txtType.getText();
        String description = txtDescription.getText();
        String priceStr = txtDefaultPrice.getText();

        if (procedureId == null || procedureId.isBlank()) {
            showError("Vui lòng nhập Procedure ID");
            return;
        }
        if (name == null || name.isBlank()) {
            showError("Vui lòng nhập Name");
            return;
        }

        BigDecimal price = null;
        if (priceStr != null && !priceStr.isBlank()) {
            try {
                price = new BigDecimal(priceStr.trim());
                if (price.compareTo(BigDecimal.ZERO) < 0) {
                    showError("Giá phải là số dương");
                    return;
                }
            } catch (NumberFormatException e) {
                showError("Giá không hợp lệ");
                return;
            }
        }

        ProcedureCatalog procedure = new ProcedureCatalog(
                procedureId.trim(), name.trim(),
                type != null ? type.trim() : "",
                description != null ? description.trim() : "",
                price
        );

        boolean success = procedureCatalogDAO.insertProcedureCatalog(procedure);
        if (!success) {
            showError("Procedure ID đã tồn tại! Vui lòng nhập mã khác.");
            return;
        }

        clearProcedureForm();
        loadProcedureCatalogTable();
        showInfo("Thêm procedure catalog thành công!");
    }

    private void onUpdateProcedure() {
        ProcedureCatalog selected = tableProcedureCatalog.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Hãy chọn một procedure trong bảng để cập nhật");
            return;
        }

        String procedureId = txtProcedureID.getText();
        String name = txtProcedureName.getText();
        String type = txtType.getText();
        String description = txtDescription.getText();
        String priceStr = txtDefaultPrice.getText();

        if (procedureId == null || procedureId.isBlank()) {
            showError("Vui lòng nhập Procedure ID");
            return;
        }
        if (name == null || name.isBlank()) {
            showError("Vui lòng nhập Name");
            return;
        }

        BigDecimal price = null;
        if (priceStr != null && !priceStr.isBlank()) {
            try {
                price = new BigDecimal(priceStr.trim());
                if (price.compareTo(BigDecimal.ZERO) < 0) {
                    showError("Giá phải là số dương");
                    return;
                }
            } catch (NumberFormatException e) {
                showError("Giá không hợp lệ");
                return;
            }
        }

        selected.setProcedureId(procedureId.trim());
        selected.setName(name.trim());
        selected.setType(type != null ? type.trim() : "");
        selected.setDescription(description != null ? description.trim() : "");
        selected.setDefaultPrice(price);

        procedureCatalogDAO.updateProcedureCatalog(selected);
        clearProcedureForm();
        loadProcedureCatalogTable();
        showInfo("Cập nhật procedure catalog thành công!");
    }

    private void onDeleteProcedure() {
        ProcedureCatalog selected = tableProcedureCatalog.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Hãy chọn một procedure trong bảng để xóa");
            return;
        }

        procedureCatalogDAO.deleteProcedureCatalog(selected.getProcedureId());
        clearProcedureForm();
        loadProcedureCatalogTable();
        showInfo("Xóa procedure catalog thành công!");
    }

    private void onSearchProcedure() {
        String keyword = txtProcedureID.getText();
        if (keyword == null || keyword.isBlank()) {
            loadProcedureCatalogTable();
        } else {
            procedures.setAll(procedureCatalogDAO.searchProcedureCatalogs(keyword.trim()));
        }
    }

    private void loadProcedureCatalogTable() {
        procedures.setAll(procedureCatalogDAO.getAllProcedureCatalogs());
    }

    private void clearProcedureForm() {
        txtProcedureID.clear();
        txtProcedureName.clear();
        txtType.clear();
        txtDescription.clear();
        txtDefaultPrice.clear();
        tableProcedureCatalog.getSelectionModel().clearSelection();
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
