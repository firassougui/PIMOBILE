/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.File;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Opportunite;
import com.mycompany.entities.Task;
import com.mycompany.services.ServiceTask;
 
import com.codename1.capture.Capture;
import com.codename1.ui.Display;
import java.io.IOException;
 
/**
 *
 * @author bhk
 */
public class UpdateOpporForm extends Form{

    public UpdateOpporForm(Form previous,int id) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("Update Opportunite");
        setLayout(BoxLayout.y());
      TextField Logo = new TextField("","UploadFile");
               Button upload = new Button("UploadFile");
Label image=new Label();

    upload.addActionListener((ActionEvent evt) -> {
        
        String path=Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);  
        if(path!=null)
        {
            try {
                Image img=Image.createImage(path);
                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        Logo.setText(path);
     });
    
        TextField idd = new TextField("","Identif");
        TextField Titre = new TextField("","Titre");
        TextField Lieu= new TextField("", "Lieu");
        TextField Desc = new TextField("","Description");
        TextField Op_employeur_id= new TextField("", "Nom_emp");
        TextField Nom_entreprise = new TextField("","Nom_entreprise");
        TextField Taille_entreprise= new TextField("", "Taille_entreprise");
        TextField Poste = new TextField("","Poste");
        TextField Media= new TextField("", "Media");
        TextField nombre_recrutement= new TextField("", "Nombre_recrutement");

           idd.setText(""+id);
                   idd.setEditable(false);

        Button btnValider = new Button("Update Opportunite");
        
        btnValider.addActionListener((ActionEvent evt) -> {
            if ((Titre.getText().length()==0)||(Lieu.getText().length()==0)
               ||(Desc.getText().length()==0)||(Op_employeur_id.getText().length()==0)
               ||(Nom_entreprise.getText().length()==0)||(Taille_entreprise.getText().length()==0)
               ||(Poste.getText().length()==0)||(Media.getText().length()==0)
                    ||(nombre_recrutement.getText().length()==0))     
                  
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            else
            {
                try {      

                    Opportunite t = new Opportunite(Integer.parseInt(Op_employeur_id.getText()),Titre.getText(),Lieu.getText(),Desc.getText(),Nom_entreprise.getText(),Taille_entreprise.getText(),
                   Poste.getText(),Media.getText(),Integer.parseInt(nombre_recrutement.getText()),Logo.getText());
                    if( ServiceTask.getInstance().UpdateOpportunite(t,id))
                        Dialog.show("Success","Element modifié avec sucées",new Command("OK"));
                    else
                       Dialog.show("Success","Element modifié avec sucées",new Command("OK"));               
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "Nbre recrutement and taille_entre must be a number", new Command("OK"));
                }
                
            }
        });
        
        addAll(upload,idd,Titre,Lieu,Desc,Op_employeur_id,Nom_entreprise,Taille_entreprise,Poste,Media,nombre_recrutement,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                
    }
    
    
}
