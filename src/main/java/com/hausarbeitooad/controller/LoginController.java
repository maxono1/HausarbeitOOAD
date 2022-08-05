package com.hausarbeitooad.controller;

import com.hausarbeitooad.SceneFxmlApp;
import com.hausarbeitooad.db.DatabaseConnection;
import com.hausarbeitooad.model.LoginListener;
import com.hausarbeitooad.model.SceneName;
import com.hausarbeitooad.model.Stageable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Stageable, Initializable, LoginListener {

    private Stage stage;
    private DatabaseConnection conn;

    @FXML
    private PasswordField passwordID;

    @FXML
    private TextField usernameID;

    @FXML
    private Label errorTextID;
    private String activeUser;

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        conn = DatabaseConnection.getInstance();
    }
    @Override
    public void setActiveUser(String uname) {
        this.activeUser = uname;
    }
    @FXML
    private void handleOnSubmitButtonClicked(ActionEvent event) {
        if (this.usernameID.getText().equals("") || this.passwordID.getText().equals("")) {
            this.errorTextID.setVisible(true);
            this.errorTextID.setText("Username or Password missing.");
        }

        if (conn.selectUser(this.usernameID.getText(), this.passwordID.getText())){
            this.errorTextID.setVisible(false);
            SceneFxmlApp.getScenes().get(SceneName.MAIN).getLoginListener().setActiveUser(this.usernameID.getText());
            SceneFxmlApp.getScenes().get(SceneName.GUTHABENVERWALTEN).getLoginListener().setActiveUser(this.usernameID.getText());
            SceneFxmlApp.getScenes().get(SceneName.GUTHABENAUFLADEN).getLoginListener().setActiveUser(this.usernameID.getText());
            SceneFxmlApp.getScenes().get(SceneName.GAME_DETAIL_VIEW).getLoginListener().setActiveUser(this.usernameID.getText());
            SceneFxmlApp.getScenes().get(SceneName.REVIEW_VIEW).getLoginListener().setActiveUser(this.usernameID.getText());
            SceneFxmlApp.getScenes().get(SceneName.LOGIN).getLoginListener().setActiveUser(this.usernameID.getText());
            SceneFxmlApp.getScenes().get(SceneName.SHOP_MENU).getLoginListener().setActiveUser(this.usernameID.getText());
            SceneFxmlApp.getScenes().get(SceneName.SHOP_ITEM).getLoginListener().setActiveUser(this.usernameID.getText());
            SceneFxmlApp.getScenes().get(SceneName.COLLECTION_VIEW).getLoginListener().setActiveUser(this.usernameID.getText());
            stage.setScene(SceneFxmlApp.getScenes().get(SceneName.MAIN).getScene());
        } else {
            this.errorTextID.setVisible(true);
            this.errorTextID.setText("Username or Password incorrect.");
        }
        //System.out.println(query);

        //System.out.println("clicked on login");
        //stage.setScene(SceneFxmlApp.getScenes().get(SceneName.MAIN).getScene());
        //hier raussoaken und abfragen ob das stimmt
        //maybe irgendwie die current session zwischenspeichern? oder nächsten inkrement
        //
        event.consume();
    }
}
