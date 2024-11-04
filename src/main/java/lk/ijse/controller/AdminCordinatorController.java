package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminCordinatorController {

    @FXML
    private Button addbtn;

    @FXML
    private Button back;

    @FXML
    private Button btnregister;

    @FXML
    private Button paymentbtn;

    @FXML
    void addstudentbtn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/CoStudent.fxml"));
        Scene scene1 = new Scene(root);
        Stage stage1 = (Stage) addbtn.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.setTitle("Courses Form");
        stage1.centerOnScreen();
    }

    @FXML
    void backonaction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/LoginPage.fxml"));
        Scene scene1 = new Scene(root);
        Stage stage1 = (Stage) back.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.setTitle("Courses Form");
        stage1.centerOnScreen();
    }

    @FXML
    void registeraction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/coregisterform.fxml"));
        Scene scene1 = new Scene(root);
        Stage stage1 = (Stage) btnregister.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.setTitle("Courses Form");
        stage1.centerOnScreen();

    }

    public void addpaymenttbtn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/CoPayment.fxml"));
        Scene scene1 = new Scene(root);
        Stage stage1 = (Stage) paymentbtn.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.setTitle("Courses Form");
        stage1.centerOnScreen();
    }
}
