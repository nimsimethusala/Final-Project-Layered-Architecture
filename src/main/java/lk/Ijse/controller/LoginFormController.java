
package lk.Ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.Ijse.dao.SQLUtil;
import lk.Ijse.db.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFormController {
    @FXML
    public Label lblUsername;
    @FXML
    public Label lblPassword;

    @FXML
    private AnchorPane rootLogin;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserId;

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        String password = txtPassword.getText();
        String username = txtUserId.getText();

        checkCredential(password, username);
    }

    private void checkCredential(String password, String username) {
        //String sql = "SELECT username, password from credential where username = ?";

        try {
            /*Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1, username);*/
            ResultSet resultSet = SQLUtil.execute("SELECT username, password from credential where username = ?", username);

            if (resultSet.next()){
                String pw = resultSet.getString("password");

                if (password.equals(pw)){
                    loginDashboard();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Sorry! Your Password is incorrect").show();
                }
            }else {
                new Alert(Alert.AlertType.ERROR,"Sorry! Your Username is incorrect").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loginDashboard() {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/dashboardForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) rootLogin.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void passwordOnAction(MouseEvent event) {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/passwordForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) rootLogin.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
