package com.hausarbeitooad.entity;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.InputMismatchException;

public class Spiel {
    private int spielID;
    private  String name;
    private String beschreibung;
    private double preis;
    private String genre;
    private int bewertungProzent;
    private byte[] logo;
    private byte[] titelbild; //eig mediumblob

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

    public int getSpielID() {
        return spielID;
    }

    public void setSpielID(int spielID) {
        this.spielID = spielID;
    }

    public String getName() {
        return name;
    }

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

    public void setLogo(InputStream logoStream) throws IOException {
        this.logo = logoStream.readAllBytes();
    }

    public byte[] getTitelbild() {
        return titelbild;
    }

    public void setTitelbild(InputStream titelbild) throws IOException{
        this.titelbild = titelbild.readAllBytes();
    }
}
