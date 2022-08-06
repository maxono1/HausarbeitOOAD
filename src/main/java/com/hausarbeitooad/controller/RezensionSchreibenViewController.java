package com.hausarbeitooad.controller;

import com.hausarbeitooad.RudisDampfkesselApp;
import com.hausarbeitooad.db.DatabaseConnection;
import com.hausarbeitooad.entity.Rezension;
import com.hausarbeitooad.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Dieser Controller steuert die RezensionSchreiben View
 *
 * @author 1st: Abdurrahman Azattemür, 2nd: Maximilian Jaesch, 3rd: Tim Cirksena
 */
public class RezensionSchreibenViewController implements Stageable, Initializable, LoginListener, AcceptsID, CleaningListener {

    private Stage stage;
    @FXML
    private TextField bewertungID;
    @FXML
    private TextField opinionReviewID;
    private int spielID;
    private String activeUser;

    private DatabaseConnection conn;

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        conn = DatabaseConnection.getInstance();
    }

    /**
     * back button navigiert eine Scene zurück.
     *
     * @author Tim Cirksena
     * @Source: selber erstellt
     * */
    @FXML
    void onActionReviewBackBtn(ActionEvent event) {
        stage.setScene(RudisDampfkesselApp.getScenes().get(SceneName.GAME_DETAIL_VIEW).getScene());
        event.consume();
    }

    @Override
    public void setActiveUser(String uname) {
        this.activeUser = uname;
    }

    @Override
    public void setSpielID(int spielID) {
        this.spielID = spielID;
    }

    /**
     * Dieser onClickListener ist für das abschicken einer Rezension und das Eintragen dieser in die Datenbank zuständig
     *
     * @param event
     * @author 1st: Abdurrahman Azattemür, 2nd: Maximilian Jaesch
     */
    @FXML
    void onActionSubmitButton(ActionEvent event){
        try{
            conn.insertRezension(new Rezension(this.spielID, this.activeUser, Integer.parseInt(this.bewertungID.getText()), this.opinionReviewID.getText()));
            conn.commit();
        } catch (SQLException sqlException){
            if(sqlException.getSQLState().equals("23505")){
                //hier son popup einfügen
                System.out.println("sie haben schon eine rezension geschrieben");
            } else{
                DatabaseConnection.printSQLException(sqlException);
            }
        }
    }

    public void cleanTextFields(){
        this.opinionReviewID.setText("");
        this.bewertungID.setText("");
    }
}
