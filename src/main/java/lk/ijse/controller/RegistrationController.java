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
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {

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
    private TableColumn<RegistrationTm, Double> paymentId;

    @FXML
    private TableView<RegistrationTm> tblregistration;

    RegistrationBo registrationBo = (RegistrationBo) BoFactory.getBoFactory().getBo(BoTypes.Registration);

    @FXML
    void btnbackonaction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/AdminDash.fxml"));
        Scene scene1 = new Scene(root);
        Stage stage1 = (Stage) btnback.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.setTitle("Courses Form");
        stage1.centerOnScreen();

    }

//    @FXML
//    void btnsaveaction(ActionEvent event) {
//
//        String regId = registrationid.getText();
//        double payment = Double.parseDouble(this.payment.getText());
//        LocalDate selectedDate = datapicker.getValue();
//        Courses course = cmbcourseid.getSelectionModel().getSelectedItem();  // assuming this gives a Courses object
//        Student student = cmbstudentid.getSelectionModel().getSelectedItem();  // assuming this gives a Student object
//        RegistrationDTO registrationDTO = new RegistrationDTO(regId, payment, selectedDate, course, student);
//        System.out.println("DTO created: " + registrationDTO);
//        boolean b = registrationBo.saveRegistration(registrationDTO);
//        if (b){
//            System.out.println("Registration saved");
//        }
//    }

    @FXML
    void btnsaveaction(ActionEvent event) {
        try {
            String regId = registrationid.getText();
            double payment = Double.parseDouble(this.payment.getText());
            LocalDate selectedDate = datapicker.getValue();

            // Check if course and student are selected
            Courses course = cmbcourseid.getSelectionModel().getSelectedItem();
            Student student = cmbstudentid.getSelectionModel().getSelectedItem();
            Payment payment1 = cmbPaymentID.getSelectionModel().getSelectedItem();


            if (course == null) {
                showAlert("Error", "Please select a course.");
                return;
            }

            if (student == null) {
                showAlert("Error", "Please select a student.");
                return;
            }


            RegistrationDTO registrationDTO = new RegistrationDTO(regId, payment, selectedDate, course, student,payment1);
            System.out.println("DTO created: " + registrationDTO);

            boolean b = registrationBo.saveRegistration(registrationDTO);
            if (b) {
                System.out.println("Registration saved");
                loadRegistrationTable();
                showAlert("Success", "Registration saved successfully.");
            } else {
                showAlert("Error", "Failed to save registration.");
            }

        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid payment amount. Please enter a valid number.");
        } catch (Exception e) {
            showAlert("Error", "An unexpected error occurred: " + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    @FXML
    void clearaction(ActionEvent event) {
        registrationid.setText("");
        payment.setText("");
        datapicker.setValue(null);


    }

    @FXML
    void deleteaction(ActionEvent event) {
        String id = registrationid.getText();


        if (id.isEmpty()) {
            showAlert("Error", "Please enter a registration ID to delete.");
            return;
        }


        boolean success = registrationBo.deleteRegistration(id);

        if (success) {
            showAlert("Success", "Registration with ID: " + id + " has been successfully deleted.");
            loadRegistrationTable();

            clearaction(event);
        } else {
            showAlert("Error", "Failed to delete the registration. Please check the ID and try again.");
        }
    }


    @FXML
    void searchaction(ActionEvent event) {
        String id = registrationid.getText();

        // Check if the ID field is empty
        if (id.isEmpty()) {
            showAlert("Error", "Please enter a registration ID to search.");
            return; // Exit if the ID is empty
        }

        // Attempt to find the registration based on the ID
        RegistrationDTO registrationDTO = registrationBo.searchRegistrations(id);

        if (registrationDTO != null) {
            // Populate fields with the found registration details
            this.registrationid.setText(String.valueOf(registrationDTO.getRegistrationId()));
            this.payment.setText(String.valueOf(registrationDTO.getAdvanced()));
            this.datapicker.setValue(registrationDTO.getDate());

            // Set the selected course and student in the combo boxes
            cmbcourseid.getSelectionModel().select(registrationDTO.getCourses());
            cmbstudentid.getSelectionModel().select(registrationDTO.getStudent());

            System.out.println("Registration found: " + registrationDTO);
        } else {
            // Show an error alert if the registration is not found
            showAlert("Error", "Registration not found for ID: " + id);
        }
    }



    @FXML
    void updateonaction(ActionEvent event) {
        String regId = registrationid.getText();
        if (regId.isEmpty()) {
            showAlert("Error", "Please enter a registration ID to update.");
            return;
        }

        double payment;
        try {
            payment = Double.parseDouble(this.payment.getText());
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid payment amount. Please enter a valid number.");
            return;
        }

        LocalDate selectedDate = datapicker.getValue();
        if (selectedDate == null) {
            showAlert("Error", "Please select a date.");
            return;
        }

        Courses course = cmbcourseid.getSelectionModel().getSelectedItem();
        Student student = cmbstudentid.getSelectionModel().getSelectedItem();
        Payment payment1 = cmbPaymentID.getSelectionModel().getSelectedItem();

        if (course == null || student == null) {
            showAlert("Error", "Please select both a course and a student.");
            return;
        }

        boolean updateSuccessful = registrationBo.updateRegistration(new RegistrationDTO(regId, payment, selectedDate, course, student,payment1));
        loadRegistrationTable();
        showAlert(updateSuccessful ? "Success" : "Error", updateSuccessful ? "Registration updated successfully." : "Failed to update registration for ID: " + regId);
    }


    private void cmbCourseId(){
        List<Courses> courseId = registrationBo.getCourseId();
        cmbcourseid.getItems().addAll(courseId);


    }

    private void cmbStudentId(){
        List<Student> studentId = registrationBo.getStudentId();
        cmbstudentid.getItems().addAll(studentId);
    }

    private void paymentId(){
        List<Payment> paymentID = registrationBo.getPaymentID();
        cmbPaymentID.getItems().addAll(paymentID);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbCourseId();
        cmbStudentId();
        loadRegistrationTable();
        setCellValueFactory();
        paymentId();
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
        paymentId.setCellValueFactory(new PropertyValueFactory<>("payment"));

    }

}
