package lk.Ijse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Launcher extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //AnchorPane root = FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"));
        AnchorPane root = FXMLLoader.load(getClass().getResource("/view/dashboardForm.fxml")); // login page eke idn load wela enn on nm meka ain kranna
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}