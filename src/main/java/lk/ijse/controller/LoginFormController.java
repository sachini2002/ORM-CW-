package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BoFactory;
import lk.ijse.bo.BoTypes;
import lk.ijse.bo.UserBo;
import lk.ijse.dto.UserDTO;
import javafx.fxml.Initializable;
import lk.ijse.entity.Student;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import java.util.List;

public class LoginFormController implements Initializable {

    @FXML
    private AnchorPane Anchor;

    @FXML
    private AnchorPane ChildPane;

    @FXML
    private Button loginbtn;


    @FXML
    private TextField UserName;

    @FXML
    private ComboBox<String> cmbAdmin;

    @FXML
    private PasswordField passward;

    UserBo userBo = (UserBo) BoFactory.getBoFactory().getBo(BoTypes.User);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadRolesIntoComboBox();
    }

    private void loadRolesIntoComboBox() {
        List<String> roles = userBo.getAllRoles(); // Get roles from BO
        cmbAdmin.getItems().addAll(roles); // Add roles to ComboBox
    }






    public void btnLoginOnAction(ActionEvent event) {
        String role = cmbAdmin.getValue(); // Get the selected role from the ComboBox
        String password = passward.getText(); // Get the entered password
        String username = UserName.getText(); // Get the entered username
        List<Student> students = new ArrayList<>();

        UserDTO userDTO = new UserDTO(role, username, password,students);
        List<UserDTO> userList = new ArrayList<>();

        if(role.equals("Admin")) {
            userList = userBo.getUserDetails(userDTO);
            try {
                boolean isCredintialsok = false;
                for (UserDTO userDTO1 : userList) {
                    if(userDTO1.getUsername().equals(username)&&userDTO1.getPassword().equals(password)) {
                        new Alert(Alert.AlertType.INFORMATION, "Welcome Admin " + username).show();
                        userDTO1.getUsername();
                        userDTO1.getPassword();
                        try {
                            Parent root = FXMLLoader.load(getClass().getResource("/AdminDash.fxml"));
                            Scene scene1 = new Scene(root);
                            Stage stage1 = (Stage) loginbtn.getScene().getWindow();
                            stage1.setScene(scene1);
                            stage1.setTitle("Dashboard Form");
                            stage1.centerOnScreen();
                            isCredintialsok = true;
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }

                }
                if (isCredintialsok==false) {
                    new Alert(Alert.AlertType.INFORMATION, "Invalid Admin Credentials").show();
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        } else if (role.equals("Coordinator")) {
            userList = userBo.getUserDetails(userDTO);
            try {
                boolean isCredintialsok = false;
                for (UserDTO userDTO1 : userList) {
                    if(userDTO1.getUsername().equals(username)&&userDTO1.getPassword().equals(password)) {
                        new Alert(Alert.AlertType.INFORMATION, "Welcome Coordinator " + username).show();
                        userDTO1.getUsername();
                        userDTO1.getPassword();

                        try {
                            Parent root = FXMLLoader.load(getClass().getResource("/AdminCordinator.fxml"));
                            Scene scene1 = new Scene(root);
                            Stage stage1 = (Stage) loginbtn.getScene().getWindow();
                            stage1.setScene(scene1);
                            stage1.setTitle("Dashboard Form");
                            stage1.centerOnScreen();
                            isCredintialsok = true;

                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }
                }
                if (isCredintialsok==false) {
                    new Alert(Alert.AlertType.INFORMATION, "Invalid Coordinator Credentials").show();
                }

            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }









}
