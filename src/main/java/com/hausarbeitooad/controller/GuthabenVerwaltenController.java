package com.hausarbeitooad.controller;

import com.hausarbeitooad.RudisDampfkesselApp;
import com.hausarbeitooad.db.DatabaseConnection;
import com.hausarbeitooad.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class GuthabenVerwaltenController implements Stageable, Initializable, LoginListener, GuthabenListner {

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
        RudisDampfkesselApp.getScenes().get(SceneName.GUTHABENAUFLADEN).getCleaningListener().cleanTextFields();
        stage.setScene(RudisDampfkesselApp.getScenes().get(SceneName.GUTHABENAUFLADEN).getScene());
        event.consume();
    }

    /**
     * back button navigiert eine Scene zurück.
     *
     * @author Tim Cirksena
     * Source: selber erstellt
     * */
    @FXML
    void onActionGuthabenVerwaltenBackBtn(ActionEvent event) {
        stage.setScene(RudisDampfkesselApp.getScenes().get(SceneName.MAIN).getScene());
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