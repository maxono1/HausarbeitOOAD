package com.hausarbeitooad.entity;
import java.util.InputMismatchException;

/**
 * Entität für Nutzer Besitzt. Repräsentiert die Entität in der Datenbank, und fungiert als Schnittstelle zwischen Java und Sql
 * es werden in den einzelnen Methoden invalide Werte abgefangen.
 *
 *
 * @author 1st: Maximilian Jaesch, 2nd: Tim Cirksena
 * @source: selber erstellt
 * */
public class NutzerBesitzt {
    private int spielID;
    private int spielzeit;
    private String bName = "test";

    /**
     * verwendet die setter
     *
     * @author Maximilian Jaesch
     * @param spielID
     * @param bName
     * @param spielzeit
     * @throws InputMismatchException wenn name länger als 20 zeichen ist oder Spielzeit kleiner als null ist.
     */
    public NutzerBesitzt(int spielID, String bName, int spielzeit) throws InputMismatchException {
        this.spielID = spielID;
        setSpielzeit(spielzeit);
        setbName(bName);
    }
    public int getSpielzeit() {
        return spielzeit;
    }

    public int getSpielID() {
        return spielID;
    }

    public String getbName() {

        return bName;
    }

    /**
     * @author 1st: Maximilian Jaesch
     * @param bName
     * @throws InputMismatchException wenn name länger als 20 zeichen ist
     */

    public void setbName(String bName) throws InputMismatchException{
        if(bName.length() > 20){
            throw new InputMismatchException("name länger als 20 zeichen");
        }
        this.bName = bName;
    }

    /**
     * @author Cirksena
     * @param spielzeit
     * @throws InputMismatchException wenn Spielzeit kleiner als null ist
     */
    public void setSpielzeit(int spielzeit) throws InputMismatchException{
        if(spielzeit < 0) {
            throw new InputMismatchException("Spielzeit ist kleiner als null");
        }
        this.spielzeit = spielzeit;
    }
}
