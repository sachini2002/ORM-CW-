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
import lk.ijse.bo.PaymentBo;
import lk.ijse.dto.PaymentDTO;
import lk.ijse.dto.tm.PaymentTm;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentFormController implements Initializable {

    @FXML
    private TextField paymentId;

    @FXML
    private TextField amount;

    @FXML
    private TableView<PaymentTm> tblpayment;

    @FXML
    private TableColumn<PaymentTm, String> payid;

    @FXML
    private TableColumn<PaymentTm, Double> fullamount;

    @FXML
    private Button backbtn;

    @FXML
    private TableColumn<PaymentTm, Double> advanced;

    @FXML
    private TableColumn<PaymentTm, Double> havetopay;

    private final PaymentBo paymentBo = (PaymentBo) BoFactory.getBoFactory().getBo(BoTypes.Payment);

    @FXML
    public void btnsaveonpayment(ActionEvent event) {
        try {
            String payId = paymentId.getText();
            double fullAmount = Double.parseDouble(amount.getText());

            // Create a PaymentDTO object and save
            PaymentDTO paymentDTO = new PaymentDTO(payId, fullAmount, 0, 0); // Assuming default balance and paidAmount
            boolean isSaved = paymentBo.savePayment(paymentDTO);

            if (isSaved) {
                showAlert("Success", "Payment saved successfully.", Alert.AlertType.INFORMATION);
                loadTable();
                clearFields();
            } else {
                showAlert("Error", "Failed to save payment.", Alert.AlertType.ERROR);
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid amount. Please enter a valid number.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void btnremovepayment(ActionEvent event) {
        String payId = paymentId.getText();

        if (payId.isEmpty()) {
            showAlert("Error", "Please enter a Payment ID to delete.", Alert.AlertType.WARNING);
            return;
        }

        boolean isRemoved = paymentBo.removePayment(payId);
        if (isRemoved) {
            showAlert("Success", "Payment removed successfully.", Alert.AlertType.INFORMATION);
            loadTable();
            clearFields();
        } else {
            showAlert("Error", "Failed to remove payment.", Alert.AlertType.ERROR);
        }
    }

    private void loadTable() {
        tblpayment.getItems().clear();
        List<PaymentDTO> paymentDTOS = paymentBo.loadTable();


        for (PaymentDTO paymentDTO : paymentDTOS) {
            PaymentTm paymentTm = new PaymentTm(paymentDTO.getPaymentId(), paymentDTO.getAmount(), paymentDTO.getPaidAmount(), paymentDTO.getBalance());
            System.out.println(paymentDTO.getPaymentId());
            System.out.println(paymentDTO.getAmount());
            System.out.println(paymentDTO.getPaidAmount());
            System.out.println(paymentDTO.getBalance());
            tblpayment.getItems().add(paymentTm);
        }
    }

    private void setCellValueFactory() {
        payid.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        fullamount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        advanced.setCellValueFactory(new PropertyValueFactory<>("paidAmount"));
        havetopay.setCellValueFactory(new PropertyValueFactory<>("balance"));
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        paymentId.clear();
        amount.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadTable();
    }

    public void btnback(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/AdminDash.fxml"));
        Scene scene1 = new Scene(root);
        Stage stage1 = (Stage) backbtn.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.setTitle("Courses Form");
        stage1.centerOnScreen();
    }
}
