package com.hausarbeitooad.controller;

import com.hausarbeitooad.RudisDampfkesselApp;
import com.hausarbeitooad.db.DatabaseConnection;
import com.hausarbeitooad.entity.NutzerBesitzt;
import com.hausarbeitooad.entity.Spiel;
import com.hausarbeitooad.model.*;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Dieser Controller steuert die ShopItem View
 *
 * @author 1st: Maximilian Jaesch, 2nd: Tim Cirksena, 3rd: Abdurrahman Azattemür
 */
public class ShopItemController implements Stageable, Initializable, LoginListener, AcceptsID, GuthabenListner {

    @FXML
    private Label keineKohleID;
    @FXML
    private Label beschreibungInhaltID;
    @FXML
    private ImageView gameImageID;
    @FXML
    private Label preisInhaltID;
    @FXML
    private Label bewertungInhaltID;
    @FXML
    private Label genreInhaltID;
    @FXML
    private Label gameNameID;
    @FXML
    private Button kaufBtnID;
    private DatabaseConnection conn;
    private Stage stage;
    private String activeUser;
    private int spielID;
    @FXML
    private Label guthabenInItemViewID;

    @Override
    public void setActiveUser(String uname) {
        this.activeUser = uname;
        updateGuthaben();
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        conn = DatabaseConnection.getInstance();
    }

    @Override
    public void setSpielID(int spielID) {
        //spiel laden von id

        this.spielID = spielID;
        //System.out.println(spielID);
        try {
            Spiel spiel = conn.retrieveSpielById(spielID);
            preisInhaltID.setText(Double.toString(spiel.getPreis()) + "€");
            bewertungInhaltID.setText(Integer.toString(spiel.getBewertungProzent()) + "%");
            genreInhaltID.setText(spiel.getGenre());
            gameNameID.setText(spiel.getName());
            gameImageID.setImage(new Image(new ByteArrayInputStream(spiel.getTitelbild())));
            beschreibungInhaltID.setText(spiel.getBeschreibung());
        } catch (SQLException s) {
            DatabaseConnection.printSQLException(s);
        }
        userBesitztAbfrage();
    }

    /**
     * Diese Methode ist für das kaufen der spiele zuständig.
     *
     * @author Maximilian Jaesch
     */
    @FXML
    private void kaufen() {
        //guthaben abfragen
        try {
            int iend = preisInhaltID.getText().indexOf("€");

            if (conn.selectGuthaben(activeUser) > Double.parseDouble(preisInhaltID.getText().substring(0, iend))) {
                //besitzt updaten
                conn.insertNutzerBesitzt(new NutzerBesitzt(spielID, activeUser, ThreadLocalRandom.current().nextInt(0, 2000)));
                //guthaben abziehen
                conn.updateGuthaben(activeUser, -Double.parseDouble(preisInhaltID.getText().substring(0, iend)));
                conn.commit();
                //alle guthaben- anzeigen updaten
                userBesitztAbfrage();
                updateGuthaben();
                RudisDampfkesselApp.getScenes().get(SceneName.GUTHABENVERWALTEN).getGuthabenListner().updateGuthaben();
                RudisDampfkesselApp.getScenes().get(SceneName.COLLECTION_VIEW).getBuyListner().updateGames();
            } else {
                keineKohleID.setVisible(true);
                //Quelle https://stackoverflow.com/questions/29487645/how-to-make-a-label-visible-for-a-certain-time-and-then-should-be-invisible-with
                PauseTransition visiblePause = new PauseTransition(
                        Duration.seconds(3)
                );
                visiblePause.setOnFinished(
                        event -> keineKohleID.setVisible(false)
                );
                visiblePause.play();
            }
        } catch (SQLException s) {
            DatabaseConnection.printSQLException(s);
        }
    }

    /**
     * back button navigiert eine Scene zurück.
     *
     * @author Tim Cirksena
     * Source: selber erstellt
     */
    @FXML
    private void onActionKaufenBackBtn(ActionEvent event) {
        stage.setScene(RudisDampfkesselApp.getScenes().get(SceneName.SHOP_MENU).getScene());
        event.consume();
    }

    /**
     * Funktion: von dem active user guthaben laden
     *
     * @author Tim Cirksena
     */
    @Override
    public void updateGuthaben() {
        guthabenInItemViewID.setVisible(true);
        guthabenInItemViewID.setText(conn.selectGuthaben(activeUser) + "€");
    }

    /**
     * Funktion: überprüfen, ob der User das Spiel bereits besitzt.
     *
     * @author Maximilian Jaesch
     */
    private void userBesitztAbfrage() {
        //abfrage besitzt der user das spiel
        try {
            boolean userBesitztSpiel = conn.besitztNutzerSpiel(activeUser, spielID);
            if (userBesitztSpiel) {
                kaufBtnID.setDisable(true);
                kaufBtnID.setText("bereits erworben");
            } else {
                kaufBtnID.setDisable(false);
                kaufBtnID.setText("Kaufen");
            }
        } catch (SQLException s) {
            DatabaseConnection.printSQLException(s);
        }
    }

    /**
     * Diese Methode ist für das Wechseln zur Rezension View zuständig.
     *
     * @param event
     * @author Abdurrahman Azattemür
     */
    @FXML
    private void onActionRezensionBtn(ActionEvent event) {
        RudisDampfkesselApp.getScenes().get(SceneName.REZENSION_VIEW).getAcceptsID().setSpielID(this.spielID);
        stage.setScene(RudisDampfkesselApp.getScenes().get(SceneName.REZENSION_VIEW).getScene());
        event.consume();
    }
}
