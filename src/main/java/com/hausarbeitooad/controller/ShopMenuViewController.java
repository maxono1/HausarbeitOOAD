package com.hausarbeitooad.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class ShopMenuViewController implements Initializable {
    @FXML
    private ImageView arrowLeftID;

    @FXML
    private ListView<String> listViewID;
    Label textField = new Label("LOL");
    HBox hBox = new HBox(textField);

    @FXML
    private Label gameNameID;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textField.setBackground(Background.fill(Color.AQUA));

        listViewID.getItems().add("textField");
    }
}
