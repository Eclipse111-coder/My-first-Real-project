package com.example.passwordman.fxmlpratcise;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

import static javafx.scene.control.ButtonType.CANCEL;
import static javafx.scene.control.ButtonType.OK;


    public class passGenController {

        @FXML
        private AnchorPane rootPane;

        public void initialize() {
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

        private Scene scene;
        private Stage stage;
        private Parent root;
        @FXML
        VBox sideMenu;

        public File passwordStorage = new File("Passwords storage");
        Password passHelp = new Password();
        @FXML
        Button btnGenerateNewPassword;
        @FXML
        Button btnCopyPassword;
        @FXML
        TextField result;

        public void generatePassword() {

            Dialog<Password> GeneratePassword = new Dialog<>();
            VBox generatePasswordVBox = new VBox(10);
            TextField passwordEncrypt = new TextField();
            passwordEncrypt.setPromptText("Type here your Password");
            Text helpText = new Text("Skip if you didn`t wanna encrypt any word");
            TextField lengthOfPassword = new TextField();
            lengthOfPassword.setText("8");
            lengthOfPassword.setPromptText("type Here length of your Password");
            Text helpTextWithLength = new Text("type here length");

            generatePasswordVBox.getChildren().addAll(passwordEncrypt, helpText, helpTextWithLength, lengthOfPassword);
            GeneratePassword.getDialogPane().setContent(generatePasswordVBox);
            GeneratePassword.getDialogPane().getButtonTypes().addAll(OK, CANCEL);

            GeneratePassword.setResultConverter(button -> {
                if (button == OK) {
                    if (passwordEncrypt.getText().trim().isEmpty()) {
                        String newPassword = "";
                        int tempIntForLoop = Integer.parseInt(lengthOfPassword.getText());
                        for (int i = 0; i < tempIntForLoop; i++) {
                            newPassword += passHelp.poolIndexChoose();
                        }
                        try (PrintWriter pw = new PrintWriter(new FileWriter(passwordStorage, true))) {
                            pw.println(newPassword);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        result.setText(findLastString());
                        passHelp.setPassword(result.getText());
                    } else {
                        String encrypted = passHelp.caesarEncrypt(passwordEncrypt.getText());
                        result.setText(encrypted);
                        passHelp.setPassword(encrypted);
                        try (PrintWriter pw = new PrintWriter(new FileWriter(passwordStorage, true))) {
                            pw.println(result.getText());
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        System.out.println("1");
                    }
                }
                return null;
            });
            GeneratePassword.showAndWait();
        }

        public void copyPass() {


            Clipboard copyPassword = Clipboard.getSystemClipboard();
            ClipboardContent passwordThatNeedToBeCopy = new ClipboardContent();
            passwordThatNeedToBeCopy.putString(findLastString());
            copyPassword.setContent(passwordThatNeedToBeCopy);
        }

        private String findLastString() {
            Scanner fileScan = null;
            String lastLine = "";
            try {
                fileScan = new Scanner(passwordStorage);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            while (fileScan.hasNextLine()) {
                lastLine = fileScan.nextLine();
            }
            return lastLine;
        }

        public void toggleMenu(ActionEvent event) {
            sideMenu.setVisible(true);
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
        public void openSettings(ActionEvent event) {
        }
    }


