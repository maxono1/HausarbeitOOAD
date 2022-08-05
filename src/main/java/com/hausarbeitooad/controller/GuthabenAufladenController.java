package com.hausarbeitooad.controller;

import com.hausarbeitooad.SceneFxmlApp;
import com.hausarbeitooad.db.DatabaseConnection;
import com.hausarbeitooad.model.LoginListener;
import com.hausarbeitooad.model.SceneName;
import com.hausarbeitooad.model.Stageable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class GuthabenAufladenController implements Stageable, Initializable, LoginListener {

    private Stage stage;
    @FXML
    private ImageView arrowLeftID;

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
        guthabenAufladen(20);
        event.consume();
    }

    @FXML
    private void onClick50Euro(ActionEvent event) {
        guthabenAufladen(50);
        event.consume();
    }

    @FXML
    private void onClick100Euro(ActionEvent event) {
        guthabenAufladen(100);
        event.consume();
    }

    @FXML
    private void onActionGuthabenAufladenBackBtn(ActionEvent event) {
        stage.setScene(SceneFxmlApp.getScenes().get(SceneName.GUTHABENVERWALTEN).getScene());
        event.consume();
    }

    @FXML
    private void onActionGuthabenAufladenBtn(ActionEvent event) {
        //guthabenAufladen();

    }

    private void guthabenAufladen(double geld){
        conn.updateGuthaben(activeUser, geld);
        conn.commit();
        SceneFxmlApp.getScenes().get(SceneName.SHOP_ITEM).getGuthabenListner().updateGuthaben();
        SceneFxmlApp.getScenes().get(SceneName.GUTHABENVERWALTEN).getGuthabenListner().updateGuthaben();
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

}
