module com.example.kino_seans {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.kino_seans to javafx.fxml;
    exports com.example.kino_seans;
}