module com.example.passwordman.fxmlpratcise {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.passwordman.fxmlpratcise to javafx.fxml;
    exports com.example.passwordman.fxmlpratcise;
}