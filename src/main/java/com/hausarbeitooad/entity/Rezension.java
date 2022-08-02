package com.hausarbeitooad.entity;

import java.util.InputMismatchException;

public class Rezension {
    private int spielID;
    private String bName;
    private int userBewertungProzent;
    private String text;

    public int getSpielID() {
        return spielID;
    }

    public void setSpielID(int spielID) {
        this.spielID = spielID;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        if(bName.length() > 20){
            throw new InputMismatchException("name länger als 20 zeichen");
        }
        this.bName = bName;
    }

    public int getUserBewertungProzent() {
        return userBewertungProzent;
    }

    public void setUserBewertungProzent(int userBewertungProzent) {
        if(userBewertungProzent >=0 && userBewertungProzent <=100){
            this.userBewertungProzent = userBewertungProzent;
        } else {
            this.userBewertungProzent = 0;
            throw new InputMismatchException("bewertung nicht zwischen 0 und 100 prozent");
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        if(text.length() <= 3999){
            this.text = text;
        } else {
            this.text = text.substring(0,3998);
        }
    }

    public Rezension(int spielID, String bName, int userBewertungProzent, String text) {
        this.spielID = spielID;
        this.setbName(bName);
        this.setUserBewertungProzent(userBewertungProzent);
        this.setText(text);
    }
}
