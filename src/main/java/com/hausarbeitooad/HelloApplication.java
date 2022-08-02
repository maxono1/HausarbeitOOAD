package com.hausarbeitooad;

import com.hausarbeitooad.controller.HelloController;
import com.hausarbeitooad.db.DatabaseConnection;
import com.hausarbeitooad.entity.Nutzer;
import com.hausarbeitooad.entity.NutzerBesitzt;
import com.hausarbeitooad.entity.Rezension;
import com.hausarbeitooad.entity.Spiel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        HelloController helloController = fxmlLoader.getController();

        DatabaseConnection lol = new DatabaseConnection();

        try {
            Spiel fallGuys = new Spiel(1, "Fall Guys", "Don't Fall in the Slime", 1.0, "Horrorspiel", 50, new FileInputStream("src/main/resources/images/CSGO.png"), new FileInputStream("src/main/resources/images/CSGO.png"));
            Nutzer nutzer = new Nutzer("maxi", "12345", 100.0);
            NutzerBesitzt nutzerBesitzt = new NutzerBesitzt(1, "maxi");
            Rezension rezension = new Rezension(1, nutzer.getbName(), 75, "jo, war super Spiel, habe ich mit Freuden spielen dürfen.");
            //lol.insertImage(new FileInputStream("src/main/resources/images/CSGO.png"), "galaxie");
            lol.insertNutzer(nutzer);
            lol.insertSpiel(fallGuys);
            lol.insertNutzerBesitzt(nutzerBesitzt);
            lol.insertRezension(rezension);

        } catch (FileNotFoundException fnfe) {
            System.err.println("file not found");
        } catch (SQLException e) {
            System.err.println("some SQL Statements are broken.");
            DatabaseConnection.printSQLException(e);
            //throw new RuntimeException(e);
        }
        lol.selectQuery("Select * from Nutzer");
        lol.selectQuery("Select * from Nutzer_Besitzt");
        lol.selectQuery("Select * from Rezension");
        lol.selectQuery("Select * from Spiel");
        lol.closeDB();

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}