/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.MultiButton;
import com.codename1.io.Storage;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Evenement;
import com.mycompany.services.ServiceEvenement;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * GUI builder created Form
 *
 * @author Firas
 */
public class EventsBack extends BaseForm {
Form current;
    public EventsBack() throws IOException {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public EventsBack(com.codename1.ui.util.Resources resourceObjectInstance) throws IOException {
        current=this;
        initGuiBuilderComponents(resourceObjectInstance);
               
     
      
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label(" Events ", "Title")
                )
        );
        installSidemenu(resourceObjectInstance);
        int t = EventsBack.this.getTintColor();
        EventsBack.this.setTintColor(0);
 
        Container Product = new Container(BoxLayout.y());
         Container searchContainer = new Container(BoxLayout.x());
        Style s = UIManager.getInstance().getComponentStyle("Title");
       

FontImage  searchIcon= FontImage.createMaterial(FontImage.MATERIAL_ADD, s);

               Button searchbt = new Button("Ajouter");
               searchbt.setIcon(searchIcon);
             searchbt.getAbsoluteX();
        

        Product.add(searchbt);
         searchbt.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                       // ProduitDetailsForm.produitS=p;

                        Form bdf = new AddEvent();
                        bdf.show();
                    }
                });
        
      
       
        // gui_Container_3_3.setUIID("List");
        //  gui_Container_3_3.setScrollableY(true);
        ArrayList<Evenement> products = ServiceEvenement.getInstance().getAllProduct();
        String img= "";

        Image imgg ;
        Image image = null;
        
        if (products != null) {
            for (Evenement p : products) {
                System.out.println(p.getTitre());
                
                if(p.getPhoto()!=null | p.getPhoto().length()>0)
                {
                if ("file".equals(p.getPhoto().substring(0,4)))
                {
      
                 img=p.getPhoto();
                 
                 
                }
                else if("C:/".equals(p.getPhoto().substring(0,3)))
                {
                    img="file:/"+p.getPhoto();
                }
                else
                    img="http://localhost/PIWEB/public/images/products/" + p.getPhoto();
                }
                EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(this.getWidth()/ 2 , this.getHeight() / 8, 0xFFFFFFFF), true);
                
           
                   image = URLImage.createToStorage(placeholder, p.getPhoto(),img, URLImage.RESIZE_SCALE_TO_FILL);
                  
                  
                
                 Container imgC = new Container();
                imgC.add(image);
                FontImage  iconmodif= FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, s);
                FontImage  iconsuo= FontImage.createMaterial(FontImage.MATERIAL_DELETE, s);
                Button par = new Button("supprimer");
                Button sup =new Button("Modifier");
                
                MultiButton mb = new MultiButton(p.getTitre());
                mb.setUIID("ListItem");
                mb.setTextLine2(p.getDescription());
                mb.setTextLine3("Nom par :" + Float.toString(p.getNombre_par()) );
                
                
                
                              //  mb.addActionListener(new ActionListener() {
                   // @Override
                   // public void actionPerformed(ActionEvent evt) {
                     
                        
                    //  ServiceEvenement.getInstance().ajoutPar(p.getId());
                    
    //}
           //     });
                
       
      mb.add(RIGHT, image);
      par.setIcon(iconsuo);
      sup.setIcon(iconmodif);
      
      par.getAllStyles().setAlignment(Component.CENTER);
      sup.getAllStyles().setAlignment(Component.CENTER);
      sup.addActionListener((e) -> {
           
           
       new Updevent(p,current).show();
          
    
        
       });
      
       par.addActionListener((e) -> {
           
            System.out.println(p.getId());
       boolean x=ServiceEvenement.getInstance().DeleteCandidature(p.getId());
          
    
        
       });
              

                
                
                /*      Depot.add(BorderLayout.CENTER, new Label("Center")).
    add(BorderLayout.SOUTH, new Label("South")).
    add(BorderLayout.NORTH, new Label("North")).
    add(BorderLayout.EAST, new Label("East")).
    add(BorderLayout.WEST, new Label("West"));
                
                */
                Product.add(FlowLayout.encloseCenter(mb));
                Product.add(sup);
                Product.add(par);

            }

            this.add(Product);
        }
        }


    
@Override
    protected boolean isCurrentInbox() {
        return true;
    }
//-- DON'T EDIT BELOW THIS LINE!!!

//-- DON'T EDIT ABOVE THIS LINE!!!

    private void initGuiBuilderComponents(Resources resourceObjectInstance) {
       setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("BACK");
        setName("BACK");
    }
}
