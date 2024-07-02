package lk.Ijse.controller;

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
import lk.Ijse.bo.CustomerBO;
import lk.Ijse.bo.impl.CustomerBoImpl;
import lk.Ijse.dto.CustomerDTO;
import lk.Ijse.util.Regex;
import lk.Ijse.util.TextFeildRegex;
import lk.Ijse.view.tm.CustomerTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CustomerFormController {
    @FXML
    public Label lblCustomerId;

    @FXML
    private AnchorPane CustomerRoot;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableView<CustomerTm> tblCustomer;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colCustomerName;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtCustomerNumber;

    @FXML
    private TextField txtSearch;

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    public void initialize() {
        txtCustomerName.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtCustomerNumber.requestFocus();
            }
        });

        txtCustomerNumber.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtAddress.requestFocus();
            }
        });

        getCurrentCustomerId();
        setCellValueFactory();
        loadAllCustomer();
    }

    private void getCurrentCustomerId() {
        try {
            String nextOrderId = customerBO.generateNextCustomerId();
            lblCustomerId.setText(nextOrderId);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String cusId = lblCustomerId.getText();

        try {
            boolean isDelete = customerBO.deleteCustomer(cusId);

            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION, "Customer is Deleted...!").show();
                clearFields();
            }
        }catch (SQLException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String cusId = lblCustomerId.getText();
        String cusName = txtCustomerName.getText();
        String address = txtAddress.getText();
        int contact = Integer.parseInt(txtCustomerNumber.getText());

        try {
            CustomerDTO customer = new CustomerDTO(cusId, cusName, address, contact);

            boolean isSaved = customerBO.saveCustomer(customer);

            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION, "New Customer is Saved...!").show();
                clearFields();
            }
        }catch (SQLException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }
    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String cusId = lblCustomerId.getText();
        String cusName = txtCustomerName.getText();
        String address = txtAddress.getText();
        int contact = Integer.parseInt(txtCustomerNumber.getText());

        try {
            CustomerDTO customer = new CustomerDTO(cusId, cusName, address, contact);

            boolean isUpdate = customerBO.updateCustomer(customer);

            if (isUpdate){
                new Alert(Alert.AlertType.INFORMATION, "Customer is Updated...!").show();
                clearFields();
            }
        }catch (SQLException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void imgBackOnAction(MouseEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/dashboardForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) CustomerRoot.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        txtCustomerName.setText("");
        txtAddress.setText("");
        txtCustomerNumber.setText("");
    }

    private void loadAllCustomer(){
        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();

        try {
            List<CustomerDTO> customerList = customerBO.getAllCustomers();
            for (CustomerDTO customer : customerList){
                CustomerTm customerTm = new CustomerTm(customer.getId(), customer.getName(), customer.getAddress(), customer.getTel());
                obList.add(customerTm);
            }
            tblCustomer.setItems(obList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory(){
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("tel"));
    }

    public void txtCustomerNameOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFeildRegex.NAME,txtCustomerName);

    }

    public void txtNumberOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFeildRegex.CONTACT,txtCustomerNumber);
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        String tel = txtSearch.getText();

        try {
            CustomerDTO customer = customerBO.searchByCustomerId(tel);
            if (customer != null) {
                lblCustomerId.setText(customer.getId());
                txtCustomerName.setText(customer.getName());
                txtAddress.setText(customer.getAddress());
                txtCustomerNumber.setText(String.valueOf(customer.getTel()));
            } else {
                new Alert(Alert.AlertType.INFORMATION, "customer not found!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            //throw new RuntimeException(e);
        }
    }
}
