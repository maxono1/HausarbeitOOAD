package com.hausarbeitooad.controller;

import com.hausarbeitooad.RudisDampfkesselApp;
import com.hausarbeitooad.db.DatabaseConnection;
import com.hausarbeitooad.entity.Spiel;
import com.hausarbeitooad.model.BuyListner;
import com.hausarbeitooad.model.LoginListener;
import com.hausarbeitooad.model.SceneName;
import com.hausarbeitooad.model.Stageable;
import javafx.collections.FXCollections;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Dieser Controller steuert die Collection View
 *
 * @author 1st: Tim Cirksena, 2nd: Maximilian Jaesch
 */
public class CollectionViewController implements Stageable, Initializable, LoginListener, BuyListner {

    private Stage stage;
    private DatabaseConnection conn;
    private String activeUser;
    @FXML
    private ListView<HBox> listNameID;

    /**
     * back button navigiert eine Scene zurück.
     *
     * @author Tim Cirksena
     * Source: selber erstellt
     */
    @FXML
    void onActionCollectionBackBtn(ActionEvent event) {
        stage.setScene(RudisDampfkesselApp.getScenes().get(SceneName.MAIN).getScene());
        event.consume();
    }

    @Override
    public void setActiveUser(String uname) {
        this.activeUser = uname;
        updateGames();
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        conn = DatabaseConnection.getInstance();
    }


    /**
     * hier wird dynamisch ein HBox element aus einer Spiel entität und Spielzeit erstellt,
     * um dies einer ListView hinzuzufügen
     * <p>
     * Source: selber erstellt
     *
     * @author 1st: Maximilian Jaesch, 2nd:Tim Cirksena
     */
    private HBox createHBoxFromSpiel(Spiel spiel, int spielzeit) {
        ImageView spielImageView = new ImageView(new Image(new ByteArrayInputStream(spiel.getLogo())));
        spielImageView.setFitHeight(150);
        spielImageView.setFitWidth(200);
        spielImageView.setPickOnBounds(true);
        spielImageView.setPreserveRatio(true);

        Label spielIDLabel = new Label(Integer.toString(spiel.getSpielID()));
        spielIDLabel.setFont(Font.font(24));
        spielIDLabel.setAlignment(Pos.CENTER);
        spielIDLabel.setPrefHeight(150);
        spielIDLabel.setPrefWidth(100);

        Label spielNameLabel = new Label(spiel.getName());
        spielNameLabel.setFont(Font.font(36));
        spielNameLabel.setAlignment(Pos.CENTER);
        spielNameLabel.setPrefHeight(54);
        spielNameLabel.setPrefWidth(206.0);

        VBox spielNameVbox = new VBox(spielNameLabel);
        spielNameVbox.setAlignment(Pos.CENTER_LEFT);
        spielNameVbox.setPrefWidth(243);
        spielNameVbox.setPrefHeight(150);

        Label spielZeitLabel = new Label(Integer.toString(spielzeit) + " std");
        spielZeitLabel.setAlignment(Pos.CENTER);

        VBox spielZeitVbox = new VBox(spielZeitLabel);
        spielZeitVbox.setAlignment(Pos.CENTER);
        spielZeitVbox.setPrefWidth(218);
        spielZeitVbox.setPrefHeight(150);

        return new HBox(spielImageView, spielIDLabel, spielNameVbox, spielZeitVbox);
    }

    /**
     * hier wird dynamisch ein HBox element aus einer Spiel entität und Spielzeit erstellt,
     * um dies einer ListView hinzuzufügen
     * <p>
     * Source: selber erstellt
     *
     * @author 1st:Tim Cirksena 2nd: Maximilian Jaesch
     */
    @Override
    public void updateGames() {
        try {
            listNameID.setItems(FXCollections.observableList(new ArrayList<HBox>()));
            List<Spiel> spiele = conn.sammlungView(activeUser);

            for (Spiel s : spiele) {
                int spielzeit = conn.retrieveSpielzeitNutzerBesitzt(activeUser, s.getSpielID());
                HBox spielHbox = createHBoxFromSpiel(s, spielzeit);
                spielHbox.setOnMouseClicked(event -> {
                    Label idLabel = (Label) spielHbox.getChildren().get(1);
                    //System.out.println(idLabel.getText());
                    int id = Integer.parseInt(idLabel.getText());
                    RudisDampfkesselApp.getScenes().get(SceneName.GAME_DETAIL_VIEW).getAcceptsID().setSpielID(id);
                    stage.setScene(RudisDampfkesselApp.getScenes().get(SceneName.GAME_DETAIL_VIEW).getScene());
                    event.consume();
                });
                listNameID.getItems().add(spielHbox);
            }
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
        }
    }
}
