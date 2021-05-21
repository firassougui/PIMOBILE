/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Candidature;
import com.mycompany.entities.Opportunite;
import com.mycompany.entities.Task;
import com.mycompany.services.ServiceTask;

/**
 *
 * @author bhk
 */
public class DeleteCandidatureForm extends Form{

    public DeleteCandidatureForm(Form previous,int id) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("Add a new Candidature");
        setLayout(BoxLayout.y());
        
        TextField Titre = new TextField("","Titre");
        TextField Fonction= new TextField("", "Fonction");
        TextField Type_Contrat = new TextField("","Type_Contrat");
        TextField Horaires= new TextField("", "Horaires");
        TextField Mode_Salaire = new TextField("","Mode_Salaire");
        TextField Periode= new TextField("", "Periode");
        TextField Annuel_mois = new TextField("","Annuel_mois");
      Titre.setText(""+id);
        Titre.setEditable(false);
        Button btnValider = new Button("Add Candidature");
        
        btnValider.addActionListener((ActionEvent evt) -> {
            if ((Fonction.getText().length()==0)
               ||(Type_Contrat.getText().length()==0)||(Horaires.getText().length()==0)
               ||(Mode_Salaire.getText().length()==0)||(Periode.getText().length()==0)
               ||(Annuel_mois.getText().length()==0))     
                  
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            else
            {
                try {      

                    Candidature t = new Candidature(Titre.getText(),Fonction.getText(),Type_Contrat.getText(),Horaires.getText(),Mode_Salaire.getText(), Periode.getText(),Integer.parseInt(Annuel_mois.getText()) );
                    if( ServiceTask.getInstance().addCandidature(t))
                        Dialog.show("Success","Connection accepted",new Command("OK"));
                    else
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "Nbre recrutement and taille_entre must be a number", new Command("OK"));
                }
                
            }
        });
       
      
        addAll(Titre,Fonction,Type_Contrat,Horaires,Mode_Salaire,Periode,Annuel_mois,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                
    }
    
    
}
