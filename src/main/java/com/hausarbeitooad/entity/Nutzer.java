package com.hausarbeitooad.entity;

import java.util.InputMismatchException;

public class Nutzer {
    private String bName = "test";
    private String password = "5555";

    public Nutzer(String bName, String password) {
        setbName(bName);
        setPassword(password);
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
}
