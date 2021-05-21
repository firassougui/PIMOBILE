/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.entities.Evenement;
import com.mycompany.services.ServiceEvenement;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * GUI builder created Form
 *
 * @author Firas
 */
public class Updevent extends BaseForm {
        ServiceEvenement ad =new ServiceEvenement();
    String path;
    String filePath;
    Container imgCtn;
    ImageViewer l = new ImageViewer();
    String FilenameInserver = "";

  
    
    public Updevent(Evenement p, Form prev) {
             imgCtn = new Container();

         System.out.println(p.getId());

     getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ARROW_BACK, e -> prev.showBack());
        this.setTitle("Modifier");
        this.setLayout(BoxLayout.y());

        Container center = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        center.setUIID("SignUpCenter");

      
     
        
        
        
        TextField titre = new TextField(p.getTitre(),"user", 20 ,TextField.ANY);
        titre.setUIID("SignUpField");
       
        titre.getHintLabel().setUIID("SignupFieldHint");
        
        Container row3 = new Container(new GridLayout(1, 2));
          
        row3.add(new Label("Titre :")).add(titre);
        center.addComponent(row3);
        center.setScrollableY(true);
        
        
        Picker date_start =new Picker();
        date_start.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        date_start.setUIID("SignUpField");
        Container row6 = new Container(new GridLayout(1, 2));
          
        row6.add(new Label("date Debut :")).add(date_start);
        center.addComponent(row6);
        
         Picker date_end =new Picker();
        date_end.setType(Display.PICKER_TYPE_DATE_AND_TIME);
    
        date_end.setUIID("SignUpField");
          Container row7 = new Container(new GridLayout(1, 2));
          
        row7.add(new Label("Date de la fin  :")).add(date_end);
        center.addComponent(row7);
  
        
        
        Container row2 = new Container(new GridLayout(1, 2));
        /*  
        TextField ville = new TextField();
       
        ville.setUIID("SignUpField");
        ville.setHint("ville");
        ville.getHintLabel().setUIID("SignupFieldHint");

        row1.addComponent(new Label("ville :"));
        row1.addComponent(ville);
        center.addComponent(row2);
       */ 

        TextField nbpar = new TextField(String.valueOf(p.getNombre_par()),"nbr", 20 ,TextField.ANY);
        nbpar.setUIID("SignUpField");
      
       
        nbpar.getHintLabel().setUIID("SignupFieldHint");
        
        row2.add(new Label("Nombre participants :")).addComponent(nbpar);
          center.addComponent(row2);
        
        
        
        
        TextField type = new TextField(String.valueOf(p.getId_type()),"type", 20 ,TextField.ANY);
        type.setUIID("SignUpField");
        
        Container row4 = new Container(new GridLayout(1, 2));
        
     
        row4.add(new Label("type :")).addComponent(type);
        center.addComponent(row4);

        TextField description = new TextField(p.getDescription(),"description", 20 ,TextField.ANY);
        description.setUIID("SignUpField");

        Container row5 = new Container(new GridLayout(1, 2));
        description.setHint("description");
        description.getHintLabel().setUIID("SignupFieldHint");
         row5.add(new Label("description")).add(description);
        center.addComponent(row5);

        

        

        

        this.addComponent(center);
        //  this.addComponent(getimage);
        Button Ajouter = new Button("Ajouter event");

        Container btnC = new Container();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
        
     
        btnC.add(Ajouter);
        this.add(btnC);
                                                Ajouter.addActionListener(new ActionListener (){
                            @Override
                            public void actionPerformed ( ActionEvent evt ){
                                      //  System.out.println(FilenameInserver);
                                      
                                Media m;
                                try {
                                    m = MediaManager.createMedia("audio/done.mp3", true);
                                    m.play();
                                } catch (IOException ex) {
                                   
                                }
                                
                                p.setTitre(titre.getText());
                                p.setDate_start(date_start.getDate());
                                p.setDate_end(date_end.getDate());
                                p.setNombre_par(Integer.parseInt(nbpar.getText()));
                                p.setId_type(Integer.parseInt(type.getText()));
                                p.setDescription(description.getText());
                                
                                
                                
            ad.modifierevent(p);

//Adresse a = new Adresse (adresse.getText(),Pays.getText(),Comp.getText(),cp.getText(),ville.getText());
                            }
                            });
    }

//-- DON'T EDIT BELOW THIS LINE!!!
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
    }
//-- DON'T EDIT ABOVE THIS LINE!!!

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }

    
    
  
}
