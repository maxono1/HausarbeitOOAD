package com.hausarbeitooad.entity;

import java.util.InputMismatchException;

public class Nutzer {
    private String bName = "test";
    private String password = "5555";
    private double guthaben = 0;

    public Nutzer(String bName, String password, double guthaben) {
        setbName(bName);
        setPassword(password);
        setGuthaben(guthaben);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(password.length() > 20){
            throw new InputMismatchException("pw länger als 20 zeichen");
        }
        this.password = password;
    }

    public double getGuthaben() {
        return guthaben;
    }

    public void setGuthaben(double guthaben) {
        if (this.guthaben < 0){
            throw new InputMismatchException("Guthaben kann nicht unter 0 sein");
        }
        this.guthaben = guthaben;
    }
}
