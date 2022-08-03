package com.hausarbeitooad.controller;

import com.hausarbeitooad.SceneFxmlApp;
import com.hausarbeitooad.db.DatabaseConnection;
import com.hausarbeitooad.model.AcceptsDatabase;
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

public class GuthabenAufladenController implements Stageable, Initializable {

    private Stage stage;
    @FXML
    private ImageView arrowLeftID;

    @FXML
    private Label gameNameID;

    @FXML
    private Button guthabenAufladenBtnID;
    private DatabaseConnection conn;

    @FXML
    void onActionGuthabenAufladenBackBtn(ActionEvent event) {
        stage.setScene(SceneFxmlApp.getScenes().get(SceneName.GUTHABENVERWALTEN).getScene());
        event.consume();
    }

    @FXML
    void onActionGuthabenAufladenBtn(ActionEvent event) {

    }



    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
