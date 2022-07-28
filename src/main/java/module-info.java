module com.example.hausarbeitooad {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires java.sql;

    opens com.example.hausarbeitooad to javafx.fxml;
    exports com.example.hausarbeitooad;
}