package com.hausarbeitooad.controller;

import com.hausarbeitooad.db.DatabaseConnection;
import com.hausarbeitooad.model.AcceptsDatabase;
import com.hausarbeitooad.model.Stageable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ShopMenuViewController implements Stageable, Initializable, AcceptsDatabase {
    @FXML
    private ImageView arrowLeftID;

    @FXML
    private ListView<String> listViewID;
    Label textField = new Label("LOL");
    HBox hBox = new HBox(textField);

    @FXML
    private Label gameNameID;
    private DatabaseConnection conn;
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textField.setBackground(Background.fill(Color.AQUA));

        listViewID.getItems().add("textField");
    }

    @Override
    public void setDatabaseConnection(DatabaseConnection conn) {
        this.conn = conn;
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
