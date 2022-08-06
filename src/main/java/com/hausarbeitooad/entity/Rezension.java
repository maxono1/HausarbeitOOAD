package com.hausarbeitooad.entity;

import java.util.InputMismatchException;

/**
 *  Die Entität Rezension, repräsentiert die Entität in der Datenbank, und fungiert als Schnittstelle zwischen Java und Sql
 *  es werden in den einzelnen Methoden invalide Werte abgefangen
 *
 *
 * @author 1st: Abdurrahman Azattemür, 2nd: Maximillian Jaesch
 * @source: Selbst erstellt für alle methoden
 * */
public class Rezension {
    private int spielID;
    private String bName;
    private int userBewertungProzent;
    private String text;

    /**
     * verwendet die Setter.
     *
     * @author Abdurrahman Azattemür
     * @param spielID
     * @param bName
     * @param userBewertungProzent
     * @param text
     * @throws InputMismatchException wenn name länger als 20 zeichen, wenn bewertung nicht zwischen 0 und 100 prozent
     * und wenn text länger als 3999 zeichen.
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
     * @throws InputMismatchException wenn name länger als 20 zeichen
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
     * @throws InputMismatchException wenn bewertung nicht zwischen 0 und 100 prozent
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
    /**
     * @author Maximilian Jaesch
     * @param text
     * @throws InputMismatchException wenn text länger als 3999 zeichen"
     */

    public void setText(String text) throws InputMismatchException{
        if(text.length() <= 3999){
            this.text = text;
        } else {
            this.text = text.substring(0,3998);
            throw new InputMismatchException("text länger als 3999 zeichen");
        }
    }


}
