package com.hausarbeitooad.entity;

import java.io.FileInputStream;
import java.sql.Blob;
import java.util.InputMismatchException;

public class Spiel {
    private int spielID;
    private  String name;
    private String beschreibung;
    private double preis;
    private String genre;
    private int bewertungProzent;
    private FileInputStream logo;
    private FileInputStream titelbild; //eig mediumblob

    public Spiel(int spielID, String name, String beschreibung, double preis, String genre, int bewertungProzent, FileInputStream logo, FileInputStream titelbild) {
        this.spielID = spielID;
        this.name = name;
        this.beschreibung = beschreibung;
        setPreis(preis);
        this.genre = genre;
        setBewertungProzent(bewertungProzent);
        this.logo = logo;
        this.titelbild = titelbild;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
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

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getBewertungProzent() {
        return bewertungProzent;
    }

    public void setBewertungProzent(int bewertungProzent) throws InputMismatchException {
        if(bewertungProzent >=0 && bewertungProzent <=100){
            this.bewertungProzent = bewertungProzent;
        } else {
            bewertungProzent = 0;
            throw new InputMismatchException("bewertung nicht zwischen 0 und 100 prozent");
        }
    }

    public FileInputStream getLogo() {
        return logo;
    }

    public void setLogo(FileInputStream logo) {
        this.logo = logo;
    }

    public FileInputStream getTitelbild() {
        return titelbild;
    }

    public void setTitelbild(FileInputStream titelbild) {
        this.titelbild = titelbild;
    }
}
