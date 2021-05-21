/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobilePIDEV.entites;

import javafx.scene.image.ImageView;

/**
 *
 * @author Ahmed
 */
public class employer {
    
    
    private int id ;
    private int employeur_id ;
    private String name;
    private String mdp;
    private String mail;
    private int num ;
    private String localisation;
    private String categorie;
    private String img;
      
        private int cin;
       
        private int numtel; 
     
       
        private String roles;
        private String photo;
        private boolean isblocked;
        private String niveau;
        private ImageView image;
        private String activation_token;
        
        
         @Override
    public String toString() {
        return "employer{" + "id=" + id + ", employeur_id=" + employeur_id + ", name=" + name + ", mdp=" + mdp + ", mail=" + mail + ", num=" + num +", localisation=" + localisation +", categorie=" + categorie + ", img=" + img + '}';
    }

    public employer() {
    }
  public employer(int id, int employeur_id, String name, String mdp, String mail, int num, String localisation, String categorie, String img) {
        this.id = id;
        this.employeur_id = employeur_id;
        this.name = name;
        this.mdp = mdp;
        this.mail = mail;
        this.num = num;
        this.localisation = localisation;
        this.categorie = categorie;
        this.img = img;
    }
   public employer(int employeur_id, String name, String mdp, String mail, int num, String localisation, String categorie, String img) {
        this.employeur_id = employeur_id;
        this.name = name;
        this.mdp = mdp;
        this.mail = mail;
        this.num = num;
        this.localisation = localisation;
        this.categorie = categorie;
        this.img = img;
    }
    public employer(String name, String mail) {
        this.name = name;
        this.mail = mail;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   

    

   

    public void setCin(int cin) {
        this.cin = cin;
    }

    public int getNum() {
        return numtel;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getEmployeur_id() {
        return employeur_id;
    }

    public void setEmployeur_id(int employeur_id) {
        this.employeur_id = employeur_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
   
    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean getIsblocked() {
        return isblocked;
    }

    public void setIsblocked(boolean isblocked) {
        this.isblocked = isblocked;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }


   

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public String getActivation_token() {
        return activation_token;
    }

    public void setActivation_token(String activation_token) {
        this.activation_token = activation_token;
    }
    
    
    

 
    
   
}
