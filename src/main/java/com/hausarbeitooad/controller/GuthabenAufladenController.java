package com.hausarbeitooad.controller;

import com.hausarbeitooad.SceneFxmlApp;
import com.hausarbeitooad.db.DatabaseConnection;
import com.hausarbeitooad.model.AcceptsDatabase;
import com.hausarbeitooad.model.Loggerble;
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

public class GuthabenAufladenController implements Stageable, Initializable, Loggerble {

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
        conn.updateGuthaben(activeUser, 20.00);
        SceneFxmlApp.getScenes().get(SceneName.SHOP_ITEM).getGuthaberble().updateGuthaben();
        event.consume();
    }

    @FXML
    private void onClick50Euro(ActionEvent event) {
        conn.updateGuthaben(activeUser, 50.00);
        conn.commit();
        SceneFxmlApp.getScenes().get(SceneName.SHOP_ITEM).getGuthaberble().updateGuthaben();
        event.consume();
    }

    @FXML
    private void onClick100Euro(ActionEvent event) {
        conn.updateGuthaben(activeUser, 100.00);
        conn.commit();
        SceneFxmlApp.getScenes().get(SceneName.SHOP_ITEM).getGuthaberble().updateGuthaben();
        event.consume();
    }

    @FXML
    private void onActionGuthabenAufladenBackBtn(ActionEvent event) {
        stage.setScene(SceneFxmlApp.getScenes().get(SceneName.GUTHABENVERWALTEN).getScene());
        conn.commit();
        event.consume();
    }

    @FXML
    private void onActionGuthabenAufladenBtn(ActionEvent event) {

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
