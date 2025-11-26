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
import java.text.DecimalFormat;
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
    @FXML
    private Button btnResetMedicine;

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
    @FXML
    private Button btnResetProcedure;

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
    
    // Formatter cho tiền: xxx.xxx.xxx (không có số thập phân)
    private final DecimalFormat currencyFormatter = new DecimalFormat("#,###");
    
    /**
     * Format số tiền theo dịnh dạng Việt Nam: xxx.xxx.xxx
     * @param value Giá trị BigDecimal
     * @return Chuỗi đã format
     */
    private String formatCurrency(BigDecimal value) {
        if (value == null) return "0";
        // DecimalFormat sử dụng dấu phẩy, thay bằng dấu chấm
        return currencyFormatter.format(value.longValue()).replace(',', '.');
    }

    @FXML
    public void initialize() {
        // Initialize Medicine Table
        colMedicineID.setCellValueFactory(new PropertyValueFactory<>("formattedMedicineId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colStrength.setCellValueFactory(new PropertyValueFactory<>("strength"));
        colUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        tableMedicine.setItems(medicines);
        tableMedicine.setPlaceholder(new Label(""));

        // Initialize Procedure Catalog Table
        colProcedureID.setCellValueFactory(new PropertyValueFactory<>("formattedProcedureId"));
        colProcedureName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDefaultPrice.setCellValueFactory(new PropertyValueFactory<>("defaultPrice"));
        // Format cột giá tiền
        colDefaultPrice.setCellFactory(column -> new TableCell<ProcedureCatalog, BigDecimal>() {
            @Override
            protected void updateItem(BigDecimal item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(formatCurrency(item));
                }
            }
        });
        tableProcedureCatalog.setItems(procedures);
        tableProcedureCatalog.setPlaceholder(new Label(""));

        // Initialize Report 1 Table
        colDoctorID.setCellValueFactory(new PropertyValueFactory<>("formattedDoctorId"));
        colDoctorName.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        colNumberOfPatients.setCellValueFactory(new PropertyValueFactory<>("numberOfPatients"));
        colNumberOfCompletedExaminations.setCellValueFactory(new PropertyValueFactory<>("numberOfCompletedExaminations"));
        colTotalRevenue.setCellValueFactory(new PropertyValueFactory<>("totalRevenue"));
        // Format cột doanh thu
        colTotalRevenue.setCellFactory(column -> new TableCell<DoctorPerformance, BigDecimal>() {
            @Override
            protected void updateItem(BigDecimal item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(formatCurrency(item));
                }
            }
        });
        tableReport1.setItems(doctorPerformances);
        tableReport1.setPlaceholder(new Label(""));

        // Initialize Report 2 Table
        colMonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        colRevenue2.setCellValueFactory(new PropertyValueFactory<>("totalRevenue"));
        // Format cột doanh thu
        colRevenue2.setCellFactory(column -> new TableCell<MonthlyRevenue, BigDecimal>() {
            @Override
            protected void updateItem(BigDecimal item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(formatCurrency(item));
                }
            }
        });
        tableReport2.setItems(monthlyRevenues);
        tableReport2.setPlaceholder(new Label(""));

        // Medicine selection listener
        tableMedicine.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                txtMedicineID.setText(newSel.getFormattedMedicineId());
                txtMedicineName.setText(newSel.getName());
                txtStrength.setText(newSel.getStrength());
                txtUnit.setText(newSel.getUnit());
            }
        });

        // Procedure Catalog selection listener
        tableProcedureCatalog.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                txtProcedureID.setText(newSel.getFormattedProcedureId());
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
        btnResetMedicine.setOnAction(e -> onResetMedicine());

        btnAddProcedure.setOnAction(e -> onAddProcedure());
        btnUpdateProcedure.setOnAction(e -> onUpdateProcedure());
        btnDeleteProcedure.setOnAction(e -> onDeleteProcedure());
        btnSearchProcedure.setOnAction(e -> onSearchProcedure());
        btnResetProcedure.setOnAction(e -> onResetProcedure());

        btnSearchReport1.setOnAction(e -> onSearchReport1());

        btnSearchReport2.setOnAction(e -> onSearchReport2());

        // Load initial data
        loadMedicineTable();
        loadProcedureCatalogTable();
    }

    // Medicine Methods
    private void onAddMedicine() {
        String name = txtMedicineName.getText();
        String strength = txtStrength.getText();
        String unit = txtUnit.getText();

        if (name == null || name.isBlank()) {
            showError("Vui lòng nhập Name");
            return;
        }

        Medicine medicine = new Medicine(
                0, name.trim(),
                strength != null ? strength.trim() : "",
                unit != null ? unit.trim() : ""
        );

        medicineDAO.insertMedicine(medicine);
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

        String name = txtMedicineName.getText();
        String strength = txtStrength.getText();
        String unit = txtUnit.getText();

        if (name == null || name.isBlank()) {
            showError("Vui lòng nhập Name");
            return;
        }

        selected.setName(name.trim());
        selected.setStrength(strength != null ? strength.trim() : "");
        selected.setUnit(unit != null ? unit.trim() : "");

        medicineDAO.updateMedicine(selected);
        clearMedicineForm();
        loadMedicineTable();
        showInfo("Đã cập nhật medicine thành công!");
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
        // Lấy giá trị từ các trường nhập (bỏ qua ID vì nó chỉ hiển thị)
        String name = txtMedicineName.getText();
        String strength = txtStrength.getText();
        String unit = txtUnit.getText();
        
        // Nếu tất cả trường đều rỗng, tải lại toàn bộ danh sách
        if ((name == null || name.isBlank()) && 
            (strength == null || strength.isBlank()) && 
            (unit == null || unit.isBlank())) {
            loadMedicineTable();
        } else {
            // Tìm kiếm với các điều kiện đã nhập
            medicines.setAll(medicineDAO.searchMedicines(name, strength, unit));
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

    private void onResetMedicine() {
        clearMedicineForm();
        loadMedicineTable();
    }

    // Procedure Catalog Methods
    private void onAddProcedure() {
        String name = txtProcedureName.getText();
        String type = txtType.getText();
        String description = txtDescription.getText();
        String priceStr = txtDefaultPrice.getText();

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
                0, name.trim(),
                type != null ? type.trim() : "",
                description != null ? description.trim() : "",
                price
        );

        procedureCatalogDAO.insertProcedureCatalog(procedure);
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

        String name = txtProcedureName.getText();
        String type = txtType.getText();
        String description = txtDescription.getText();
        String priceStr = txtDefaultPrice.getText();

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

        selected.setName(name.trim());
        selected.setType(type != null ? type.trim() : "");
        selected.setDescription(description != null ? description.trim() : "");
        selected.setDefaultPrice(price);

        procedureCatalogDAO.updateProcedureCatalog(selected);
        clearProcedureForm();
        loadProcedureCatalogTable();
        showInfo("Đã cập nhật procedure catalog thành công!");
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
        // Lấy giá trị từ các trường nhập (bỏ qua ID vì nó chỉ hiển thị)
        String name = txtProcedureName.getText();
        String type = txtType.getText();
        String description = txtDescription.getText();
        String defaultPrice = txtDefaultPrice.getText();
        
        // Nếu tất cả trường đều rỗng, tải lại toàn bộ danh sách
        if ((name == null || name.isBlank()) && 
            (type == null || type.isBlank()) && 
            (description == null || description.isBlank()) &&
            (defaultPrice == null || defaultPrice.isBlank())) {
            loadProcedureCatalogTable();
        } else {
            // Tìm kiếm với các điều kiện đã nhập
            procedures.setAll(procedureCatalogDAO.searchProcedureCatalogs(name, type, description, defaultPrice));
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

    private void onResetProcedure() {
        clearProcedureForm();
        loadProcedureCatalogTable();
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
        lblTotalRevenueReport1.setText(formatCurrency(totalRevenue) + " VNĐ");

        showInfo("Đã tải báo cáo thành công!");
    }

    // Report 2 Methods
    private void onSearchReport2() {
        // Clear selection to prevent data loss on scroll
        tableReport2.getSelectionModel().clearSelection();
        
        // Load all monthly revenue data
        var revenues = monthlyRevenueDAO.getMonthlyRevenueReport();
        
        // Clear and reload data - tạo mới ObservableList để force reload
        monthlyRevenues.clear();
        monthlyRevenues.addAll(revenues);
        
        // Refresh table và set lại items để reload hoàn toàn
        tableReport2.setItems(null);
        tableReport2.layout();
        tableReport2.setItems(monthlyRevenues);
        tableReport2.refresh();

        if (revenues.isEmpty()) {
            showInfo("Không có dữ liệu doanh thu trong cơ sở dữ liệu");
        } else {
            showInfo("Đã tải " + revenues.size() + " bản ghi doanh thu theo tháng!");
        }
    }
}
