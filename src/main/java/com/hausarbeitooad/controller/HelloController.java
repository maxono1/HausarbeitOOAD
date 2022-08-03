package com.hausarbeitooad.controller;

import com.hausarbeitooad.db.DatabaseConnection;
import com.hausarbeitooad.model.AcceptsDatabase;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController  {

    private DatabaseConnection dbConn;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

}