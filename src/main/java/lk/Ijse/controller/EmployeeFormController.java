package lk.Ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.Ijse.bo.BOFactory;
import lk.Ijse.bo.EmployeeBO;
import lk.Ijse.bo.impl.EmployeeBoImpl;
import lk.Ijse.dto.EmployeeDTO;
import lk.Ijse.util.Regex;
import lk.Ijse.util.TextFeildRegex;
import lk.Ijse.view.tm.EmployeeTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EmployeeFormController {
    @FXML
    public TextField txtCost;
    @FXML
    public Label lblEmployeeId;
    @FXML
    public JFXComboBox cmbAttenence;

    @FXML
    private AnchorPane EmployeeRoot;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colAttendance;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableView<EmployeeTm> tblEmployee;

    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmployeeName;

    @FXML
    private TextField txtSalary;

    @FXML
    private TextField txtSearch;

    public EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);

    public void initialize(){
        txtEmployeeName.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                cmbAttenence.requestFocus();
            }
        });

        cmbAttenence.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtContact.requestFocus();
            }
        });

        txtContact.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtAddress.requestFocus();
            }
        });

        txtAddress.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtSalary.requestFocus();
            }
        });

        txtSalary.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtCost.requestFocus();
            }
        });

        ObservableList<String> obList = FXCollections.observableArrayList();

        obList.add(0,"Yes");
        obList.add(1,"No");

        cmbAttenence.setItems(obList);

        getCurrentEmployeeId();
        setCellValueFactory();
        loadAllEmployees();
    }

    private void getCurrentEmployeeId() {
        try {
            String nextOrderId = employeeBO.generateNextEmployeeId();
            lblEmployeeId.setText(nextOrderId);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllEmployees() {
        ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();
        try {
            List<EmployeeDTO> empList = employeeBO.getAllEmployees();

            for (EmployeeDTO employee : empList){
                EmployeeTm employeeTm = new EmployeeTm(
                        employee.getEmpId(),
                        employee.getName(),
                        employee.getAttendance(),
                        employee.getContact(),
                        employee.getAddress(),
                        employee.getSalary()
                );
                obList.add(employeeTm);
            }

            tblEmployee.setItems(obList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAttendance.setCellValueFactory(new PropertyValueFactory<>("attendance"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFleids();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String empId = lblEmployeeId.getText();

        try {
            boolean isDeleted = employeeBO.deleteEmplyee(empId);

            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Employee is deleted successfully...!").show();
                clearFleids();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String empId = lblEmployeeId.getText();
        String name = txtEmployeeName.getText();
        String attendance = (String) cmbAttenence.getValue();
        int contact = Integer.parseInt(txtContact.getText());
        String address = txtAddress.getText();
        double salary = Double.parseDouble(txtSalary.getText());
        double cost = Double.parseDouble(txtCost.getText());

        EmployeeDTO employee = new EmployeeDTO(empId, name, attendance, contact, address, salary, cost);

        try {
            boolean isSaved = employeeBO.saveEmployee(employee);

            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "New Employee is saved successfully...!").show();
                clearFleids();
            }

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String empId = lblEmployeeId.getText();
        String empName = txtEmployeeName.getText();
        String attendance = (String) cmbAttenence.getValue();
        int contact = Integer.parseInt(txtContact.getText());
        String address = txtAddress.getText();
        double salary = Double.parseDouble(txtSalary.getText());
        double cost = Double.parseDouble(txtCost.getText());

        EmployeeDTO employee = new EmployeeDTO(empId, empName, attendance, contact, address, salary, cost);

        try {
            boolean isUpdated = employeeBO.updateEmployee(employee);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Employee is Updated successfully...!").show();
                clearFleids();
            }

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void imgBackOnAction(MouseEvent mouseEvent) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/dashboardForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) EmployeeRoot.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFleids(){
        txtEmployeeName.setText("");
        txtContact.setText("");
        txtAddress.setText("");
        txtSalary.setText("");
        txtCost.setText("");
    }

    public void txtCostOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFeildRegex.PRICE,txtCost);
    }

    public void txtSalaryOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFeildRegex.PRICE,txtSalary);
    }

    public void txtEmployeeNameOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFeildRegex.NAME,txtEmployeeName);
    }

    public void txtContactOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFeildRegex.CONTACT,txtContact);
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        String id = txtSearch.getText();

        try {
            EmployeeDTO employee = employeeBO.searchByEmployeeId(id);
            if (employee != null) {
                lblEmployeeId.setText(employee.getEmpId());
                txtEmployeeName.setText(employee.getName());
                txtAddress.setText(employee.getAddress());
                txtContact.setText(String.valueOf(employee.getContact()));
                txtCost.setText(String.valueOf(employee.getCost()));
                txtSalary.setText(String.valueOf(employee.getSalary()));
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Employee not found!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
