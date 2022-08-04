package com.hausarbeitooad.controller;

import com.hausarbeitooad.SceneFxmlApp;
import com.hausarbeitooad.entity.Spiel;
import com.hausarbeitooad.model.Loggerble;
import com.hausarbeitooad.model.SceneName;
import com.hausarbeitooad.model.Stageable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class CollectionViewController implements Stageable, Initializable, Loggerble {

    private Stage stage;
    @FXML
    private ImageView arrowLeftID1;

    @FXML
    private Label gameNameID;

    @FXML
    void onActionCollectionBackBtn(ActionEvent event) {
        stage.setScene(SceneFxmlApp.getScenes().get(SceneName.MAIN).getScene());
        event.consume();
    }

    @Override
    public void setActiveUser(String uname) {

    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private HBox createHBoxFromSpiel(Spiel spiel){
        ImageView spielImageView = new ImageView(new Image(new ByteArrayInputStream(spiel.getLogo())));
        spielImageView.setFitHeight(150);
        spielImageView.setFitWidth(200);
        spielImageView.setPickOnBounds(true);
        spielImageView.setPreserveRatio(true);

        Label spielIDLabel = new Label(Integer.toString(spiel.getSpielID()));
        spielIDLabel.setFont(Font.font(24));
        spielIDLabel.setAlignment(Pos.CENTER);
        spielIDLabel.setPrefHeight(54);
        spielIDLabel.setPrefWidth(206.0);

        VBox spielIDVbox = new VBox(spielIDLabel);
        spielIDVbox.setAlignment(Pos.CENTER_LEFT);
        spielIDVbox.setPrefWidth(100);
        spielIDVbox.setPrefHeight(150);

        Label spielNameLabel = new Label(spiel.getName());
        spielNameLabel.setFont(Font.font(36));
        spielNameLabel.setAlignment(Pos.CENTER);
        spielNameLabel.setPrefHeight(54);
        spielNameLabel.setPrefWidth(206.0);

        VBox spielNameVbox = new VBox(spielNameLabel);
        spielNameVbox.setAlignment(Pos.CENTER_LEFT);
        spielNameVbox.setPrefWidth(243);
        spielNameVbox.setPrefHeight(150);

        Label spielZeitLabel = new Label("0 std");
        spielZeitLabel.setAlignment(Pos.CENTER);

        VBox spielZeitVbox = new VBox(spielZeitLabel);
        spielNameVbox.setAlignment(Pos.CENTER_LEFT);
        spielNameVbox.setPrefWidth(218);
        spielNameVbox.setPrefHeight(150);

        return new HBox(spielImageView, spielIDVbox, spielNameVbox, spielZeitVbox);
    }
}
