package com.hausarbeitooad.entity;

import java.util.InputMismatchException;

public class NutzerBesitzt {
    private int spielID;
    private int spielzeit;
    private String bName = "test";

    public NutzerBesitzt(int spielID, String bName, int spielzeit) {
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
    public void setSpielzeit(int spielzeit){
        if(spielzeit < 0) {
            throw new InputMismatchException("Spielzeit ist kleiner als null");
        }
        this.spielzeit = spielzeit;
    }
}
