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
 * Dieser Controller steuert die Main View
 *
 * @author 1st: Abdurrahman Azattemür, 2nd: Tim Cirksena, 3rd: Maximilian Jaesch
 */
public class MainController implements Stageable, Initializable, LoginListener {
    private Stage stage;
    private String activeUser;
    @FXML
    private Label usernameID;

    /**
     * Dieser EventListener ist für das Logout zuständig.
     *
     * @param event
     * @author 1st: Abdurrahman Azattemür, 2nd: Maximilian Jaesch
     */
    @FXML
    private void handleOnActionLogout(ActionEvent event) {
        stage.setScene(RudisDampfkesselApp.getScenes().get(SceneName.LOGIN).getScene());
        event.consume();
    }

    /**
     * Dieser EventListener ist für das Wechseln in die ShopMenu zuständig.
     *
     * @param event
     * @author 1st: Abdurrahman Azattemür, 2nd: Maximilian Jaesch
     */
    @FXML
    private void handleOnActionShop(ActionEvent event) {
        stage.setScene(RudisDampfkesselApp.getScenes().get(SceneName.SHOP_MENU).getScene());
        event.consume();
    }

    /**
     * Dieser EventListener ist für das Wechseln in die CollectionView zuständig.
     *
     * @param event
     * @author 1st: Tim Cirksena, 2nd: Maximilian Jaesch
     */
    @FXML
    private void handleOnActionSceneCollection(ActionEvent event) {
        stage.setScene(RudisDampfkesselApp.getScenes().get(SceneName.COLLECTION_VIEW).getScene());
        event.consume();
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    /**
     * Dieser EventListener ist für das Wechseln in die GuthabenVerwaltenView zuständig.
     *
     * @param event
     * @author Abdurrahman Azattemür
     */
    public void handleonActionGuthabenScene(ActionEvent event) {
        stage.setScene(RudisDampfkesselApp.getScenes().get(SceneName.GUTHABENVERWALTEN).getScene());
        event.consume();
    }

    /**
     * Dieser EventListener ist für das Wechseln in die OptionsView zuständig.
     *
     * @param event
     * @author Tim Cirksena
     */
    public void handleOnActionOptionen(ActionEvent event) {
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
