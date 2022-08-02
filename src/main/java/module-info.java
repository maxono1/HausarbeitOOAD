module com.example.hausarbeitooad {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires java.sql;

    opens com.hausarbeitooad to javafx.fxml;
    exports com.hausarbeitooad;
    exports com.hausarbeitooad.controller;
    exports com.hausarbeitooad.model;
    opens com.hausarbeitooad.controller to javafx.fxml;

}