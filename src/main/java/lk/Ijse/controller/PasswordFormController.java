package lk.Ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.Ijse.bo.BOFactory;
import lk.Ijse.bo.LoginBO;
import lk.Ijse.dao.SQLUtil;

import java.io.IOException;
import java.sql.SQLException;

public class PasswordFormController {

    @FXML
    private AnchorPane Root;

    @FXML
    private AnchorPane rootAnchorpane;

    @FXML
    private TextField txtConfirmPassword;

    @FXML
    private TextField txtNewPassword;

    LoginBO loginBO = (LoginBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    public void initialize(){
        txtNewPassword.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtConfirmPassword.requestFocus();
            }
        });
    }

    @FXML
    void btnConfirmOnAction(ActionEvent event) {
        String cpw = txtConfirmPassword.getText();
        String npw = txtNewPassword.getText();

        boolean isUpdate = update(cpw, npw);

        if (isUpdate){
            new Alert(Alert.AlertType.INFORMATION,"New password is updated...!").show();
            try {
                AnchorPane root = FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) rootAnchorpane.getScene().getWindow();
                stage.setScene(scene);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }else {
            txtConfirmPassword.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            new animatefx.animation.Shake(txtConfirmPassword).play();
        }
    }

    public boolean update(String cpw, String npw) {
        //String sql = "UPDATE credential SET password = ? WHERE username = 'nimsi'";

        try {
            boolean update = loginBO.update(cpw, npw);

            if (update){
                /*Connection connection = DbConnection.getInstance().getConnection();
                PreparedStatement pstm = connection.prepareStatement(sql);
                pstm.setObject(1, cpw);

                return pstm.executeUpdate() > 0;*/
                return SQLUtil.execute("UPDATE credential SET password = ? WHERE username = 'nimsi'", cpw);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public void btnBackButtonOnAction(MouseEvent mouseEvent) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) rootAnchorpane.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
