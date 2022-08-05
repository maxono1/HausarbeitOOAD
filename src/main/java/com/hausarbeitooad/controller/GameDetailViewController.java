package com.hausarbeitooad.controller;

import com.hausarbeitooad.SceneFxmlApp;
import com.hausarbeitooad.db.DatabaseConnection;
import com.hausarbeitooad.entity.Spiel;
import com.hausarbeitooad.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GameDetailViewController implements Stageable, Initializable, LoginListener, AcceptsID {

    private Stage stage;
    private DatabaseConnection conn;
    private String activeUser;
    @FXML
    private ImageView arrowBackCollectedGameID;

    @FXML
    private Label gameNameID;

    @FXML
    private Button rezensionSchreibenID;
    private int spielID;

    @FXML
    void handleOnActionRezensionSchreiben(ActionEvent event) {
        stage.setScene(SceneFxmlApp.getScenes().get(SceneName.REVIEW_VIEW).getScene());
        event.consume();
    }
    @FXML
    void onActionCollectionBackBtn(ActionEvent event) {
        stage.setScene(SceneFxmlApp.getScenes().get(SceneName.MAIN).getScene());
        event.consume();
    }


    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void setActiveUser(String uname) {
        this.activeUser = uname;
    }

    @Override
    public void setSpielID(int spielID) {
        this.spielID = spielID;
        System.out.println(spielID);
        try {
            Spiel spiel = conn.retrieveSpielById(spielID);
            /*
            preisInhaltID.setText(Double.toString(spiel.getPreis()) + "€");
            bewertungInhaltID.setText(Integer.toString(spiel.getBewertungProzent()) + "%");
            genreInhaltID.setText(spiel.getGenre());
            gameNameID.setText(spiel.getName());
            gameImageID.setImage(new Image(new ByteArrayInputStream(spiel.getTitelbild())));
            beschreibungInhaltID.setText(spiel.getBeschreibung());*/
        } catch (SQLException s) {
            DatabaseConnection.printSQLException(s);
        }
        //userBesitztAbfrage();
    }
}
