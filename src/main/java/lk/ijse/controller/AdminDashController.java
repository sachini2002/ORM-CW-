package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminDashController {

    @FXML
    private Button logout;
    @FXML
    private AnchorPane CourseCountPane;

    @FXML
    private AnchorPane MainPain;

    @FXML
    private AnchorPane StudentCountPane;

    @FXML
    private AnchorPane UserCountPane;

    @FXML
    private Button coursebtn;

    @FXML
    private Button registrationbtn;

    @FXML
    private AnchorPane slidePane;

    @FXML
    private Button paymentbtn;


    @FXML
    private Button stbtn;

    @FXML
    private Button userbtn;

    @FXML
    void StudentOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/StudentForm.fxml"));
        Scene scene1 = new Scene(root);
        Stage stage1 = (Stage) stbtn.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.setTitle("Student Form");
        stage1.centerOnScreen();

    }

    @FXML
    void coursesOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/CoursesForm.fxml"));
        Scene scene1 = new Scene(root);
        Stage stage1 = (Stage) coursebtn.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.setTitle("Courses Form");
        stage1.centerOnScreen();
    }

    @FXML
    void registrationOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/RegistrationUI.fxml"));
        Scene scene1 = new Scene(root);
        Stage stage1 = (Stage) registrationbtn.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.setTitle("Courses Form");
        stage1.centerOnScreen();
    }

    @FXML
    void usersOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/UserRegisterForm.fxml"));
        Scene scene1 = new Scene(root);
        Stage stage1 = (Stage) userbtn.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.setTitle("Courses Form");
        stage1.centerOnScreen();

    }

    public void logoutOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/LoginPage.fxml"));
        Scene scene1 = new Scene(root);
        Stage stage1 = (Stage) logout.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.setTitle("Courses Form");
        stage1.centerOnScreen();
    }

    public void btnpaymentclick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/paymentForm.fxml"));
        Scene scene1 = new Scene(root);
        Stage stage1 = (Stage) paymentbtn.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.setTitle("Courses Form");
        stage1.centerOnScreen();
    }
}



