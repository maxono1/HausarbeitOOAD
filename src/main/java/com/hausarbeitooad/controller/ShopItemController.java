package com.hausarbeitooad.controller;

import com.hausarbeitooad.SceneFxmlApp;
import com.hausarbeitooad.db.DatabaseConnection;
import com.hausarbeitooad.model.AcceptsDatabase;
import com.hausarbeitooad.model.AcceptsID;
import com.hausarbeitooad.model.Loggerble;
import com.hausarbeitooad.model.SceneName;
import com.hausarbeitooad.model.Stageable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ShopItemController implements Stageable, Initializable, Loggerble, AcceptsID {


    @FXML
    private Label gameNameID;
    @FXML
    private TextField tfTitle;
    private DatabaseConnection conn;
    private Stage stage;
    private String activeUser;
    private int spielID;
    @FXML
    private Label guthabenInItemViewID;
    @FXML
    private void btnOkClicked(ActionEvent event) {
        Stage mainWindow = (Stage) tfTitle.getScene().getWindow();
        String title = tfTitle.getText();
        mainWindow.setTitle(title);
    }
    @Override
    public void setActiveUser(String uname) {
        this.activeUser = uname;
        System.out.println(uname);
        guthabenInItemViewID.setVisible(true);
        guthabenInItemViewID.setText( ""+conn.selectGuthaben(uname));
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        conn = DatabaseConnection.getInstance();
    }
    @Override
    public void setSpielID(int spielID) {
        this.spielID = spielID;
    }

    private void kaufen(){

    }
    @FXML
    private void onActionKaufenBackBtn(ActionEvent event) {
        stage.setScene(SceneFxmlApp.getScenes().get(SceneName.SHOP_MENU).getScene());
        event.consume();
    }

    /**
     * Funktion: von dem active user guthaben laden
     * */
    private void guthabenLaden(){

    }

}
