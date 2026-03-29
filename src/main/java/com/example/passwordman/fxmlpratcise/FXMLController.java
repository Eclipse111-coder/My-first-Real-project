package com.example.passwordman.fxmlpratcise;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class FXMLController {
    @FXML
    private PasswordField passwordField;

    @FXML
    protected void loginAction() throws IOException {
        String userName = userNameField.getText();
        String password = passwordField.getText();

        if (userName.equals("admin") && password.equals("adminR")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succses");
            alert.setHeaderText(null);
            alert.setContentText("Right password and UserName");
            alert.showAndWait();
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Wrong Password or UserName");
            alert.showAndWait();
        }
    }

    @FXML
    private TextField userNameField;
}
