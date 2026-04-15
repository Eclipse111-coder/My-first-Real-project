package com.example.passwordman.fxmlpratcise;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class exchangerController {

    @FXML
    public ChoiceBox<String> currencyChoose;
    public TextField textFieldResult;
    public TextField textFieldEntering;

    @FXML
    private void initialize() {
        // Инициализация ChoiceBox
        currencyChoose.setItems(FXCollections.observableArrayList(
                "RUB --> USDT",
                "USDT --> RUB",
                "RUB --> EUR",
                "EUR --> RUB"
        ));
        currencyChoose.setValue("RUB --> USDT");

        btnConvert.setOnAction(this::handleConvert);
    }
    @FXML
    Button btnConvert;
    @FXML
    private void handleConvert(ActionEvent event) {
        try {
            String text = textFieldEntering.getText();
            float userEnterence = Float.parseFloat(text);

            String userChoose = currencyChoose.getValue();
            switch (userChoose) {
                case "RUB --> USDT":
                    userEnterence /= 76.66f;
                    break;
                case "USDT --> RUB":
                    userEnterence *= 76.66f;
                    break;
                case "RUB --> EUR":
                    userEnterence /= 90.40f;
                    break;
                case "EUR --> RUB":
                    userEnterence *= 90.40f;
                    break;
                default:
                    textFieldResult.setText("Ошибка выбора");
                    return;
            }
            textFieldResult.setText(String.format("%.2f", userEnterence));
        } catch (NumberFormatException e) {
            textFieldResult.setText("Введите число");

        }
    }

    private Scene scene;
    private Stage stage;
    private Parent root;

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

    public void openSettings(ActionEvent event) {
    }

    public void exchanger(ActionEvent event) {
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("exchanger.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();   // <-- самое важное: показать реальную ошибку
            // Опционально: показать диалог с ошибкой пользователю
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Не удалось загрузить окно обмена валют");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    public void TODOset(ActionEvent event) {
            try {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TODOlist.fxml")));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
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
    @FXML
    VBox sideMenu;
    public void toggleMenu(ActionEvent event) {sideMenu.setVisible(true);}
}
