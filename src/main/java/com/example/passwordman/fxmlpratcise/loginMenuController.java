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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class loginMenuController {

    @FXML
    private GridPane rootPane;
    private Scene scene;
    private Stage stage;
    private Parent root;

    public loginMenuController() throws FileNotFoundException {
    }

    public void switchToMenu() {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("started menu.fxml")));

            stage = (Stage) userNameField.getScene().getWindow();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void initialize() {
        checkRemember();
        try {
            java.io.File cssFile = new java.io.File("MainStyle.css");
            if (cssFile.exists()) {
                String cssUrl = cssFile.toURI().toURL().toExternalForm();
                // Подключаем файл
                rootPane.getStylesheets().add(cssUrl);
                // Говорим панели использовать стили из этого файла
                rootPane.getStyleClass().add("anchor-pane");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private PasswordField passwordField;

    @FXML
    protected void loginAction(ActionEvent event) throws IOException {
        String userName = userNameField.getText();
        String password = passwordField.getText();

        if (userName.equals("admin") && password.equals("adminR")) {

            switchToMenu();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Wrong Password or UserName");
            alert.showAndWait();
        }
    }

    @FXML
    private TextField userNameField;


    File configFile = new File("Settings.conf");
    Scanner fileScan = new Scanner(configFile);

    private void checkRemember() {

        String line = fileScan.nextLine();
        boolean rememberMeEnabled;
        if (line.equals("true")) {
            javafx.application.Platform.runLater(this::switchToMenu);
        }
    }
}
