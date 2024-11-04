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
import lk.ijse.bo.CourseBo;
import lk.ijse.dto.CoursesDTO;
import lk.ijse.dto.tm.CourseTm;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class CourseFormController implements Initializable {

    @FXML
    private TableColumn<CourseTm, Double> colPrice;

    @FXML
    private Button btnback;

    @FXML
    private TableColumn<CourseTm, String> colid;

    @FXML
    private TableColumn<CourseTm, String> colname;

    @FXML
    private TextField courseId;

    @FXML
    private TextField courseName;

    @FXML
    private TextField coursePrice;

    @FXML
    private TextField duaration;

    @FXML
    private TableColumn<CourseTm, String> durationcol;

    @FXML
    private TableView<CourseTm> tblcourse;

    CourseBo courseBo = (CourseBo) BoFactory.getBoFactory().getBo(BoTypes.Course);

    @FXML
    void clearOnAction(ActionEvent event) {
        courseId.clear();
        courseName.clear();
        coursePrice.clear();
        duaration.clear();

    }


    @FXML
    void deleteonaction(ActionEvent event) {
        try {
            String courseIdText = courseId.getText();

            boolean isDeleted = courseBo.deleteCourse(courseIdText);
            loadCourseTable();

            if (isDeleted) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Delete Course");
                alert.setHeaderText(null);
                alert.setContentText("Course deleted successfully.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Delete Course");
                alert.setHeaderText(null);
                alert.setContentText("Failed to delete the course. The course may not exist.");
                alert.showAndWait();
            }

        } catch (Exception e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("An error occurred while deleting the course.");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
            e.printStackTrace();
        }
    }


    @FXML
    void saveOnAction(ActionEvent event) {
        try {
            String courseId = this.courseId.getText();
            String courseName = this.courseName.getText();
            double coursePrice = Double.parseDouble(this.coursePrice.getText());
            String duration = this.duaration.getText(); // Fixed typo from 'duaration' to 'duration'



            CoursesDTO coursesDTO = new CoursesDTO(courseId, courseName, duration, coursePrice);

            boolean isSaved = courseBo.saveCourses(coursesDTO);
            loadCourseTable();

            if (isSaved) {
                showAlert("Save", "Course saved successfully.", Alert.AlertType.INFORMATION);
            } else {
                showAlert("Save", "Course save failed.", Alert.AlertType.ERROR);
            }

        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid input for course price. Please enter a valid number.", Alert.AlertType.ERROR);
        } catch (Exception e) {
            showAlert("Error", "An unexpected error occurred: " + e.getMessage(), Alert.AlertType.ERROR);

        }
    }



    @FXML
    void searchOnAction(ActionEvent event) {
        try {
            String courseId = this.courseId.getText();

            CoursesDTO coursesDTO = courseBo.searchCourse(courseId);
            loadCourseTable();

            if (coursesDTO != null) {
                this.courseName.setText(coursesDTO.getCourseName());
                this.coursePrice.setText(String.valueOf(coursesDTO.getCoursePrice()));
                this.duaration.setText(coursesDTO.getDuration()); // Fixed typo from 'duaration' to 'duration'
            } else {
                showAlert("Search", "No course found with the provided ID.", Alert.AlertType.WARNING);
            }

        } catch (Exception e) {
            showAlert("Error", "An unexpected error occurred: " + e.getMessage(), Alert.AlertType.ERROR);

        }
    }



    @FXML
    void updateOnAction(ActionEvent event) {
        try {
            String courseId = this.courseId.getText();
            String courseName = this.courseName.getText();
            double coursePrice = Double.parseDouble(this.coursePrice.getText());
            String duration = this.duaration.getText(); // Fixed typo from 'duaration' to 'duration'

            CoursesDTO coursesDTO = new CoursesDTO(courseId, courseName, duration, coursePrice);

            boolean isUpdated = courseBo.updateCourse(coursesDTO);
            loadCourseTable();

            if (isUpdated) {
                showAlert("Success", "Course updated successfully!", Alert.AlertType.INFORMATION);
            } else {
                showAlert("Failure", "Failed to update the course. Please try again.", Alert.AlertType.ERROR);
            }

        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please enter a valid number for the course price.", Alert.AlertType.WARNING);
        } catch (Exception e) {
            showAlert("Error", "An unexpected error occurred: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void loadCourseTable(){
        tblcourse.getItems().clear();

        List<CoursesDTO> coursesDTOS = courseBo.loadTable();
        for (CoursesDTO coursesDTO : coursesDTOS) {
            CourseTm courseTm = new CourseTm(coursesDTO.getCourseId(), coursesDTO.getCourseName(), coursesDTO.getDuration(), coursesDTO.getCoursePrice());
            tblcourse.getItems().add(courseTm);
        }
    }

    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colname.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        durationcol.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("coursePrice"));


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
          loadCourseTable();
          setCellValueFactory();
    }

    public void btnbackonaction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/AdminDash.fxml"));
        Scene scene1 = new Scene(root);
        Stage stage1 = (Stage) btnback.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.setTitle("Courses Form");
        stage1.centerOnScreen();
    }
}
