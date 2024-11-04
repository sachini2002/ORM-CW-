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
import lk.ijse.bo.StudentBo;
import lk.ijse.dto.StudentDTO;
import lk.ijse.dto.tm.StudentTm;
import lk.ijse.entity.User;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CordinatorStudentController implements Initializable {

    @FXML
    private TextField Email;

    @FXML
    private TextField address;

    @FXML
    private TextField contact;


    @FXML
    private TextField studentId;

    @FXML
    private TextField studentName;

    @FXML
    private Button btnback;

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
    private ComboBox<User> cmbCoId;


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

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/AdminCordinator.fxml"));
        Scene scene1 = new Scene(root);
        Stage stage1 = (Stage) btnback.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.setTitle("Courses Form");
        stage1.centerOnScreen();
    }

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       loadStudentTable();
       setCellValueFactory();
       cmbCordinator();
    }
    private void cmbCordinator() {
        List<User> users = studentBo.getUserIds(); // Adjust this method to return List<User>
        cmbCoId.getItems().addAll(users);
    }
}
