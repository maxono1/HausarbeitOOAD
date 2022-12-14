package com.hausarbeitooad.controller;

import com.hausarbeitooad.RudisDampfkesselApp;
import com.hausarbeitooad.db.DatabaseConnection;
import com.hausarbeitooad.entity.Rezension;
import com.hausarbeitooad.model.AcceptsID;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Dieser Controller steuert die Rezension View
 *
 * @author Abdurrahman Azattemür
 */
public class RezensionViewController implements Stageable, Initializable, LoginListener, AcceptsID {

    private Stage stage;
    private String activeUser;
    private DatabaseConnection conn;
    private int spielID;
    @FXML
    private ListView<HBox> listViewID;

    /**
     * back button navigiert eine Scene zurück.
     *
     * @author Tim Cirksena
     * Source: selber erstellt
     * */
    @FXML
    private void onActionShopItemBackBtn(ActionEvent event) {
        stage.setScene(RudisDampfkesselApp.getScenes().get(SceneName.SHOP_ITEM).getScene());
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
    }

    /**
     * hier wird dynamisch ein HBox Element aus einer Rezension Entität erstellt,
     * um dies einer ListView hinzuzufügen
     *
     * @param rezension
     * @return HBox
     * @author 1st: Abdurrahman Azattemür, 2nd: Maximilian Jaesch
     */
    private HBox createHBoxForRezension(Rezension rezension) {
        Label usernameLabel = new Label(rezension.getbName());
        usernameLabel.setFont(Font.font(18.0));
        usernameLabel.setAlignment(Pos.CENTER);

        VBox usernameVBox = new VBox(usernameLabel);
        usernameVBox.setAlignment(Pos.CENTER_LEFT);
        usernameVBox.setPrefHeight(200);
        usernameVBox.setPrefWidth(100);

        Text beschreibungText = new Text(rezension.getText());
        beschreibungText.setFont(Font.font(18));

        VBox beschreibungVBox = new VBox(beschreibungText);
        beschreibungVBox.setAlignment(Pos.CENTER_LEFT);
        beschreibungVBox.setPrefHeight(200);
        beschreibungVBox.setPrefWidth(420);

        Label bewertungLabel = new Label("" + rezension.getUserBewertungProzent());
        bewertungLabel.setFont(Font.font(18.0));
        bewertungLabel.setAlignment(Pos.CENTER);

        VBox bewertungVBox = new VBox(bewertungLabel);
        bewertungVBox.setAlignment(Pos.CENTER_LEFT);
        bewertungVBox.setPrefHeight(200);
        bewertungVBox.setPrefWidth(150);

        return new HBox(usernameVBox, beschreibungVBox, bewertungVBox);
    }

    @Override
    public void setSpielID(int spielID) {
        this.spielID = spielID;
        listViewID.setItems(FXCollections.observableList(new ArrayList<HBox>()));
        try{
            List<Rezension> rezensionen = conn.retrieveRezensionen(this.spielID);
            for (Rezension r:rezensionen) {
                HBox rezensionBox = createHBoxForRezension(r);
                listViewID.getItems().add(rezensionBox);
            }
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);

        }
    }
}
