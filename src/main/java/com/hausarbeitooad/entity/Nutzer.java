package com.hausarbeitooad.entity;

import java.util.InputMismatchException;

/**
 * Entität für Nutzer, repräsentiert die Entität in der Datenbank, und fungiert als Schnittstelle zwischen Java und Sql
 * es werden in den einzelnen Methoden invalide Werte abgefangen
 *
 * @author 1st: Maximilian Jaesch, 2nd: Abdurrahman Azattemür
 * @source: selber erstellt
 */
public class Nutzer {
    private String bName = "test";
    private String password = "5555";
    private double guthaben = 0;

    /**
     * @author Maximilian Jaesch
     * @param bName
     * @param password
     * @param guthaben
     * @throws InputMismatchException wenn name/pw länger als 20 zeichen ist, wenn guthaben < 0
     */
    public Nutzer(String bName, String password, double guthaben) throws InputMismatchException {
        setbName(bName);
        setPassword(password);
        setGuthaben(guthaben);
    }

    public String getbName() {
        return bName;
    }

    /**
     * @author Maximilian Jaesch
     * @param bName
     * @throws InputMismatchException wenn name länger als 20 zeichen ist
     */
    public void setbName(String bName) throws InputMismatchException {
        if (bName.length() > 20) {
            throw new InputMismatchException("name länger als 20 zeichen");
        }
        this.bName = bName;
    }

    public String getPassword() {
        return password;
    }

    /**
     * @author Maximilian Jaesch
     * @param password
     * @throws InputMismatchException wenn pw länger als 20 zeichen ist
     */
    public void setPassword(String password) throws InputMismatchException {
        if (password.length() > 20) {
            throw new InputMismatchException("pw länger als 20 zeichen");
        }
        this.password = password;
    }

    public double getGuthaben() {
        return guthaben;
    }

    /**
     * @author Abdurrahman Azattemür
     * @param guthaben
     * @throws InputMismatchException wenn guthaben < 0
     */
    public void setGuthaben(double guthaben) throws InputMismatchException {
        if (this.guthaben < 0) {
            throw new InputMismatchException("Guthaben kann nicht unter 0 sein");
        }
        this.guthaben = guthaben;
    }
}
