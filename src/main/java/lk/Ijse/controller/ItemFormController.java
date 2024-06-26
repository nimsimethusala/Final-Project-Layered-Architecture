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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.Ijse.bo.DefectBO;
import lk.Ijse.bo.ItemBO;
import lk.Ijse.bo.impl.DefectBoImpl;
import lk.Ijse.bo.impl.ItemBoImpl;
import lk.Ijse.dto.ItemDTO;
import lk.Ijse.util.Regex;
import lk.Ijse.util.TextFeildRegex;
import lk.Ijse.view.tm.ItemTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ItemFormController {
    @FXML
    public TableColumn colJobId;

    @FXML
    public JFXComboBox cmbDefectId;

    @FXML
    public Label lblItemId;
    @FXML
    public TextField txtCount;

    @FXML
    private AnchorPane itemRoot;

    @FXML
    private TableView<ItemTm> tblItem;

    @FXML
    private TableColumn<?, ?> colItemId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TextField txtItemCount;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSearch;

    public ItemBO itemBO = new ItemBoImpl();

    public DefectBO defectBO = new DefectBoImpl();

    public void initialize(){
        getCurrentItemId();
        getDefectId();
        setCellValueFactory();
        loadAllCustomer();
    }

    private void getCurrentItemId() {
        try {
            String nextOrderId = itemBO.generateNextItemId();
            lblItemId.setText(nextOrderId);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFeilds();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String itemId = lblItemId.getText();

        try {
            boolean isDeleted = itemBO.deleteItem(itemId);

            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Item is deleted successfully...!");
                clearFeilds();
            }

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String itemId = lblItemId.getText();
        String name = txtName.getText();
        int itemCount = Integer.parseInt(txtCount.getText());
        String defectId = (String) cmbDefectId.getValue();

        ItemDTO item = new ItemDTO(itemId, name, itemCount, defectId);
        try {
            boolean isSaved = itemBO.saveItem(item);
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "New Item is saved SuccessFully...!").show();
                clearFeilds();
            }

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String itemId = lblItemId.getText();
        String itemName = txtName.getText();
        int itemCount = Integer.parseInt(txtCount.getText());
        String defectId = (String) cmbDefectId.getValue();

        try {
            ItemDTO item = new ItemDTO(itemId, itemName, itemCount, defectId);

            boolean isUpdate = itemBO.updateItem(item);

            if (isUpdate){
                new Alert(Alert.AlertType.INFORMATION, "Item is Updated....!").show();
                clearFeilds();
            }
        }catch (SQLException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String id = txtSearch.getText();

        try {
            ItemDTO item = itemBO.searchByItemId(id);
            if (item != null) {
                lblItemId.setText(item.getItemID());
                txtName.setText(item.getItemName());
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Item not found!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void imgBackOnAction(MouseEvent mouseEvent) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/dashboardForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) itemRoot.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearFeilds(){
        lblItemId.setText("");
        txtName.setText("");
    }

    private void getDefectId(){
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = defectBO.getDefectId();

            for (String code : idList) {
                obList.add(code);
            }
            cmbDefectId.setItems(obList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllCustomer(){
        ObservableList<ItemTm> obList = FXCollections.observableArrayList();

        try {
            List<ItemDTO> itemList = itemBO.getAllItems();
            for (ItemDTO item : itemList){
                ItemTm itemTm = new ItemTm(
                        item.getItemID(),
                        item.getItemName(),
                        item.getDefectId()
                );
                obList.add(itemTm);
            }
            tblItem.setItems(obList);

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void setCellValueFactory(){
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colJobId.setCellValueFactory(new PropertyValueFactory<>("defectId"));
    }

    public void txtItemNameOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFeildRegex.NAME,txtName);
    }
}
