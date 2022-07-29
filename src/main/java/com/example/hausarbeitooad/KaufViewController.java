package com.example.hausarbeitooad;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class KaufViewController {


    @FXML
    private Label gameNameID;

    @FXML
    private TextField tfTitle;

    @FXML
    void btnOkClicked(ActionEvent event) {
        Stage mainWindow = (Stage) tfTitle.getScene().getWindow();
        String title = tfTitle.getText();
        mainWindow.setTitle(title);
    }

}
