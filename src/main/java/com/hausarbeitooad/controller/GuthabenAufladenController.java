package com.hausarbeitooad.controller;

import com.hausarbeitooad.RudisDampfkesselApp;
import com.hausarbeitooad.db.DatabaseConnection;
import com.hausarbeitooad.model.CleaningListener;
import com.hausarbeitooad.model.LoginListener;
import com.hausarbeitooad.model.SceneName;
import com.hausarbeitooad.model.Stageable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Dieser Controller steuert die Guthaben Aufladen View
 *
 * @author 1st: Tim Cirksena, 2nd: Maximilian Jaesch, 3rd: Abdurrahman Azattemür
 * @Source selber erstellt
 */
public class GuthabenAufladenController implements Stageable, Initializable, LoginListener, CleaningListener {

    private Stage stage;
    @FXML
    private TextField moneyID;
    private DatabaseConnection conn;
    private String activeUser;

    /**
     * Diese Methode schreibt 20 auf das Textfeld
     *
     * @param event
     * @author Tim Cirksena
     */
    @FXML
    private void onClick20Euro(ActionEvent event) {
        moneyID.setText("20");
        event.consume();
    }

    /**
     * Diese Methode schreibt 50 auf das Textfeld
     *
     * @param event
     * @author Tim Cirksena
     */
    @FXML
    private void onClick50Euro(ActionEvent event) {
        moneyID.setText("50");
        event.consume();
    }

    /**
     * Diese Methode schreibt 100 auf das Textfeld
     *
     * @param event
     * @author Tim Cirksena
     */
    @FXML
    private void onClick100Euro(ActionEvent event) {
        moneyID.setText("100");
        event.consume();
    }

    /**
     * back button navigiert eine Scene zurück.
     *
     * @author Tim Cirksena
     * Source: selber erstellt
     */
    @FXML
    private void onActionGuthabenAufladenBackBtn(ActionEvent event) {
        stage.setScene(RudisDampfkesselApp.getScenes().get(SceneName.GUTHABENVERWALTEN).getScene());
        event.consume();
    }

    /**
     * Diese Methode dient als Absicherung, dass auch nur double Werte im Textfeld sind.
     *
     * @author Tim Cirksena
     */
    private double sicherung() {
        double tmp;
        try {
            tmp = Double.parseDouble(moneyID.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0.0;
        }
        return tmp;
    }

    /**
     * Dies ist der ButtonOnCLickListener, sie ruft die Methode "guthabenAufladen" auf
     *
     * @param event
     * @author Tim Cirksena
     */
    @FXML
    private void onActionGuthabenAufladenBtn(ActionEvent event) {
        guthabenAufladen(sicherung());
        event.consume();
    }

    /**
     * Diese Methode ist für das Geldaufladen und das reinschreiben in die Datenbank zuständig.
     * @param geld
     * @author Tim Cirksena
     */
    private void guthabenAufladen(double geld) {
        conn.updateGuthaben(activeUser, geld);
        conn.commit();
        RudisDampfkesselApp.getScenes().get(SceneName.SHOP_ITEM).getGuthabenListner().updateGuthaben();
        RudisDampfkesselApp.getScenes().get(SceneName.GUTHABENVERWALTEN).getGuthabenListner().updateGuthaben();
    }

    @Override
    public void setActiveUser(String uname) {
        this.activeUser = uname;
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
    public void cleanTextFields() {
        this.moneyID.setText("");
    }
}
