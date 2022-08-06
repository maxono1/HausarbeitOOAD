package com.hausarbeitooad.controller;

import com.hausarbeitooad.RudisDampfkesselApp;
import com.hausarbeitooad.db.DatabaseConnection;
import com.hausarbeitooad.entity.Spiel;
import com.hausarbeitooad.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
/**
 * Controller der GameDetailView
 * erlaubt dem User eine Rezension zu schreiben und die Spielzeit einzulesen
 *
 * @source selber erstellt
 *
 * @author 1st:Tim Cirksena, 2nd: Maximilian Jaesch
 * */
public class GameDetailViewController implements Stageable, Initializable, LoginListener, AcceptsID {

    private Stage stage;
    private DatabaseConnection conn;
    private String activeUser;
    @FXML
    private ImageView arrowLeftID1;

    @FXML
    private Text erfolgeTextID;

    @FXML
    private Label gameNameID;

    @FXML
    private Button rezensionSchreibenID;
    @FXML
    private Text spielzeitTextID;

    private int spielID;

    @FXML
    void handleOnActionRezensionSchreiben(ActionEvent event) {
        //interface acceptsid
        RudisDampfkesselApp.getScenes().get(SceneName.REVIEW_VIEW).getCleaningListener().cleanTextFields();
        RudisDampfkesselApp.getScenes().get(SceneName.REVIEW_VIEW).getAcceptsID().setSpielID(this.spielID);
        stage.setScene(RudisDampfkesselApp.getScenes().get(SceneName.REVIEW_VIEW).getScene());
        event.consume();
    }

    /**
     * back button navigiert eine Scene zurück.
     *
     * @author Tim Cirksena
     * Source: selber erstellt
     * */
    @FXML
    void onActionCollectionBackBtn(ActionEvent event) {
        stage.setScene(RudisDampfkesselApp.getScenes().get(SceneName.COLLECTION_VIEW).getScene());
        event.consume();
    }


    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.conn = DatabaseConnection.getInstance();
    }

    @Override
    public void setActiveUser(String uname) {
        this.activeUser = uname;
    }

    /**
     * Interfacemethode, die uns erlaubt bei einem Update der spielID aus der Datenbank informationen.
     *
     * @param spielID
     * @author 1st:Maximilian Jaesch
     */
    @Override
    public void setSpielID(int spielID) {
        this.spielID = spielID;
        //System.out.println(spielID);
        try {
            Spiel spiel = conn.retrieveSpielById(spielID);
            erfolgeTextID.setText(spielErfolg(spielID));
            gameNameID.setText(spiel.getName());
            spielzeitTextID.setText(Integer.toString(conn.retrieveSpielzeitNutzerBesitzt(activeUser, spielID)));
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


    /**
     * nimmt spielID, liest die Spielzeit aus der Tabelle Nutzer_Besitzt aus und generiert einen fortschritt
     * @author 1st:Tim Cirksena
     *
     * @param spielID
     * @return String erfolg, zeigt den Spielerfolg grob an
     * @throws SQLException
     */
    private String spielErfolg(int spielID) throws SQLException {
        int spielzeit = conn.retrieveSpielzeitNutzerBesitzt(activeUser, spielID);
        if (spielzeit == 0) {
            return "Blutiger Anfänger! 0%";
        } else if (spielzeit > 0 && spielzeit <= 150) {
            return "Anfänger, es wurden schon Fortschritte gemacht! 25%";
        } else if (spielzeit > 150 && spielzeit <= 750) {
            return "Fortgeschrittner, es wurden große Erfolge erzielt! 50%";
        } else if (spielzeit > 750 && spielzeit <= 1500) {
            return "Meister, Sie haben das Spiel gemeistert! 75%";
        } else if (spielzeit > 1500 && spielzeit <= 2000) {
            return "Kleuker, Sie wissen über alles bescheid! 100%";
        } else {
            return "Blutiger Anfänger! 0%";
        }

    }
}
