/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author Firas
 */
public class Participation {
     private int id ;
    private String date;
    private int id_employer;
    private int id_event ;
    private String titre;
    private String email;

    public Participation(String date, int id_employer, int id_event, String titre, String email) {
        this.date = date;
        this.id_employer = id_employer;
        this.id_event = id_event;
        this.titre = titre;
        this.email = email;
    }

    public Participation(int id, String date, int id_employer, int id_event, String titre, String email) {
        this.id = id;
        this.date = date;
        this.id_employer = id_employer;
        this.id_event = id_event;
        this.titre = titre;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Participation(String date, int id_employer, int id_event, String titre) {
        this.date = date;
        this.id_employer = id_employer;
        this.id_event = id_event;
        this.titre = titre;
    }

    public Participation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId_employer() {
        return id_employer;
    }

    public void setId_employer(int id_employer) {
        this.id_employer = id_employer;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
    
}
