package com.hausarbeitooad.controller;

import com.hausarbeitooad.SceneFxmlApp;
import com.hausarbeitooad.model.SceneName;
import com.hausarbeitooad.model.Stageable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller für
 *
 * @author
 */
public class SceneTwoController implements Stageable, Initializable {
    private Stage stage;

    @FXML
    private MenuBar menuBar;

    @FXML
    private ScrollPane scrollPane;

    /**
     * Close application
     */
    @FXML
    private void handleOnActionClose(ActionEvent event) {
        stage.close();
        event.consume();
    }

    /**
     * Back to the main scene
     */
    @FXML
    private void handleOnActionBackButton(ActionEvent event) {
        stage.setScene(SceneFxmlApp.getScenes().get(SceneName.MAIN).getScene());
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
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setPannable(true);
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
}
