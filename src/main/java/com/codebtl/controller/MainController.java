package com.codebtl.controller;

import com.codebtl.model.Medicine;
import com.codebtl.model.MedicineDAO;
import com.codebtl.model.ProcedureCatalog;
import com.codebtl.model.ProcedureCatalogDAO;
import com.codebtl.model.DoctorPerformance;
import com.codebtl.model.DoctorPerformanceDAO;
import com.codebtl.model.MonthlyRevenue;
import com.codebtl.model.MonthlyRevenueDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.time.LocalDate;

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

    // Report 1 Tab Components
    @FXML
    private TableView<DoctorPerformance> tableReport1;
    @FXML
    private TableColumn<DoctorPerformance, String> colDoctorID;
    @FXML
    private TableColumn<DoctorPerformance, String> colDoctorName;
    @FXML
    private TableColumn<DoctorPerformance, LocalDate> colDOB;
    @FXML
    private TableColumn<DoctorPerformance, Integer> colNumberOfPatients;
    @FXML
    private TableColumn<DoctorPerformance, Integer> colNumberOfCompletedExaminations;
    @FXML
    private TableColumn<DoctorPerformance, BigDecimal> colTotalRevenue;

    @FXML
    private DatePicker datePickerStartReport1;
    @FXML
    private DatePicker datePickerEndReport1;
    @FXML
    private Button btnSearchReport1;
    @FXML
    private Label lblTotalRevenueReport1;

    // Report 2 Tab Components
    @FXML
    private TableView<MonthlyRevenue> tableReport2;
    @FXML
    private TableColumn<MonthlyRevenue, Integer> colMonth;
    @FXML
    private TableColumn<MonthlyRevenue, Integer> colYear;
    @FXML
    private TableColumn<MonthlyRevenue, BigDecimal> colRevenue2;

    @FXML
    private Button btnSearchReport2;

    private final MedicineDAO medicineDAO = new MedicineDAO();
    private final ProcedureCatalogDAO procedureCatalogDAO = new ProcedureCatalogDAO();
    private final DoctorPerformanceDAO doctorPerformanceDAO = new DoctorPerformanceDAO();
    private final MonthlyRevenueDAO monthlyRevenueDAO = new MonthlyRevenueDAO();
    private final ObservableList<Medicine> medicines = FXCollections.observableArrayList();
    private final ObservableList<ProcedureCatalog> procedures = FXCollections.observableArrayList();
    private final ObservableList<DoctorPerformance> doctorPerformances = FXCollections.observableArrayList();
    private final ObservableList<MonthlyRevenue> monthlyRevenues = FXCollections.observableArrayList();

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

        // Initialize Report 1 Table
        colDoctorID.setCellValueFactory(new PropertyValueFactory<>("doctorId"));
        colDoctorName.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        colNumberOfPatients.setCellValueFactory(new PropertyValueFactory<>("numberOfPatients"));
        colNumberOfCompletedExaminations.setCellValueFactory(new PropertyValueFactory<>("numberOfCompletedExaminations"));
        colTotalRevenue.setCellValueFactory(new PropertyValueFactory<>("totalRevenue"));
        tableReport1.setItems(doctorPerformances);

        // Initialize Report 2 Table
        colMonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        colRevenue2.setCellValueFactory(new PropertyValueFactory<>("totalRevenue"));
        tableReport2.setItems(monthlyRevenues);

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

        btnSearchReport1.setOnAction(e -> onSearchReport1());

        btnSearchReport2.setOnAction(e -> onSearchReport2());

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

    // Report 1 Methods
    private void onSearchReport1() {
        LocalDate startDate = datePickerStartReport1.getValue();
        LocalDate endDate = datePickerEndReport1.getValue();

        if (startDate == null || endDate == null) {
            showError("Vui lòng chọn cả ngày bắt đầu và ngày kết thúc");
            return;
        }

        if (startDate.isAfter(endDate)) {
            showError("Ngày bắt đầu phải trước ngày kết thúc");
            return;
        }

        // Load data from database
        var performances = doctorPerformanceDAO.getDoctorPerformanceReport(startDate, endDate);
        doctorPerformances.setAll(performances);

        // Calculate and display total revenue
        BigDecimal totalRevenue = doctorPerformanceDAO.calculateTotalRevenue(performances);
        lblTotalRevenueReport1.setText(String.format("%.2f $", totalRevenue));

        showInfo("Đã tải báo cáo thành công!");
    }

    // Report 2 Methods
    private void onSearchReport2() {
        // Load all monthly revenue data
        var revenues = monthlyRevenueDAO.getMonthlyRevenueReport();
        monthlyRevenues.setAll(revenues);

        if (revenues.isEmpty()) {
            showInfo("Không có dữ liệu doanh thu trong cơ sở dữ liệu");
        } else {
            showInfo("Đã tải báo cáo doanh thu theo tháng thành công!");
        }
    }
}
