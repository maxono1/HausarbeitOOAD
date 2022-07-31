package com.hausarbeitooad.controller;

import com.hausarbeitooad.SceneFxmlApp;
import com.hausarbeitooad.db.DatabaseConnection;
import com.hausarbeitooad.model.AcceptsDatabase;
import com.hausarbeitooad.model.SceneName;
import com.hausarbeitooad.model.Stageable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Stageable, Initializable, AcceptsDatabase {

    private Stage stage;
    private DatabaseConnection conn;

    @FXML
    private PasswordField passwordID;

    @FXML
    private TextField usernameID;

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void setDatabaseConnection(DatabaseConnection conn) {
        this.conn = conn;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void handleOnSubmitButtonClicked(ActionEvent event) {
        System.out.println("clicked on login");
        stage.setScene(SceneFxmlApp.getScenes().get(SceneName.MAIN).getScene());
        event.consume();
    }
}
