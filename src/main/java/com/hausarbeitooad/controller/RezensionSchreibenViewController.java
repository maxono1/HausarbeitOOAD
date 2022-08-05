package com.hausarbeitooad.controller;

import com.hausarbeitooad.SceneFxmlApp;
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

public class RezensionSchreibenViewController implements Stageable, Initializable, LoginListener, AcceptsID, CleaningListener {

    private Stage stage;
    @FXML
    private ImageView dragImageReviewID;

    @FXML
    private ImageView arrowLeftID;

    @FXML
    private Label gameNameID;

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

    @FXML
    void onActionReviewBackBtn(ActionEvent event) {
        stage.setScene(SceneFxmlApp.getScenes().get(SceneName.GAME_DETAIL_VIEW).getScene());
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


    @FXML
    void onActionSubmitButton(ActionEvent event){
        try{
            System.out.println(this.spielID);
            conn.insertRezension(new Rezension(this.spielID, this.activeUser, Integer.parseInt(this.bewertungID.getText()), this.opinionReviewID.getText()));
        } catch (SQLException sqlException){
            DatabaseConnection.printSQLException(sqlException);
        }
    }

    public void cleanTextFields(){
        this.opinionReviewID.setText("");
        this.bewertungID.setText("");
    }
}
