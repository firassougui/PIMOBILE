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
public class Rating {
    int id;
    int idEv;
    float stars;

    public int getId() {
        return id;
    }

    public Rating() {
    }

    public Rating(int idEv, float stars) {
        this.idEv = idEv;
        this.stars = stars;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEv() {
        return idEv;
    }

    public void setIdEv(int idEv) {
        this.idEv = idEv;
    }

    public float getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }
    
    
}
