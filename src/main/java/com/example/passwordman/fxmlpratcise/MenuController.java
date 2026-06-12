package com.example.passwordman.fxmlpratcise;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class MenuController {

    @FXML
    private AnchorPane rootPane;


    private Scene scene;
    private Stage stage;
    private Parent root;
    @FXML
    VBox sideMenu;

    @FXML
    Circle circle1;
    @FXML
    Circle circle2;

    public void toggleMenu(ActionEvent event) {
        sideMenu.setVisible(true);
    }

    public void openSettings(ActionEvent event) {
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("settings.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void createAnimationHover(javafx.scene.Node element) {
        ScaleTransition scaleIn = new ScaleTransition(Duration.millis(400), element);
        scaleIn.setToX(1.3);
        scaleIn.setToY(1.3);

        ScaleTransition scaleOut = new ScaleTransition(Duration.millis(400), element);
        scaleOut.setToX(1.0);
        scaleOut.setToY(1.0);

        element.setOnMouseEntered(e -> scaleIn.playFromStart());
        element.setOnMouseExited(e -> scaleOut.playFromStart());
    }

    @FXML
    public void initialize() {
        createAnimationHover(circle1);
        createAnimationHover(circle2);

        try {
            java.io.File cssFile = new java.io.File("MainStyle.css");
            if (cssFile.exists()) {
                String cssUrl = cssFile.toURI().toURL().toExternalForm();
                rootPane.getStylesheets().add(cssUrl);
                rootPane.getStyleClass().add("anchor-pane");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void logOut(ActionEvent event) throws IOException {
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("loginMenu.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMenu(ActionEvent event) {
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("started menu.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void TODOset(ActionEvent event) {
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TODOlist.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("An error");
            alert.setHeaderText("can`t load TODO list");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }

    public void exchanger(ActionEvent event) {
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("exchanger.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Can`t load exchanger window");
            alert.setContentText(e.getMessage());
            alert.showAndWait();

        }
    }

    public void openPasswordGen(ActionEvent event) {
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Passwordgen.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Can`t load Password generator window");
            alert.setContentText(e.getMessage());
            alert.showAndWait();

        }
    }
}
