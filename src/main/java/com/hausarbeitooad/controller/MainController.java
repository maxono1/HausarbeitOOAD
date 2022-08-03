package com.hausarbeitooad.controller;

import com.hausarbeitooad.db.DatabaseConnection;
import com.hausarbeitooad.SceneFxmlApp;
import com.hausarbeitooad.model.AcceptsDatabase;
import com.hausarbeitooad.model.SceneName;
import com.hausarbeitooad.model.Stageable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Pressing a button displays the different scenes.
 *
 * @author
 */
public class MainController implements Stageable, Initializable {
    private Stage stage;


    @FXML
    private MenuBar menuBar;

    /**
     * Close application
     */
    @FXML
    private void handleOnActionLogout(ActionEvent event) {
        stage.setScene(SceneFxmlApp.getScenes().get(SceneName.LOGIN).getScene());
        event.consume();
    }

    /**
     * Display the first scene
     */
    @FXML
    private void handleOnActionShop(ActionEvent event) {
        stage.setScene(SceneFxmlApp.getScenes().get(SceneName.SHOP_MENU).getScene());
        event.consume();
    }

    /**
     * Display the third scene
     */
    @FXML
    private void handleOnActionSceneCollection(ActionEvent event) {
        stage.setScene(SceneFxmlApp.getScenes().get(SceneName.COLLECTION_VIEW).getScene());
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
        menuBar.setFocusTraversable(true);
    }

    @FXML
    private void handleKeyInput(final InputEvent event) {
        if (event instanceof KeyEvent) {
            final KeyEvent keyEvent = (KeyEvent) event;
            if (keyEvent.isControlDown() && keyEvent.getCode() == KeyCode.A) {
                provideAboutFunctionality();
            }
        }
        event.consume();
    }

    private void provideAboutFunctionality() {
        System.out.println("You clicked on About!");
    }

    @FXML
    private void handleAboutAction(final ActionEvent event) {
        provideAboutFunctionality();
        event.consume();
    }

    public void handleonActionGuthabenScene(ActionEvent event) {
        stage.setScene(SceneFxmlApp.getScenes().get(SceneName.GUTHABENVERWALTEN).getScene());
        event.consume();
    }

}
