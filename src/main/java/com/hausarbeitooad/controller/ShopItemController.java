package com.hausarbeitooad.controller;

import com.hausarbeitooad.SceneFxmlApp;
import com.hausarbeitooad.db.DatabaseConnection;
import com.hausarbeitooad.entity.NutzerBesitzt;
import com.hausarbeitooad.entity.Spiel;
import com.hausarbeitooad.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ShopItemController implements Stageable, Initializable, Loggerble, AcceptsID, Guthaberble {

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

    @FXML
    private void btnOkClicked(ActionEvent event) {

    }

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
        System.out.println(spielID);
        try {
            Spiel spiel = conn.retrieveSpielById(spielID);
            preisInhaltID.setText(Double.toString(spiel.getPreis()));
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

    @FXML
    private void kaufen() {
        //guthaben abfragen
        try {
            if (conn.selectGuthaben(activeUser) > Double.parseDouble(preisInhaltID.getText())) {
                //besitzt updaten
                conn.insertNutzerBesitzt(new NutzerBesitzt(spielID, activeUser));
                //guthaben abziehen
                conn.updateGuthaben(activeUser, -Double.parseDouble(preisInhaltID.getText()));
                conn.commit();
                userBesitztAbfrage();
            } else {

            }
        } catch (SQLException s) {
            DatabaseConnection.printSQLException(s);
        }


    }

    @FXML
    private void onActionKaufenBackBtn(ActionEvent event) {
        stage.setScene(SceneFxmlApp.getScenes().get(SceneName.SHOP_MENU).getScene());
        event.consume();
    }

    /**
     * Funktion: von dem active user guthaben laden
     */
    @Override
    public void updateGuthaben() {
        guthabenInItemViewID.setVisible(true);
        guthabenInItemViewID.setText("" + conn.selectGuthaben(activeUser));
    }

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
}
