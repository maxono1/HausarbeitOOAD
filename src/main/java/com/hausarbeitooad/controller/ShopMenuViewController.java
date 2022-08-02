package com.hausarbeitooad.controller;

import com.hausarbeitooad.entity.Spiel;
import com.hausarbeitooad.db.DatabaseConnection;
import com.hausarbeitooad.model.AcceptsDatabase;
import com.hausarbeitooad.model.Stageable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShopMenuViewController implements Stageable, Initializable, AcceptsDatabase {
    @FXML
    private ImageView arrowLeftID;

    @FXML
    private ListView<HBox> listViewID;
    //Label textField = new Label("LOL");
    //HBox hBox = new HBox(textField);

    @FXML
    private Label gameNameID;
    private DatabaseConnection conn;
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //textField.setBackground(Background.fill(Color.AQUA));

        //listViewID.getItems().add("textField");
        Spiel fallGuys = null;
        try {
            fallGuys = new Spiel(1, "Fall Guys", "Don't Fall in the Slime", 1.0, "Horrorspiel", 50, new FileInputStream("src/main/resources/images/CSGO.png"), new FileInputStream("src/main/resources/images/CSGO.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < 50; i++){
            listViewID.getItems().add(createHBoxFromSpiel(fallGuys));
        }

    }

    @Override
    public void setDatabaseConnection(DatabaseConnection conn) {
        this.conn = conn;
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public HBox createHBoxFromSpiel(Spiel spiel){

        ImageView logoImageView = new ImageView(new Image(spiel.getLogo()));
        logoImageView.setFitHeight(68);
        logoImageView.setFitWidth(68);
        logoImageView.setPickOnBounds(true);
        logoImageView.setPreserveRatio(true);

        VBox logoVbox = new VBox(logoImageView);
        logoVbox.setAlignment(Pos.CENTER);
        logoVbox.setPrefHeight(70);
        logoVbox.setPrefWidth(100);

        Label nameLabel = new Label(spiel.getName());
        nameLabel.setFont(Font.font(18.0));
        nameLabel.setAlignment(Pos.CENTER);

        VBox nameVbox = new VBox(nameLabel);
        nameVbox.setAlignment(Pos.CENTER);
        nameVbox.setPrefHeight(70);
        nameVbox.setPrefWidth(320);

        Label preisLabel = new Label(Double.toString(spiel.getPreis()));
        preisLabel.setFont(Font.font(18.0));
        preisLabel.setAlignment(Pos.CENTER);

        VBox preisVbox = new VBox(preisLabel);
        preisVbox.setAlignment(Pos.CENTER);
        preisVbox.setPrefHeight(70);
        preisVbox.setPrefWidth(280);


        return new HBox(logoVbox,nameVbox,preisVbox);
    }
}
