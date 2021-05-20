/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.ArrayList;

/**
 *
 * @author bhk
 */
public class Publication {

    private int id;
    private int categorie_id ; 
    private String titre, description;
    private int vus ; 
    private int likes ; 
    private String date ; 
    private String img ; 
    private ArrayList<Post> Comments;

    public Publication() {
    }
    public Publication(int id, String titre, String description, ArrayList<Post> Comments) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.Comments = Comments;
    }

    public Publication(int id,int categorie_id, String titre, String description, int vus , int likes , String date , String img) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.categorie_id=categorie_id;
        this.img=img; 
        this.date=date; 
        this.likes=likes ; 
        this.vus=vus;
    }

    public Publication(String titre, String description) {
        this.titre = titre;
        this.description = description;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id= categorie_id;
    }

    public int getVus() {
        return vus;
    }

    public void setVus(int vus) {
        this.vus = vus;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    

    public ArrayList<Post> getComments() {
        return Comments;
    }

    public void setComments(ArrayList<Post> Comments) {
        this.Comments = Comments;
    }

    @Override
    public String toString() {
        return "Publication{" + "id=" + id + ", categorie_id=" + categorie_id + ", titre=" + titre + ", description=" + description + ", vus=" + vus + ", likes=" + likes + ", date=" + date + ", img=" + img + ", Comments=" + Comments + '}';
    }
    

}
