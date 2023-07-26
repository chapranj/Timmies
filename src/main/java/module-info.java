module com.example.timmies {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.timmies to javafx.fxml;
    exports com.example.timmies;
}