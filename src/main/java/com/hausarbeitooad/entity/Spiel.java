package com.hausarbeitooad.entity;

import java.sql.Blob;

public class Spiel {
    private int spielID;
    private  String name;
    private String beschreibung;
    private double preis;
    private String genre;
    private int bewertungProzent;
    private Blob logo;
    private Blob titelbild; //eig mediumblob
}
