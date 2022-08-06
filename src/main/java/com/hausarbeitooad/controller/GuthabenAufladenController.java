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

public class GuthabenAufladenController implements Stageable, Initializable, LoginListener, CleaningListener {

    private Stage stage;
    @FXML
    private ImageView arrowLeftID;
    @FXML
    private TextField moneyID;
    @FXML
    private Label gameNameID;

    @FXML
    private Button euro100BtnID;

    @FXML
    private Button euro20BtnID;

    @FXML
    private Button euro50BtnID;

    @FXML
    private Button guthabenAufladenBtnID;
    private DatabaseConnection conn;
    private String activeUser;


    @FXML
    private void onClick20Euro(ActionEvent event) {
        moneyID.setText("20");
        event.consume();
    }

    @FXML
    private void onClick50Euro(ActionEvent event) {
        moneyID.setText("50");
        event.consume();
    }

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
     * */
    @FXML
    private void onActionGuthabenAufladenBackBtn(ActionEvent event) {
        stage.setScene(RudisDampfkesselApp.getScenes().get(SceneName.GUTHABENVERWALTEN).getScene());
        event.consume();
    }
    private double sicherung(){
        double tmp;
        try{
            tmp = Double.parseDouble(moneyID.getText());
        }catch (NumberFormatException e){
            e.printStackTrace();
            return 0.0;
        }
       return tmp;
    }

    @FXML
    private void onActionGuthabenAufladenBtn(ActionEvent event) {
        guthabenAufladen(sicherung());
        event.consume();
    }

    private void guthabenAufladen(double geld){
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
