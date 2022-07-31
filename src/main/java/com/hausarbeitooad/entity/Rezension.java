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
        this.text = text;
    }

    public Rezension(int spielID, String bName, int userBewertungProzent, String text) {
        this.spielID = spielID;
        this.bName = bName;
        this.setUserBewertungProzent(userBewertungProzent);
        this.text = text;
    }
}
