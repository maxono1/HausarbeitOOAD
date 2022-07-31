package com.hausarbeitooad.entity;

public class Nutzer {
    private String bName;
    private String password;

    public Nutzer(String bName, String password) {
        this.bName = bName;
        this.password = password;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
