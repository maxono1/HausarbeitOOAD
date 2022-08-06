package com.hausarbeitooad.entity;

import java.util.InputMismatchException;

/**
 *  Die Entität Rezension, repräsentiert die Entität in der Datenbank, und fungiert als Schnittstelle zwischen Java und Sql
 *  es werden in den einzelnen Methoden invalide Werte abgefangen
 *
 * @throws InputMismatchException
 * @author 1st: Abdurrahman Azattemür, 2nd: Maximillian Jaesch
 * @source: Selbst erstellt
 * */
public class Rezension {
    private int spielID;
    private String bName;
    private int userBewertungProzent;
    private String text;

    /**
     * @author Abdurrahman Azattemür
     * @param spielID
     * @param bName
     * @param userBewertungProzent
     * @param text
     * @throws InputMismatchException
     */
    public Rezension(int spielID, String bName, int userBewertungProzent, String text) throws InputMismatchException{
        this.spielID = spielID;
        this.setbName(bName);
        this.setUserBewertungProzent(userBewertungProzent);
        this.setText(text);
    }

    public int getSpielID() {
        return spielID;
    }

    public void setSpielID(int spielID) {
        this.spielID = spielID;
    }

    public String getbName() {
        return bName;
    }

    /**
     * @author Abdurrahman Azattemür
     * @param bName
     * @throws InputMismatchException
     */
    public void setbName(String bName) throws InputMismatchException{
        if(bName.length() > 20){
            throw new InputMismatchException("name länger als 20 zeichen");
        }
        this.bName = bName;
    }

    public int getUserBewertungProzent() {
        return userBewertungProzent;
    }

    /**
     * @author Abdurrahman Azattemür
     * @param userBewertungProzent
     * @throws InputMismatchException
     */
    public void setUserBewertungProzent(int userBewertungProzent) throws InputMismatchException {
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


}
