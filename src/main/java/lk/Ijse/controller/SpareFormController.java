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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.Ijse.bo.SpareBO;
import lk.Ijse.bo.impl.SpareBoImpl;
import lk.Ijse.dto.SparesDTO;
import lk.Ijse.view.tm.SpareTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SpareFormController {
    @FXML
    public Label lblSpareId;

    @FXML
    public JFXComboBox cmbSupplierId;

    @FXML
    private AnchorPane SpareRoot;

    @FXML
    private TableColumn<?, ?> colCount;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colSpareId;

    @FXML
    private TableView<SpareTm> tblSpare;

    @FXML
    private TextField txtCount;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtSearch;

    SpareBO spareBO = new SpareBoImpl();

    public void initialize(){
        txtName.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtCount.requestFocus();
            }
        });

        txtCount.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtPrice.requestFocus();
            }
        });

        getSupplierId();
        getCurrentSpareId();
        setCellValueFactory();
        loadAllSpares();
    }

    private void getCurrentSpareId() {
        try {
            String nextOrderId = spareBO.generateNextSpareId();
            lblSpareId.setText(nextOrderId);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadAllSpares() {
        ObservableList<SpareTm> obList = FXCollections.observableArrayList();

        try {
            List<SparesDTO> spareList = spareBO.getAllSpares();
            for (SparesDTO spares : spareList){
                SpareTm spareTm = new SpareTm(spares.getSpareId(), spares.getName(), spares.getCount(), spares.getPrice());
                obList.add(spareTm);
            }
            tblSpare.setItems(obList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colSpareId.setCellValueFactory(new PropertyValueFactory<>("spareId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCount.setCellValueFactory(new PropertyValueFactory<>("count"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String spareId = lblSpareId.getText();

        try {
            boolean isDeleted = spareBO.deleteSpares(spareId);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Spare is Deleted successfully...!").show();
                clearFields();
            }

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String spareId = lblSpareId.getText();
        String name = txtName.getText();
        int count = Integer.parseInt(txtCount.getText());
        double price = Double.parseDouble(txtPrice.getText());
        String supplierId = (String) cmbSupplierId.getValue();

        SparesDTO spares = new SparesDTO(spareId, name, count, price, supplierId);
        try {
            boolean isSaved = spareBO.saveSpares(spares);
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "New Spare is Saved successfully...!").show();
                clearFields();
            }

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String spareId = lblSpareId.getText();
        String name = txtName.getText();
        int count = Integer.parseInt(txtCount.getText());
        double price = Double.parseDouble(txtPrice.getText());
        String supplierId = (String) cmbSupplierId.getValue();

        SparesDTO spares = new SparesDTO(spareId, name, count, price, supplierId);
        try {
            boolean isUpdated = spareBO.updateSpares(spares);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Spare is Updated successfully...!").show();
                clearFields();
            }

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void imgBackOnAction(MouseEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/dashboardForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) SpareRoot.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String id = txtSearch.getText();

        try {
            SparesDTO spares = spareBO.searchBySpareId(id);
            if (spares != null) {
                lblSpareId.setText(spares.getSpareId());
                txtName.setText(spares.getName());
                txtCount.setText(String.valueOf(spares.getCount()));
                txtPrice.setText(String.valueOf(spares.getPrice()));
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Spare not found!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        txtName.setText("");
        txtCount.setText("");
        txtPrice.setText("");
    }

    private void getSupplierId(){
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = spareBO.getSpareId();

            for (String code : idList) {
                obList.add(code);
            }
            cmbSupplierId.setItems(obList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

