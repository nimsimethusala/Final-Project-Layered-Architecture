package lk.Ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.Ijse.bo.*;
import lk.Ijse.bo.impl.*;
import lk.Ijse.dto.JobDTO;
import lk.Ijse.dto.JobDetailDTO;
import lk.Ijse.dto.PlaceJobDTO;
import lk.Ijse.entity.Job;
import lk.Ijse.view.tm.JobTm;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class JobFormController implements Initializable {
    @FXML
    public TableColumn colItemName;

    @FXML
    public TableColumn colItemCount;

    @FXML
    public TableColumn colSpareName;

    @FXML
    public Label lblJobId;

    @FXML
    public TextField txtModel;

    @FXML
    public TextField txtItemCount;

    @FXML
    public TableColumn colDefectDescription;

    @FXML
    public TextField txtSpareCount;

    @FXML
    public TableColumn colSpareCount;

    @FXML
    public TableColumn colEmployeeCost;

    @FXML
    public JFXComboBox cmbEmployeeId;

    @FXML
    private AnchorPane JobRoot;

    @FXML
    private JFXComboBox lblCustomerId;

    @FXML
    private Label lblCustomerName;

    @FXML
    private JFXComboBox lblDefectId;

    @FXML
    private JFXComboBox lblItemId;

    @FXML
    private Label lblJobDate;

    @FXML
    private Label lblName;

    @FXML
    private JFXComboBox lblSpareId;

    @FXML
    private TableColumn<?, ?> colCustomerName;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableView<JobTm> tblJob;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colVehicleModel;

    private ObservableList<JobTm> obList = FXCollections.observableArrayList();

    public CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    public ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);

    public SpareBO spareBO = (SpareBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SPARE);

    public DefectBO defectBO = (DefectBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.DEFECT);

    public EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);

    public PlaceJobBO placeJobBO = (PlaceJobBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PLACE_JOB);

    @FXML
    void btnNewCustomerOnAction(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/customerForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) JobRoot.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void getCustomerId(){
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = customerBO.getCustomerId();

            for (String code : idList) {
                obList.add(code);
            }
            lblCustomerId.setItems(obList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void getItemId(){
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = itemBO.getItemId();

            for (String code : idList) {
                obList.add(code);
            }
            lblItemId.setItems(obList);

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void getSpareId(){
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = spareBO.getSpareId();

            for (String code : idList) {
                obList.add(code);
            }
            lblSpareId.setItems(obList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void getDefectId(){
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = defectBO.getDefectId();

            for (String code : idList) {
                obList.add(code);
            }
            lblDefectId.setItems(obList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void getEmployeeId(){
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = employeeBO.getEmployeeId();

            for (String code : idList) {
                obList.add(code);
            }
            cmbEmployeeId.setItems(obList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        lblJobDate.setText(String.valueOf(now));
    }

    @FXML
    void btnNewDefectOnAction(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/defectForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) JobRoot.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnNewItemOnAction(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/itemForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) JobRoot.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnNewSpareOnAction(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/spareForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) JobRoot.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void btnAddToJobOnAction(ActionEvent actionEvent) {
        String itemId = (String) lblItemId.getValue();
        Date date = Date.valueOf(lblJobDate.getText());
        String cusId = (String) lblCustomerId.getValue();
        String model = txtModel.getText();
        String spareId = (String) lblSpareId.getValue();
        String defectId = (String) lblDefectId.getValue();
        int itemCount = Integer.parseInt(txtItemCount.getText());
        int SpareCount = Integer.parseInt(txtSpareCount.getText());
        String empId = (String) cmbEmployeeId.getValue();

        try {
            String itemName = itemBO.getItemName(itemId);
            String cusName = customerBO.getCustomerName(cusId);
            String spareName = spareBO.getSpareName(spareId);
            String desc = defectBO.getDefectDescription(defectId);
            double empCost = employeeBO.getEmployeeCost(empId);

            JFXButton btnRemove = new JFXButton("Remove");
            btnRemove.setCursor(Cursor.HAND);

            btnRemove.setOnAction((e) -> {
                ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

                Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

                if(type.orElse(no) == yes) {
                    int selectedIndex = tblJob.getSelectionModel().getSelectedIndex();
                    obList.remove(selectedIndex);

                    tblJob.refresh();
                }
            });

            if (itemName.equals(null)){
                btnNewItemOnAction(actionEvent);
            }

            if (cusName.equals(null)){
                btnNewCustomerOnAction(actionEvent);
            }

            if (spareName.equals(null)){
                btnNewSpareOnAction(actionEvent);
            }

            if (desc.equals(null)){
                btnNewDefectOnAction(actionEvent);
            }

            JobTm jobTm = new JobTm(date, model, cusName, itemName, itemCount, spareName, SpareCount, empCost, desc, btnRemove);
            obList.add(jobTm);

            tblJob.setItems(obList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnPlaceJobOnAction(ActionEvent actionEvent) {
        String jobId = lblJobId.getText();
        String itemId = (String) lblItemId.getValue();
        Date date = Date.valueOf(lblJobDate.getText());
        String cusId = (String) lblCustomerId.getValue();
        String model = txtModel.getText();
        String spareId = (String) lblSpareId.getValue();
        String defectId = (String) lblDefectId.getValue();
        int itemCount = Integer.parseInt(txtItemCount.getText());
        int spareCount = Integer.parseInt(txtSpareCount.getText());
        String empId = (String) cmbEmployeeId.getValue();

        try {
            String spareName = spareBO.getSpareName(spareId);
            String desc = defectBO.getDefectDescription(defectId);
            String empName = employeeBO.getEmployeeName(empId);
            double empCost = employeeBO.getEmployeeCost(empId);

            JobDTO job = new JobDTO(jobId, model, date, cusId, defectId, desc, itemCount, spareId, spareName, spareCount, empId, empCost, empName);
            List<JobDetailDTO> list = new ArrayList<>();

            for (int i = 0; i < tblJob.getItems().size(); i++) {
                JobTm tm = obList.get(i);

                JobDetailDTO jobDetailDTO = new JobDetailDTO(itemId, tm.getItemCount(), tm.getVehicleModel(), jobId, tm.getSpareCount());
                list.add(jobDetailDTO);

                PlaceJobDTO placeJobDTO = new PlaceJobDTO(new Job(
                        job.getJobId(),
                        job.getModel(),
                        job.getDate(),
                        job.getCustomerId(),
                        job.getDefectId(),
                        job.getDefectName(),
                        job.getItemCount(),
                        job.getSpareId(),
                        job.getSpareName(),
                        job.getSpareCount(),
                        job.getEmpId(),
                        job.getEmpCost(),
                        job.getEmpName()
                ), list);

                boolean isPlaced = placeJobBO.placeOrder(placeJobDTO);

                if (isPlaced) {
                    new Alert(Alert.AlertType.CONFIRMATION, "New Job is placed Successfully!").show();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Job Placed Unsuccessfully!").show();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/dashboardForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) JobRoot.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory(){
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colVehicleModel.setCellValueFactory(new PropertyValueFactory<>("vehicleModel"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colItemCount.setCellValueFactory(new PropertyValueFactory<>("itemCount"));
        colSpareName.setCellValueFactory(new PropertyValueFactory<>("spareName"));
        colSpareCount.setCellValueFactory(new PropertyValueFactory<>("spareCount"));
        colEmployeeCost.setCellValueFactory(new PropertyValueFactory<>("empCost"));
        colDefectDescription.setCellValueFactory(new PropertyValueFactory<>("defectDescription"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnAction"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setNextJobId();
        setDate();
        getCustomerId();
        getItemId();
        getSpareId();
        getDefectId();
        getEmployeeId();
        setCellValueFactory();
    }

    private void setNextJobId() {
        try {
            String nextJobId =  placeJobBO.generateNextId();
            lblJobId.setText(nextJobId);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCustomerName(){
        String cusId = (String) lblCustomerId.getValue();

        try {
            String cusName = customerBO.getCustomerName(cusId);
            lblCustomerName.setText(cusName);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setItemName(){
        String itemId = (String) lblItemId.getValue();

        try {
            String itemName = itemBO.getItemName(itemId);
            lblName.setText(itemName);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        txtModel.setText("");
        txtSpareCount.setText("");
        txtItemCount.setText("");
    }

    public void cmbCustomerIdOnAction(ActionEvent actionEvent) {
        setCustomerName();
    }

    public void lblItemIdOnAction(ActionEvent actionEvent) {
        setItemName();
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }
}
