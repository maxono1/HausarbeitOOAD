package com.hausarbeitooad.entity;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.InputMismatchException;

/**
 *  Die Entität Spiel, repräsentiert die Entität in der Datenbank, und fungiert als Schnittstelle zwischen Java und Sql
 *  es werden in den einzelnen Methoden invalide Werte abgefangen
 *
 * @author 1st: Maximillian Jaesch, 2nd: Tim Cirkena
 * @source: Selbst erstellt für alle methoden
 * */
public class Spiel {
    private int spielID;
    private  String name;
    private String beschreibung;
    private double preis;
    private String genre;
    private int bewertungProzent;
    private byte[] logo;
    private byte[] titelbild; //eig mediumblob


    /**
     * @author Maximilian Jaesch
     * @param spielID
     * @param name
     * @param beschreibung
     * @param preis
     * @param genre
     * @param bewertungProzent
     * @param logo
     * @param titelbild
     * @throws IOException
     */
    public Spiel(int spielID, String name, String beschreibung, double preis, String genre, int bewertungProzent, InputStream logo, InputStream titelbild) throws IOException{
        this.spielID = spielID;
        this.name = name;
        setBeschreibung(beschreibung);
        setPreis(preis);
        setGenre(genre);
        setBewertungProzent(bewertungProzent);
        setLogo(logo);
        setTitelbild(titelbild);
    }


    /**
     * minimierter konstruktor, um die anfrage an die datenbank minimieren zu können,
     * der Rest der elemente bleibt null
     * @author Tim Cirksena
     *
     * @param name
     * @param spielID
     * @param logo
     * @throws IOException
     */
    public Spiel(String name, int spielID, InputStream logo) throws IOException {
        this.spielID = spielID;
        this.name = name;
        setLogo(logo);
    }

    public int getSpielID() {
        return spielID;
    }

    public void setSpielID(int spielID) {
        this.spielID = spielID;
    }

    public String getName() {
        return name;
    }

    /**
     *
     * @author Maximilian Jaesch
     * @param name
     * @throws InputMismatchException wenn name länger als 255 chars
     * @source: Selbst erstellt
     */
    public void setName(String name) throws InputMismatchException{
        if(name.length() <=255){
            this.name = name;
        } else {
            this.name = name.substring(0,254);
            throw new InputMismatchException("name länger als 255 chars");
        }
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    /**
     * @author Maximilian Jaesch
     * @param beschreibung
     * @throws InputMismatchException wenn beschreibung länger als 2048 chars
     * @source: Selbst erstellt
     */
    public void setBeschreibung(String beschreibung) throws InputMismatchException{
        if(beschreibung.length() <=2048) {
            this.beschreibung = beschreibung;
        } else {
            this.beschreibung = beschreibung.substring(0,2047);
            throw new InputMismatchException("beschreibung länger als 2048 chars");
        }
    }

    public double getPreis() {
        return preis;
    }

    /**
     * @author Maximilian Jaesch
     * @param preis
     * @throws InputMismatchException wenn preis kleiner als 0 oder größer als 1000
     *
     */
    public void setPreis(double preis) throws InputMismatchException{
        if(preis >=0 && preis <= 1000){
            this.preis = preis;
        } else {
            this.preis = 0;
            throw new InputMismatchException("preis nicht zwischen 0 und 1000");
        }

    }

    public String getGenre() {
        return genre;
    }

    /**
     * @author Maximilian Jaesch
     * @param genre
     * @throws InputMismatchException wenn genre länger als 255 chars ist
     *
     */
    public void setGenre(String genre) throws InputMismatchException{
        if(genre.length() <=255){
            this.genre = genre;
        } else {
            this.genre = genre.substring(0,254);
            throw new InputMismatchException("genre länger als 255 chars");
        }

    }

    public int getBewertungProzent() {
        return bewertungProzent;
    }

    /**
     * länger als 255 chars
     * @param bewertungProzent
     * @throws InputMismatchException wenn bewertung nicht zwischen 0 und 100 prozent
     *
     */
    public void setBewertungProzent(int bewertungProzent) throws InputMismatchException {
        if(bewertungProzent >=0 && bewertungProzent <=100){
            this.bewertungProzent = bewertungProzent;
        } else {
            this.bewertungProzent = 0;
            throw new InputMismatchException("bewertung nicht zwischen 0 und 100 prozent");
        }
    }

    public byte[] getLogo() {
        return logo;
    }

    /**
     * nimmt einen Inputstream und speichert diesen in das byte array logo ab
     * aus diesem byte array kann später wieder mehrmals ein InputStream erstellt werden um JavaFX
     * Imageviews zu befüllen
     *
     * @param logoStream
     * @throws IOException wenn der InputStream fehlerhaft ist
     * @author 1st: Maximillian Jaesch
     *
     */
    public void setLogo(InputStream logoStream) throws IOException {
        this.logo = logoStream.readAllBytes();
    }

    public byte[] getTitelbild() {
        return titelbild;
    }

    /**
     * nimmt einen Inputstream und speichert diesen in das byte array logo ab
     * aus diesem byte array kann später wieder mehrmals ein InputStream erstellt werden um JavaFX
     * Imageviews zu befüllen
     *
     * @param titelbild
     * @throws IOException wenn der InputStream fehlerhaft ist
     * @author 1st: Maximillian Jaesch
     *
     */
    public void setTitelbild(InputStream titelbild) throws IOException{
        this.titelbild = titelbild.readAllBytes();
    }
}
