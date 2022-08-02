package com.hausarbeitooad.controller;

import com.hausarbeitooad.db.DatabaseConnection;
import com.hausarbeitooad.model.AcceptsDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController implements AcceptsDatabase {

    private DatabaseConnection dbConn;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @Override
    public void setDatabaseConnection(DatabaseConnection conn) {
        this.dbConn = conn;
    }
}