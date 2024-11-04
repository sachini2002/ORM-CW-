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
import lk.ijse.bo.UserBo;
import lk.ijse.dto.UserDTO;
import lk.ijse.dto.tm.UserTm;

import java.io.IOException;
import java.net.URL;

import java.util.List;
import java.util.ResourceBundle;

public class UserRegisterController implements Initializable {

    @FXML
    private ComboBox<String> cmbrole;

    @FXML
    private Button btnback;

    @FXML
    private TableColumn<?, ?> colpass;

    @FXML
    private TableColumn<?, ?> colrole;

    @FXML
    private TableColumn<?, ?> colusername;

    @FXML
    private TextField password;

    @FXML
    private TableView<UserTm> tbluser;

    @FXML
    private TextField username;

    UserBo userBo = (UserBo) BoFactory.getBoFactory().getBo(BoTypes.User);

    @FXML
    void clearaction(ActionEvent event) {
        username.setText("");
        password.setText("");


    }

    @FXML
    void deleteaction(ActionEvent event) {
        String username = this.username.getText();

        boolean isDeleted = userBo.deleteUser(username);

        loadUsers();
        // Show alert based on the delete result
        Alert alert = new Alert(isDeleted ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
        alert.setTitle(isDeleted ? "Delete Successful" : "Delete Failed");
        alert.setHeaderText(null);
        alert.setContentText(isDeleted ? "User deleted successfully!" : "Failed to delete the user.");
        alert.showAndWait();
    }



    @FXML
    void saveaction(ActionEvent event) {
        String username = this.username.getText();
        String password = this.password.getText();
        cmbrole.setValue("Coordinator");

        UserDTO coordinator = new UserDTO(username, password, "Coordinator");

        // Attempt to save the user
        boolean isSaved = userBo.saveUsers(coordinator);
        loadUsers();

        // Show alert based on the save result
        Alert alert = new Alert(isSaved ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
        alert.setTitle(isSaved ? "Success" : "Error");
        alert.setHeaderText(null);
        alert.setContentText(isSaved ? "User saved successfully!" : "Failed to save the user.");
        alert.showAndWait();
    }




    @FXML
    void searchaction(ActionEvent event) {
        String username = this.username.getText();

        // Search for the user
        UserDTO userDTO = userBo.searchUsers(username);

        if (userDTO != null) {
            // Populate fields if the user is found
            this.username.setText(userDTO.getUsername());
            this.password.setText(userDTO.getPassword());
            this.cmbrole.setValue(userDTO.getRole());
        } else {
            // Show error alert if the user is not found
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("User Not Found");
            alert.setHeaderText(null);
            alert.setContentText("No user found with the given username.");
            alert.showAndWait();
        }
    }




    @FXML
    void updateaction(ActionEvent event) {
        String username = this.username.getText();
        String password = this.password.getText();
        cmbrole.setValue("Coordinator");

        UserDTO coordinator = new UserDTO(username, password, "Coordinator");

        // Attempt to update the user
        boolean isUpdated = userBo.updateUser(coordinator);
        loadUsers();

        // Show alert based on the update result
        Alert alert = new Alert(isUpdated ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
        alert.setTitle(isUpdated ? "Update Successful" : "Update Failed");
        alert.setHeaderText(null);
        alert.setContentText(isUpdated ? "User updated successfully!" : "Failed to update the user.");
        alert.showAndWait();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbrole.getItems().addAll("Coordinator");
        loadUsers();
        setCellValueFactory();
    }

    private void loadUsers(){
        tbluser.getItems().clear();
        List<UserDTO> userDTOS = userBo.loadTable();
        for (UserDTO userDTO : userDTOS) {
            UserTm userTm = new UserTm(userDTO.getUsername(), userDTO.getPassword(), userDTO.getRole());
            tbluser.getItems().add(userTm);
        }
    }
    private void setCellValueFactory() {
        colusername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colrole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colpass.setCellValueFactory(new PropertyValueFactory<>("password"));
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
