package com.hausarbeitooad.controller;

import com.hausarbeitooad.SceneFxmlApp;
import com.hausarbeitooad.db.DatabaseConnection;
import com.hausarbeitooad.entity.Spiel;
import com.hausarbeitooad.model.Loggerble;
import com.hausarbeitooad.model.SceneName;
import com.hausarbeitooad.model.Stageable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class CollectionViewController implements Stageable, Initializable, Loggerble {

    private Stage stage;
    private DatabaseConnection conn;
    private String activeUser;
    @FXML
    private ImageView arrowLeftID1;

    @FXML
    private Label gameNameID;
    @FXML
    private ListView<HBox> listNameID;

    @FXML
    void onActionCollectionBackBtn(ActionEvent event) {
        stage.setScene(SceneFxmlApp.getScenes().get(SceneName.MAIN).getScene());
        event.consume();
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

        try {
            List<Spiel> spiele = conn.retrieveSpiele();
            for (Spiel s: spiele){
                HBox spielHbox = createHBoxFromSpiel(s);
                spielHbox.setOnMouseClicked( event -> {
                    Label idLabel = (Label) spielHbox.getChildren().get(1);
                    System.out.println(idLabel.getText());
                    int id = Integer.parseInt(idLabel.getText());
                    SceneFxmlApp.getScenes().get(SceneName.GAME_DETAIL_VIEW).getAcceptsID().setSpielID(id);
                    stage.setScene(SceneFxmlApp.getScenes().get(SceneName.GAME_DETAIL_VIEW).getScene());
                    event.consume();
                });
                listNameID.getItems().add(spielHbox);
            }
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
        }
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
