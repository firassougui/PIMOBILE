/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Button;
import com.codename1.ui.CN;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import com.mycompany.services.ServiceEvenement;
import com.mycompany.services.UploadServices;


import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * GUI builder created Form
 *
 * @author Firas
 */
public class AddEvent extends BaseForm {
    ServiceEvenement ad =new ServiceEvenement();
    String path;
    String filePath;
    Container imgCtn;
    ImageViewer l = new ImageViewer();
    String FilenameInserver = "";
    UploadServices uploadservices = new UploadServices();
 

    public AddEvent() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    protected String saveFileToDevice(String hi, String ext) throws IOException{
        URI uri;
        try {
            uri = new URI(hi);
            String path = uri.getPath();
            int index = hi.lastIndexOf("/");
            hi = hi.substring(index + 1);
            return hi;
        } catch (URISyntaxException ex) {
        }
        return "hh";
    }
    
    public AddEvent(com.codename1.ui.util.Resources resourceObjectInstance) {
       imgCtn = new Container();

        initGuiBuilderComponents(resourceObjectInstance);

        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Ajout evenement ", "Title")
                )
        );

        installSidemenu(resourceObjectInstance);

        //getToolbar().addCommandToRightBar("", resourceObjectInstance.getImage("contact-b.png"), e -> {
        // });

        Container center = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        center.setUIID("SignUpCenter");

        Container row1 = new Container(new GridLayout(1, 2));

        TextField user = new TextField();
       
        user.setUIID("SignUpField");
        user.setHint("user");
        user.getHintLabel().setUIID("SignupFieldHint");

        row1.addComponent(new Label("user :"));
        row1.addComponent(user);
        center.addComponent(row1);
        
        
        
        TextField titre = new TextField();
        titre.setUIID("SignUpField");
        titre.setHint("titre");
        titre.getHintLabel().setUIID("SignupFieldHint");
        
        Container row3 = new Container(new GridLayout(1, 2));
          
        row3.add(new Label("Titre :")).add(titre);
        center.addComponent(row3);
        center.setScrollableY(true);
        
        
        Picker date_start =new Picker();
        date_start.setType(Display.PICKER_TYPE_DATE);
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

        TextField nbpar = new TextField("", "1234", 4, TextField.NUMERIC);
        nbpar.setUIID("SignUpField");
        nbpar.setHint("npbpar");
        nbpar.getHintLabel().setUIID("SignupFieldHint");
        row2.add(new Label("Nombre participants :")).addComponent(nbpar);
          center.addComponent(row2);
        
        
        
        
        TextField type = new TextField();
        type.setUIID("SignUpField");
        
        Container row4 = new Container(new GridLayout(1, 2));
        type.setHint("type");
        type.getHintLabel().setUIID("SignupFieldHint");
        row4.add(new Label("type :")).addComponent(type);
        center.addComponent(row4);

        TextField description = new TextField();
        description.setUIID("SignUpField");

        Container row5 = new Container(new GridLayout(1, 2));
        description.setHint("description");
        description.getHintLabel().setUIID("SignupFieldHint");
         row5.add(new Label("description")).add(description);
        center.addComponent(row5);

        Button imageev = new Button("choisir image");

        

        center.add(imageev);

        this.addComponent(center);
        //  this.addComponent(getimage);
        Button Ajouter = new Button("Ajouter event");

        Container btnC = new Container();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
        
     
        btnC.add(Ajouter);
        this.add(btnC);
                imageev.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               Display.getInstance().openGallery(new ActionListener() {
                   
                   public void actionPerformed(final ActionEvent evt) {
                       if (evt == null) {
                           ToastBar.Status s = ToastBar.getInstance().createStatus();
                           s.setMessage("User Cancelled Gallery");
                           s.setMessageUIID("Title");
                           Image i = FontImage.createMaterial(FontImage.MATERIAL_ERROR, UIManager.getInstance().getComponentStyle("Title"));
                           s.setIcon(i);
                           System.out.println(i);
                           s.setExpires(2000);
                           s.show();
                           return;
                       }
                       String file = (String) evt.getSource();
                       System.out.println("pathhhh:" + file);
                       String path = file.substring(7);
                       System.out.println(path);
                       FilenameInserver = path;
                       System.out.println("nom image" + FilenameInserver);
                       
                   }
               }, Display.GALLERY_IMAGE);
               
               Media m;
               try {
                   m = MediaManager.createMedia("audio/browse.mp3", true);
                   m.play();
               } catch (IOException ex) {
                   
               }
           }
       });
        
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
                                System.out.println(user.getText());
                                System.out.println(titre.getText());
                                
            ad.addevent( Integer.parseInt(user.getText()), titre.getText(),date_start.getDate(), date_end.getDate(),Integer.parseInt(nbpar.getText()) , Integer.parseInt(type.getText()), description.getText(), FilenameInserver);

//Adresse a = new Adresse (adresse.getText(),Pays.getText(),Comp.getText(),cp.getText(),ville.getText());
                            }
                            });
       
    }
                
                

//-- DON'T EDIT BELOW THIS LINE!!!
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
    }
//-- DON'T EDIT ABOVE THIS LINE!!!
}
