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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.Ijse.bo.BOFactory;
import lk.Ijse.bo.PaymentBO;
import lk.Ijse.bo.impl.PaymentBoImpl;
import lk.Ijse.db.DbConnection;
import lk.Ijse.dto.PaymentDTO;
import lk.Ijse.view.tm.PaymentTm;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentFormController {
    @FXML
    public TableColumn colTotalPayment;

    @FXML
    private JFXComboBox cmbJobId;

    @FXML
    private TableColumn<?, ?> colDefectTotalCost;

    @FXML
    private TableColumn<?, ?> colEmployeeTotalCost;

    @FXML
    private TableColumn<?, ?> colJobId;

    @FXML
    private TableColumn<?, ?> colPaymentId;

    @FXML
    private TableColumn<?, ?> colSpareTotalCost;

    @FXML
    private Label lblPaymentId;

    @FXML
    private AnchorPane paymentRoot;

    @FXML
    private TableView<PaymentTm> tblPayment;

    PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);

    public void initialize(){
        setCellValueFactory();
        loadAllPayments();
        getCurrentCustomerId();
        getJobId();
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String jobId = (String) cmbJobId.getValue();
        String paymentId = lblPaymentId.getText();

        try {
            double defectTotalCost = paymentBO.getTotalDefectCost(jobId);
            double employeeTotalCost = paymentBO.getTotalEmployeeCost(jobId);
            double spareTotalCost = paymentBO.getTotalSpareCost(jobId);

            System.out.println(defectTotalCost);

            double total = (defectTotalCost + employeeTotalCost + spareTotalCost);

            System.out.println(total);

            ObservableList<PaymentDTO> obList = FXCollections.observableArrayList();

            PaymentDTO payment = new PaymentDTO(paymentId, jobId, defectTotalCost, employeeTotalCost, spareTotalCost, total);

            System.out.println(total);
            boolean isPaymentPlaced = paymentBO.savePayment(payment);


            if (isPaymentPlaced){
                new Alert(Alert.AlertType.CONFIRMATION, "New Payment is placed successfully...!").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "New Payment is not placed unsuccessfully...!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            throw new RuntimeException(e);
        }
    }

    private void getJobId(){
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = paymentBO.getPaymentId();

            for (String code : idList) {
                obList.add(code);
            }
            cmbJobId.setItems(obList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnBackOnAction(ActionEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/dashboardForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) paymentRoot.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void getCurrentCustomerId() {
        try {
            String nextPaymentId = paymentBO.generateNextPaymentId();
            lblPaymentId.setText(nextPaymentId);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnReportOnAction(ActionEvent actionEvent) {
        String jobId = (String) cmbJobId.getValue();
        JasperDesign jasperDesign = null;
        try {
            jasperDesign = JRXmlLoader.load("src/main/resources/report/Job_Report.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            Map<String,Object> data = new HashMap<>();
            data.put("JobId",jobId);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, data, DbConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);

        } catch (JRException | SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    private void loadAllPayments(){
        ObservableList<PaymentTm> obList = FXCollections.observableArrayList();

        try {
            List<PaymentDTO> paymentList = paymentBO.getAllPayments();
            for (PaymentDTO payment : paymentList){
                PaymentTm paymentTm = new PaymentTm(
                        payment.getPaymentId(),
                        payment.getJobId(),
                        payment.getDefectTotal(),
                        payment.getEmpTotal(),
                        payment.getSpareTotal(),
                        payment.getTotalCost()
                );
                obList.add(paymentTm);
            }
            tblPayment.setItems(obList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory(){
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colJobId.setCellValueFactory(new PropertyValueFactory<>("jobId"));
        colDefectTotalCost.setCellValueFactory(new PropertyValueFactory<>("defectTotal"));
        colEmployeeTotalCost.setCellValueFactory(new PropertyValueFactory<>("employeeTotal"));
        colSpareTotalCost.setCellValueFactory(new PropertyValueFactory<>("spareTotal"));
        colTotalPayment.setCellValueFactory(new PropertyValueFactory<>("total"));
    }
}