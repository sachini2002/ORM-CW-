package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BoFactory;
import lk.ijse.bo.BoTypes;
import lk.ijse.bo.StudentBo;

import lk.ijse.dto.StudentDTO;

import lk.ijse.dto.tm.StudentTm;
import lk.ijse.entity.User;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class StudetnFormController implements Initializable {

    @FXML
    private TextField studentId;

    @FXML
    private Button btnback;

    @FXML
    private TextField studentName;

    @FXML
    private TextField Email;

    @FXML
    private TextField address;

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private TableColumn<StudentTm, String> corid;

    @FXML
    private TableColumn<StudentTm, String > colName;

    @FXML
    private TableColumn<StudentTm, String> coladdress;

    @FXML
    private TableColumn<StudentTm, String> colcontact;

    @FXML
    private TableColumn<StudentTm, String> colemail;

    @FXML
    private TableColumn<String, Integer> colid;

    @FXML
    private ComboBox<User> cmbCoId; // Change to hold User objects

    @FXML
    private TextField contact;

    @FXML
    private TableView<StudentTm> tblStudent;

    StudentBo studentBo = (StudentBo) BoFactory.getBoFactory().getBo(BoTypes.Student);

    @FXML
    void SaveOnAction(ActionEvent event) {
        try {
            int studentId = validateStudentId();
            if (studentId == -1) return;

            String studentName = this.studentName.getText();
            String address = this.address.getText();
            String contact = this.contact.getText();
            String email = this.Email.getText();
            User selectedCoordinator = this.cmbCoId.getSelectionModel().getSelectedItem();

            if (selectedCoordinator == null) {
                showAlert("Input Error", "Please select a coordinator.", Alert.AlertType.WARNING);
                return;
            }

            // Create a new StudentDTO instance
            StudentDTO studentDTO = new StudentDTO(studentId, studentName, address, contact, email, selectedCoordinator);
            boolean isAdded = studentBo.addStudent(studentDTO);

            if (isAdded) {
                showAlert("Success", "Student added successfully!", Alert.AlertType.INFORMATION);
                loadStudentTable(); // Reload the table after successful addition
            } else {
                showAlert("Failure", "Failed to add the student. Please try again.", Alert.AlertType.ERROR);
            }

        } catch (Exception e) {
            showAlert("Error", "An unexpected error occurred: " + e.getMessage(), Alert.AlertType.ERROR);

        }
    }

    private int validateStudentId() {
        String studentIdText = this.studentId.getText();
        if (studentIdText.isEmpty()) {
            System.out.println("Student ID cannot be empty.");
            return -1;
        }

        try {
            return Integer.parseInt(studentIdText);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Student ID. Please enter a valid number.");
            return -1;
        }
    }





    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/AdminDash.fxml"));
        Scene scene1 = new Scene(root);
        Stage stage1 = (Stage) btnback.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.setTitle("Courses Form");
        stage1.centerOnScreen();
    }

    @FXML
    void clearOnAction(ActionEvent event) {
        this.studentId.clear();
        this.studentName.clear();
        this.address.clear();
        this.contact.clear();
        this.Email.clear();
        this.cmbCoId.getSelectionModel().clearSelection();

    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        int studentIdText = Integer.parseInt(this.studentId.getText());


        try {
            boolean b = studentBo.deleteStudent(studentIdText);
            if (b == true) {
                // Create an alert for student update confirmation
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Delete Successful");
                alert.setHeaderText(null); // You can set a header text if you want
                alert.setContentText("Student information has been Deleted successfully!");

                // Show the alert and wait for the user to respond
                alert.showAndWait();
                clearFields();
                loadStudentTable();

            }else {
                showAlert("Student not found", "No student found with the given ID.", Alert.AlertType.WARNING);
            }

        }catch (Exception e){
            showAlert("Student not found", "No student found with the given ID.", Alert.AlertType.WARNING);
        }
    }


    @FXML
    void searchOnAction(ActionEvent event) {
        try {
            int studentIdText = Integer.parseInt(this.studentId.getText());

            // Call the search method from the business object
            StudentDTO studentDTO = studentBo.searchStudent(studentIdText);

            if (studentDTO != null) {
                // Populate the fields with the retrieved student information
                this.studentId.setText(String.valueOf(studentDTO.getStudentId()));
                this.studentName.setText(studentDTO.getStudentName());
                this.address.setText(studentDTO.getStudentAddress());
                this.contact.setText(studentDTO.getStudentPhone());
                this.Email.setText(studentDTO.getStudentEmail());
                this.cmbCoId.getSelectionModel().select(studentDTO.getUserid());
            } else {
                // Clear the fields and show an alert if no student was found
                clearFields();
                showAlert("Student not found", "No student found with the given ID.", Alert.AlertType.WARNING);
            }
        } catch (NumberFormatException e) {
            // Handle invalid input
            showAlert("Invalid Input", "Please enter a valid student ID.", Alert.AlertType.ERROR);
        } catch (Exception e) {
            // Handle any other exceptions
            e.printStackTrace();
            showAlert("Student not found", "No student found with the given ID.", Alert.AlertType.WARNING);
        }
    }

    // Helper method to clear all fields
    private void clearFields() {
        this.studentId.clear();
        this.studentName.clear();
        this.address.clear();
        this.contact.clear();
        this.Email.clear();
        this.cmbCoId.getSelectionModel().clearSelection();
    }

    // Helper method to show alerts
    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    @FXML
    void updateOnAction(ActionEvent event) {
        int studentIdText = Integer.parseInt(this.studentId.getText());
        String studentName = this.studentName.getText();
        String address = this.address.getText();
        String contact = this.contact.getText();
        String email = this.Email.getText();

        User selectedCoordinator = this.cmbCoId.getSelectionModel().getSelectedItem();

        try {

            StudentDTO studentDTO = new StudentDTO(studentIdText,studentName,address,contact,email,selectedCoordinator);

            boolean b = studentBo.updateStudent(studentDTO);

            if (b == true) {
                // Create an alert for student update confirmation
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Update Successful");
                alert.setHeaderText(null); // You can set a header text if you want
                alert.setContentText("Student information has been updated successfully!");

                // Show the alert and wait for the user to respond
                alert.showAndWait();
                loadStudentTable();
            }

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cmbCordinator();
        loadStudentTable();
        setCellValueFactory();

    }

    private void cmbCordinator() {
        List<User> users = studentBo.getUserIds(); // Adjust this method to return List<User>
        cmbCoId.getItems().addAll(users);
    }

//    private void loadStudent(){
//        try {
//
//            tblStudent.getItems().clear();
//            List<StudentDTO> studentList = studentBo.loadTable();
//            ObservableList<StudentTm> tableItems = studentList.stream()
//                    .map(student -> new StudentTm(
//                            student.getStudentId(),
//                            student.getStudentName(),
//                            student.getStudentAddress(),
//                            student.getStudentPhone(),
//                            student.getStudentEmail(),
//                            student.getUserid()))
//                    .collect(Collectors.toCollection(FXCollections::observableArrayList));
//
//            tblStudent.getItems().setAll(tableItems);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    private void loadStudentTable() {
        try {
            tblStudent.getItems().clear();
            List<StudentDTO> studentList = studentBo.loadTable();

            System.out.println(studentList.size());

            for (StudentDTO studentDTO : studentList) {
                StudentTm studentTm = new StudentTm(studentDTO.getStudentId(), studentDTO.getStudentName(), studentDTO.getStudentAddress(), studentDTO.getStudentPhone(), studentDTO.getStudentEmail(), studentDTO.getUserid());
                tblStudent.getItems().add(studentTm);

            }



        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load student table.", Alert.AlertType.ERROR);
        }
    }


    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        coladdress.setCellValueFactory(new PropertyValueFactory<>("studentAddress"));
        colcontact.setCellValueFactory(new PropertyValueFactory<>("studentPhone"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("studentEmail"));
        corid.setCellValueFactory(new PropertyValueFactory<>("userid"));

    }
}
