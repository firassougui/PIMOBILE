/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entities.Candidature;
import com.mycompany.myapp.entities.Opportunite;
import com.mycompany.myapp.services.ServiceTask;
import com.mycompany.myapp.services.ServiceTask1;
 
import java.util.ArrayList;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form {

    Form current;
    /*Garder traçe de la Form en cours pour la passer en paramètres 
    aux interfaces suivantes pour pouvoir y revenir plus tard en utilisant
    la méthode showBack*/
    
    public HomeForm() {
       Button btnAddTask = new Button("Add Offres");
     
        current = this; //Récupération de l'interface(Form) en cours
        setTitle("Listes Des offres");
          
 
        btnAddTask.addActionListener(e -> new AddTaskForm(current).show());       
             


          addAll(btnAddTask);
        ArrayList<Opportunite>list=ServiceTask.getInstance().getAllTasks();
Form previous;
       for (Opportunite Op: list)
        { 
       
          
         String UrlImage="c:\\pc.jpg";
         
         
         
         Image place=Image.createImage(120,90);
         EncodedImage enc=EncodedImage.createFromImage(place, false);
         
         URLImage urlim= URLImage.createToStorage(enc,UrlImage,UrlImage,URLImage.RESIZE_SCALE);
         
         addButton(urlim,Op.getId(),Op.getTitre(),Op.getLieu(),Op.getDescription(),Op); 
         
         ScaleImageLabel image= new ScaleImageLabel(urlim);
         Container Cimg= new Container();
         image.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_HORIZONTAL_ALIGN_BOTTOM);
            
        }
     }

    private void addButton( Image img,int id,String titre ,String Lieu,String Desc,Opportunite Op) {
        int height=Display.getInstance().convertToPixels(11.5f);
        int width=Display.getInstance().convertToPixels(15f);
        Button image=new Button(img.fill(width, height));
        
        image.setUIID("LabelSpan");
        Container cnt =new Container() ;
        cnt = (BorderLayout.west(image));
        Button btnModif = new Button("Update Offres"); 
       btnModif.addActionListener(e -> new UpdateOpporForm(current,id).show()); 
       
               TextArea idd=new TextArea("Id : "+id);
        idd.setUIID("List opp");
        idd.setEditable(false);
        
        
        
        TextArea ta=new TextArea("Titre : "+titre);
        ta.setUIID("List opp");
        ta.setEditable(false);
           TextArea lie=new TextArea("Lieu : "+Lieu);
        lie.setUIID("List opp");
        lie.setEditable(false);
        
           TextArea descr=new TextArea("Description : "+Desc);
        descr.setUIID("List opp");
        descr.setEditable(false);
        cnt.add(BorderLayout.EAST,BoxLayout.encloseY(btnModif,ta,lie,descr));
         add(cnt);
   
            image.addActionListener(e -> new ListTasksForm(id).show());
        
             

    }

}
