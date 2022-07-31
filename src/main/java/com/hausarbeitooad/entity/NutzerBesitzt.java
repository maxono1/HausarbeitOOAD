package com.hausarbeitooad.entity;

public class NutzerBesitzt {
    private int spielID;
    private String bName;

    public NutzerBesitzt(int spielID, String bName) {
        this.spielID = spielID;
        this.bName = bName;
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
        this.bName = bName;
    }
}
