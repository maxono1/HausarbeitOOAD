package com.hausarbeitooad;

import com.hausarbeitooad.db.DatabaseConnection;
import com.hausarbeitooad.entity.Nutzer;
import com.hausarbeitooad.entity.NutzerBesitzt;
import com.hausarbeitooad.entity.Rezension;
import com.hausarbeitooad.entity.Spiel;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * lädt beispiel Daten in die Datenbank um die UI zu testen
 *
 * @author 1st: Maximilian Jaesch, 2nd: Abdurrahman Azattemür
 * @Source: selbst erstellt
 * */

public class BeispielDatenLoader {
    private DatabaseConnection conn;
    public BeispielDatenLoader(DatabaseConnection conn){
        this.conn = conn;
        loadBsp();
    }
    private void loadBsp(){
        try{
            ArrayList<Spiel> bspSpiele = new ArrayList<>();
            bspSpiele.add(new Spiel(1, "banana Guys", "Don't Fall in the Slime", 20.0, "Horrorspiel", 50, new FileInputStream("src/main/resources/images/bananen_game.jpg"), new FileInputStream("src/main/resources/images/bananen_game.jpg")));
            bspSpiele.add(new Spiel(2, "Car Drivers", "Ride or Die, super speed",40.0,"Racing", 90, new FileInputStream("src/main/resources/images/car.png"), new FileInputStream("src/main/resources/images/car.png")));
            bspSpiele.add(new Spiel(3, "XTreme Bike Ride", "Race down a mountain to be the winner baby", 90.55, "Racing, Thriller", 20, new FileInputStream("src/main/resources/images/bike.png"), new FileInputStream("src/main/resources/images/bike.png")));
            bspSpiele.add(new Spiel(4, "Astronaut Game explore", "explore the galaxy to become rich and influential!", 1.5, "space", 99, new FileInputStream("src/main/resources/images/galaxy.jpg"), new FileInputStream("src/main/resources/images/galaxy.jpg")));
            for (int i = 5; i < 15; i++){
                bspSpiele.add(new Spiel(i,"Spiel"+i,"Beschreibung" + i, 50, "genre", 20, new FileInputStream("src/main/resources/images/placeholder_grafik.png") ,new FileInputStream("src/main/resources/images/placeholder_grafik.png")));
            }
            Nutzer maxi = new Nutzer("maxi", "1234", 100.0);
            Nutzer tim = new Nutzer("tim", "1234", 0);
            NutzerBesitzt nutzerBesitzt = new NutzerBesitzt(1, "maxi", 5);
            Rezension rezension = new Rezension(1, maxi.getbName(), 75, "jo, war super Spiel, habe ich mit Freuden spielen dürfen.");

            for (Spiel s: bspSpiele){
                conn.insertSpiel(s);
            }
            conn.insertNutzer(maxi);
            conn.insertNutzer(tim);
            conn.insertNutzerBesitzt(nutzerBesitzt);
            conn.insertRezension(rezension);
            conn.commit();
        } catch (IOException ioException){
            ioException.printStackTrace();
        } catch (SQLException sqlException){
            DatabaseConnection.printSQLException(sqlException);
        }
    }
}
