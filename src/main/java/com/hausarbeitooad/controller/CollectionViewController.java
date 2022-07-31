package com.hausarbeitooad.controller;

import com.hausarbeitooad.SceneFxmlApp;
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

public class CollectionViewController implements Stageable, Initializable {

    private Stage stage;
    @FXML
    private ImageView arrowBackCollectedGameID;

    @FXML
    private Label gameNameID;

    @FXML
    private Button rezensionSchreibenID;

    @FXML
    void handleOnActionRezensionSchreiben(ActionEvent event) {
        stage.setScene(SceneFxmlApp.getScenes().get(SceneName.REVIEW_VIEW).getScene());
        event.consume();
    }
    @FXML
    void onActionCollectionBackBtn(ActionEvent event) {
        stage.setScene(SceneFxmlApp.getScenes().get(SceneName.MAIN).getScene());
        event.consume();
    }


    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
