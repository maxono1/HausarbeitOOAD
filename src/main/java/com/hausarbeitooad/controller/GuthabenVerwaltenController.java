package com.hausarbeitooad.controller;

import com.hausarbeitooad.SceneFxmlApp;
import com.hausarbeitooad.db.DatabaseConnection;
import com.hausarbeitooad.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class GuthabenVerwaltenController implements Stageable, Initializable, Loggerble, Guthaberble {

    @FXML
    private Text guthabenAnzeigeID;
    private Stage stage;
    private DatabaseConnection conn;
    private String activeUser;

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        conn = DatabaseConnection.getInstance();
    }
    @FXML
    void onActionGuthabenAufladenBtn(ActionEvent event) {
        stage.setScene(SceneFxmlApp.getScenes().get(SceneName.GUTHABENAUFLADEN).getScene());
        event.consume();
    }

    @FXML
    void onActionGuthabenVerwaltenBackBtn(ActionEvent event) {
        stage.setScene(SceneFxmlApp.getScenes().get(SceneName.MAIN).getScene());
        event.consume();
    }

    @Override
    public void setActiveUser(String uname) {
        this.activeUser = uname;
        updateGuthaben();
    }

    @Override
    public void updateGuthaben() {
        guthabenAnzeigeID.setText(conn.selectGuthaben(activeUser)+ "€");
    }
}