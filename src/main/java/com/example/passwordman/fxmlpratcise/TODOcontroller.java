package com.example.passwordman.fxmlpratcise;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.Scanner;

public class TODOcontroller {

    File taskInfo = new File("taskInfo.txt");
    ObservableList<Task> items = FXCollections.observableArrayList();

    @FXML
    ListView<Task> listView;

    @FXML
    public void initialize() {
        loadTaskFromFile();
        listView.setItems(items);
    }

    public void AddingTask(ActionEvent event) {
        Dialog<Task> userChoice = new Dialog<>();
        VBox dialogVBox = new VBox(10);
        TextField dialogUserTextFieldName = new TextField();
        dialogUserTextFieldName.setPromptText("Name of your task");
        TextField dialogUserTextFieldDescription = new TextField();
        dialogUserTextFieldDescription.setPromptText("Your description");
        dialogVBox.getChildren().addAll(dialogUserTextFieldDescription, dialogUserTextFieldName);
        userChoice.getDialogPane().setContent(dialogVBox);
        userChoice.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        userChoice.setResultConverter(button -> {
            if (button == ButtonType.OK) {
                Task taskResult = new Task();
                taskResult.setName(dialogUserTextFieldName.getText());
                taskResult.setDescription(dialogUserTextFieldDescription.getText());
                return taskResult;
            }
            return null;
        });
        userChoice.showAndWait().ifPresent(newTask -> {
            items.add(newTask);
            saveTaskIntoFile();
        });
        saveTaskIntoFile();
        userChoice.getDialogPane();
    }
        @FXML
        Button btnAddTask;

        @FXML
        private Button btnDelTask;

        public void deleteTask(ActionEvent event) {
            Task selected = getSelectionOfTaskAndAlert();
            if (selected != null) {
                items.remove(selected);
                saveTaskIntoFile();
            }
        }

        @FXML
        private Button btnEditTask;

        public void editTask(ActionEvent event) {

            int selectedIndex = listView.getSelectionModel().getSelectedIndex();
            Task selected = (Task) listView.getSelectionModel().getSelectedItem();
            if (selected == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Alert!");
                alert.setHeaderText("Didn`t selected any task!");
                alert.showAndWait();
                return;
            }
            Dialog<Task> userChoice = new Dialog<>();
            VBox dialogVBox = new VBox(10);
            TextField dialogUserTextFieldName = new TextField();
            dialogUserTextFieldName.setPromptText("Name of your new task");
            TextField dialogUserTextFieldDescription = new TextField();
            dialogUserTextFieldDescription.setPromptText("Your new description");
            dialogVBox.getChildren().addAll(dialogUserTextFieldDescription, dialogUserTextFieldName);
            userChoice.getDialogPane().setContent(dialogVBox);
            userChoice.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
            userChoice.showAndWait();

            userChoice.setResultConverter(button -> {
                if (button == ButtonType.OK) {
                    Task OverridedTask = new Task();
                    OverridedTask.setName(dialogUserTextFieldName.getText());
                    OverridedTask.setDescription(dialogUserTextFieldDescription.getText());
                    return OverridedTask;
                }
                return null;
            });
            userChoice.showAndWait().ifPresent(newTask -> {
                int index = listView.getSelectionModel().getSelectedIndex();
                items.set(index, newTask);
                saveTaskIntoFile();
            });
            saveTaskIntoFile();
            userChoice.getDialogPane();
        }

        private void saveTaskIntoFile() {
            try {
                PrintWriter pw = new PrintWriter("taskInfo.txt");
                for (Task t : items) {
                    pw.println(t.getName() + "|" + t.getDescription());
                }
                pw.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        @FXML
        public void showDesc() {

            Task selected = getSelectionOfTaskAndAlert();
            if (selected != null) {
                TextInputDialog tid = new TextInputDialog(selected.getName());
                tid.setTitle("Task Description");
                tid.setHeaderText("Description of " + selected.getName() + " is " + selected.getDescription());
                tid.showAndWait();
            }
        }

        private void loadTaskFromFile() {
            if (!taskInfo.exists()) return;

            try (Scanner scan = new Scanner(taskInfo)) {
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    String[] parts = line.split("\\|");

                    if (parts.length >= 2) {
                        Task loadedTask = new Task();
                        loadedTask.setName(parts[0].trim());
                        loadedTask.setDescription(parts[1].trim());
                        items.add(loadedTask);
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("File isnt created now");
            }
        }


        private Task getSelectionOfTaskAndAlert() {
            int selectedIndex = listView.getSelectionModel().getSelectedIndex();
            Task selected = listView.getSelectionModel().getSelectedItem();
            if (selected == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Alert!");
                alert.setHeaderText("Didn`t selected any task!");
                alert.showAndWait();
                return null;
            }
            return selected;
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