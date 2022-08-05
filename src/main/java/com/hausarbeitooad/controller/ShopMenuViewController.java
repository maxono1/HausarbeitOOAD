package com.hausarbeitooad.controller;

import com.hausarbeitooad.SceneFxmlApp;
import com.hausarbeitooad.entity.Spiel;
import com.hausarbeitooad.db.DatabaseConnection;
import com.hausarbeitooad.model.LoginListener;
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

public class ShopMenuViewController implements Stageable, Initializable, LoginListener {
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
    private String activeUser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //textField.setBackground(Background.fill(Color.AQUA));

        //listViewID.getItems().add("textField");
        /*
        Spiel fallGuys = null;
        try {
            fallGuys = new Spiel(1, "Fall Guys", "Don't Fall in the Slime", 1.0, "Horrorspiel", 50, new FileInputStream("src/main/resources/images/CSGO.png"), new FileInputStream("src/main/resources/images/CSGO.png"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException io){
            io.printStackTrace();
        }
        for (int i = 0; i < 50; i++){
            listViewID.getItems().add(createHBoxFromSpiel(fallGuys));
        }
        */
        conn = DatabaseConnection.getInstance();

        try {
            List<Spiel> spiele = conn.retrieveSpiele();
            for (Spiel s: spiele){
                HBox spielHbox = createHBoxFromSpiel(s);
                spielHbox.setOnMouseClicked( event -> {
                    Label idLabel = (Label) spielHbox.getChildren().get(1);
                    System.out.println(idLabel.getText());
                    int id = Integer.parseInt(idLabel.getText());
                    SceneFxmlApp.getScenes().get(SceneName.SHOP_ITEM).getAcceptsID().setSpielID(id);
                    stage.setScene(SceneFxmlApp.getScenes().get(SceneName.SHOP_ITEM).getScene());
                    event.consume();
                });
                listViewID.getItems().add(spielHbox);
            }
        } catch (SQLException e) {
            DatabaseConnection.printSQLException(e);
        }
    }
    @FXML
    private void onActionMenuItemBackBtn(ActionEvent event) {
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

    private HBox createHBoxFromSpiel(Spiel spiel){

        ImageView logoImageView = new ImageView(new Image(new ByteArrayInputStream(spiel.getLogo())));
        logoImageView.setFitHeight(68);
        logoImageView.setFitWidth(68);
        logoImageView.setPickOnBounds(true);
        logoImageView.setPreserveRatio(true);

        VBox logoVbox = new VBox(logoImageView);
        logoVbox.setAlignment(Pos.CENTER);
        logoVbox.setPrefHeight(70);
        logoVbox.setPrefWidth(100);

        Label idLabel = new Label(Integer.toString(spiel.getSpielID()));
        idLabel.setFont(Font.font(18.0));
        idLabel.setAlignment(Pos.CENTER);
        idLabel.setPrefHeight(70);
        idLabel.setPrefWidth(100);


        /*VBox idVbox = new VBox(idLabel);
        idVbox.setAlignment(Pos.CENTER);
        idVbox.setPrefHeight(70);
        idVbox.setPrefWidth(100);*/

        Label nameLabel = new Label(spiel.getName());
        nameLabel.setFont(Font.font(18.0));
        nameLabel.setAlignment(Pos.CENTER);

        VBox nameVbox = new VBox(nameLabel);
        nameVbox.setAlignment(Pos.CENTER);
        nameVbox.setPrefHeight(70);
        nameVbox.setPrefWidth(220);

        Label preisLabel = new Label(Double.toString(spiel.getPreis()) + "€");
        preisLabel.setFont(Font.font(18.0));
        preisLabel.setAlignment(Pos.CENTER);

        VBox preisVbox = new VBox(preisLabel);
        preisVbox.setAlignment(Pos.CENTER);
        preisVbox.setPrefHeight(70);
        preisVbox.setPrefWidth(280);

        return new HBox(logoVbox,idLabel,nameVbox,preisVbox);
    }
}
