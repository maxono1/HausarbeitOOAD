package com.hausarbeitooad.entity;

import java.util.InputMismatchException;

public class NutzerBesitzt {
    private int spielID;
    private String bName = "test";

    public NutzerBesitzt(int spielID, String bName) {
        this.spielID = spielID;
        setbName(bName);
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
}
