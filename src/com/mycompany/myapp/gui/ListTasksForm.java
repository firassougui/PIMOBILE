/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

 
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
 
import com.mycompany.myapp.entities.Candidature;
import com.mycompany.myapp.services.ServiceTask;
import com.mycompany.myapp.services.ServiceTask1;
  
import java.util.ArrayList;

/**
 *
 * @author bhk
 */
public class ListTasksForm extends Form{
Form previous;
 Form current;
           Button Preview;

 
    public ListTasksForm(int id) {
 
        ListTasksForm current = this; //Récupération de l'interface(Form) en cours
        setTitle("Listes Des Candidatures");
        Button btnAddCand= new Button("Add Candidatures");
      
             btnAddCand.addActionListener(e -> new AddCandidatureForm(current,id).show()); 
        
   addAll(btnAddCand);
      
         
        ArrayList<Candidature>list=ServiceTask1.getInstance().getAllTasks();
        for (Candidature Op: list)
        {
          
         
         Image place=Image.createImage(120,90);
         EncodedImage enc=EncodedImage.createFromImage(place, false);
         
         Button lim= new Button();
         
         addButton(lim,id,Op.getId(),Op.getTitre_id(),Op.getFonction(),Op); 
         
        
       
         }                       Preview=new Button("Preview");
int i=0;
if(i<1)
{
        
     Preview.setUIID("LabelSpan");
        Container cnt1 =new Container() ;
          cnt1 = (BorderLayout.west(Preview));
         add(cnt1);
     Preview.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                    
new HomeForm().show();
            }
            });
}
     }

    private void addButton( Button b,int id,int identif,String  titre,String fonction ,Candidature Cand) {
    

        int height=Display.getInstance().convertToPixels(11.5f);
        int width=Display.getInstance().convertToPixels(15f);
        ArrayList<Candidature>list=ServiceTask1.getInstance().getAllTasks();
     
      String Id="{id="+id+".0}";
        
         if(titre.equals(Id))
         {
        Button contact=new Button("Contacter"); 
      Button btnDelCand= new Button("Delete Candidatures"+identif);   
           TextArea idd=new TextArea("Num Candidature : "+identif);
        idd.setUIID("List opp");
        idd.setEditable(false);
        
        contact.setUIID("LabelSpan");
        Container cnt =new Container() ;
        
        cnt = (BorderLayout.west(contact));
         

        TextArea tit=new TextArea("Titre : "+titre);
        tit.setUIID("List opp");
        tit.setEditable(false);
        TextArea fonc=new TextArea("Fonction : "+fonction);
        fonc.setUIID("List opp");
        fonc.setEditable(false);
        
    
      
           
        cnt.add(BorderLayout.EAST,BoxLayout.encloseY(idd,fonc));
  
         add(cnt);
        addAll(btnDelCand);
  contact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
             ServiceTask1.getInstance().getconn(id);
            
            }
            });   
     
        btnDelCand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
             ServiceTask.getInstance().DeleteCandidature(identif);
            
            }
            });  
    } 
    }
    
    
    
}
