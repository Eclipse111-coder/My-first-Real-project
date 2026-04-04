package com.example.passwordman.fxmlpratcise;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class loginMenuController {

    private Scene scene;
    private Stage stage;
    private Parent root;

    public void switchToMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("started menu.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private PasswordField passwordField;

    @FXML
    protected void loginAction(ActionEvent event) throws IOException {
        String userName = userNameField.getText();
        String password = passwordField.getText();

        if (userName.equals("admin") && password.equals("adminR")) {

        switchToMenu(event);

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
