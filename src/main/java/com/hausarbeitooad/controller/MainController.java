package com.hausarbeitooad.controller;

import com.hausarbeitooad.RudisDampfkesselApp;
import com.hausarbeitooad.model.LoginListener;
import com.hausarbeitooad.model.SceneName;
import com.hausarbeitooad.model.Stageable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 *
 * @author Abdurrahman Azattemür
 */
public class MainController implements Stageable, Initializable, LoginListener {
    private Stage stage;


    private String activeUser;
    @FXML
    private Label usernameID;

    /**
     * Close application
     */
    @FXML
    private void handleOnActionLogout(ActionEvent event) {
        stage.setScene(RudisDampfkesselApp.getScenes().get(SceneName.LOGIN).getScene());
        event.consume();
    }

    /**
     * Display the first scene
     */
    @FXML
    private void handleOnActionShop(ActionEvent event) {
        stage.setScene(RudisDampfkesselApp.getScenes().get(SceneName.SHOP_MENU).getScene());
        event.consume();
    }

    /**
     * Display the third scene
     */
    @FXML
    private void handleOnActionSceneCollection(ActionEvent event) {
        stage.setScene(RudisDampfkesselApp.getScenes().get(SceneName.COLLECTION_VIEW).getScene());
        event.consume();
    }

    /**
     * Needed by the close button
     *
     * @param stage primary stage to set
     */
    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void handleonActionGuthabenScene(ActionEvent event) {
        stage.setScene(RudisDampfkesselApp.getScenes().get(SceneName.GUTHABENVERWALTEN).getScene());
        event.consume();
    }
    public void  handleOnActionOptionen(ActionEvent event) {
        stage.setScene(RudisDampfkesselApp.getScenes().get(SceneName.OPTION_VIEW).getScene());
        event.consume();
    }

    @Override
    public void setActiveUser(String uname) {
        this.activeUser = uname;
        usernameID.setVisible(true);
        usernameID.setText(activeUser);
    }
}
