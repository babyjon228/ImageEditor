module com.example.njrat {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.njrat to javafx.fxml;
    exports com.example.njrat;
}