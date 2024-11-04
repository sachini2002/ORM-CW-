package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.bo.BoFactory;
import lk.ijse.bo.BoTypes;
import lk.ijse.bo.RegistrationBo;
import lk.ijse.dto.RegistrationDTO;
import lk.ijse.dto.tm.RegistrationTm;
import lk.ijse.entity.Courses;
import lk.ijse.entity.Payment;
import lk.ijse.entity.Student;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class CoRegisterForm implements Initializable {

    @FXML
    private ComboBox<Payment> cmbPaymentID;
    @FXML
    private ComboBox<Courses> cmbcourseid;

    @FXML
    private ComboBox<Student> cmbstudentid;

    @FXML
    private TableColumn<RegistrationTm, Double> coladvanced;

    @FXML
    private TableColumn<RegistrationTm, Courses> colcourseid;

    @FXML
    private TableColumn<RegistrationTm, LocalDate> coldate;

    @FXML
    private TableColumn<RegistrationTm, String> colid;

    @FXML
    private TableColumn<RegistrationTm, Student> colstudent;

    @FXML
    private DatePicker datapicker;

    @FXML
    private TextField payment;

    @FXML
    private TextField registrationid;

    @FXML
    private Button btnback;

    @FXML
    private TableView<RegistrationTm> tblregistration;

    RegistrationBo registrationBo = (RegistrationBo) BoFactory.getBoFactory().getBo(BoTypes.Registration);

    @FXML
    void btnbackonaction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/AdminCordinator.fxml"));
        Scene scene1 = new Scene(root);
        Stage stage1 = (Stage) btnback.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.setTitle("Courses Form");
        stage1.centerOnScreen();
    }

    private void cmbCourseId(){
        List<Courses> courseId = registrationBo.getCourseId();
        cmbcourseid.getItems().addAll(courseId);


    }

    private void cmbStudentId(){
        List<Student> studentId = registrationBo.getStudentId();
        cmbstudentid.getItems().addAll(studentId);
    }

   // @FXML
//    void btnsaveaction(ActionEvent event) {
//        try {
//            String regId = registrationid.getText();
//            double payment = Double.parseDouble(this.payment.getText());
//            LocalDate selectedDate = datapicker.getValue();
//
//            // Check if course and student are selected
//            Courses course = cmbcourseid.getSelectionModel().getSelectedItem();
//            Student student = cmbstudentid.getSelectionModel().getSelectedItem();
//            Payment payment1 = cmbPaymentID.getSelectionModel().getSelectedItem();
//
//
//            if (course == null) {
//                showAlert("Error", "Please select a course.");
//                return;
//            }
//
//            if (student == null) {
//                showAlert("Error", "Please select a student.");
//                return;
//            }
//
//            RegistrationDTO registrationDTO = new RegistrationDTO(regId, payment, selectedDate, course, student,payment1);
//            System.out.println("DTO created: " + registrationDTO);
//
//            boolean b = registrationBo.saveRegistration(registrationDTO);
//            if (b) {
//                System.out.println("Registration saved");
//                loadRegistrationTable();
//                showAlert("Success", "Registration saved successfully.");
//            } else {
//                showAlert("Error", "Failed to save registration.");
//            }
//
//        } catch (NumberFormatException e) {
//            showAlert("Error", "Invalid payment amount. Please enter a valid number.");
//        } catch (Exception e) {
//            showAlert("Error", "An unexpected error occurred: " + e.getMessage());
//        }
//    }


   @FXML
   void btnsaveaction(ActionEvent event) {
       try {
           String regId = registrationid.getText();
           double paymentAmount = Double.parseDouble(this.payment.getText());
           LocalDate selectedDate = datapicker.getValue();

           // Check if course and student are selected
           Courses course = cmbcourseid.getSelectionModel().getSelectedItem();
           Student student = cmbstudentid.getSelectionModel().getSelectedItem();
           Payment payment1 = cmbPaymentID.getSelectionModel().getSelectedItem();

           // Check for duplicate registrationId


           // Continue with registration creation
           if (course == null) {
               showAlert("Error", "Please select a course.");
               return;
           }

           if (student == null) {
               showAlert("Error", "Please select a student.");
               return;
           }

           RegistrationDTO registrationDTO = new RegistrationDTO(regId, paymentAmount, selectedDate, course, student, payment1);
           boolean saved = registrationBo.saveRegistration(registrationDTO);
           if (saved) {
               loadRegistrationTable();
               showAlert("Success", "Registration saved successfully.");
           } else {
               showAlert("Error", "Failed to save registration.");
           }

       } catch (NumberFormatException e) {
           showAlert("Error", "Invalid payment amount. Please enter a valid number.");
       } catch (Exception e) {
           showAlert("Error", "An unexpected error occurred: " + e.getMessage());
           e.printStackTrace();
       }
   }



    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void loadRegistrationTable(){
        tblregistration.getItems().clear();
        List<RegistrationDTO> registrationDTOS = registrationBo.loadTable();

        for (RegistrationDTO registrationDTO : registrationDTOS) {
            RegistrationTm registrationTm = new RegistrationTm(registrationDTO.getRegistrationId(), registrationDTO.getAdvanced(), registrationDTO.getDate(), registrationDTO.getCourses(), registrationDTO.getStudent(),registrationDTO.getPayment());
            tblregistration.getItems().add(registrationTm);
        }
    }
    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("registrationId"));
        coladvanced.setCellValueFactory(new PropertyValueFactory<>("advanced"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colcourseid.setCellValueFactory(new PropertyValueFactory<>("courses"));
        colstudent.setCellValueFactory(new PropertyValueFactory<>("student"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadRegistrationTable();
        setCellValueFactory();
        cmbCourseId();
        cmbStudentId();
        paymentId();
    }
    private void paymentId(){
        List<Payment> paymentID = registrationBo.getPaymentID();
        for (Payment payment : paymentID) {
            System.out.println(payment.getPaymentId());

        }
        cmbPaymentID.getItems().addAll(paymentID);
    }

}
