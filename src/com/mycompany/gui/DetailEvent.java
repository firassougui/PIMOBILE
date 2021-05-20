/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.MultiButton;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import static com.codename1.ui.plaf.Style.BACKGROUND_NONE;
import com.mycompany.entities.Evenement;
import com.mycompany.entities.Rating;
import com.mycompany.services.ServiceEvenement;
import com.mycompany.services.ServiceRating;
import java.util.ArrayList;

/**
 * GUI builder created Form
 *
 * @author Firas
 */
public class DetailEvent extends com.codename1.ui.Form {

   
    
    public DetailEvent(Evenement p, Form prev) {
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ARROW_BACK, e -> prev.showBack());
        this.setTitle("Details");
        this.setLayout(BoxLayout.y());
        MultiButton mb1 = new MultiButton();
        mb1.setTextLine1(p.getTitre());
        mb1.setTextLine3(p.getDescription());
        
        Label l=new Label("");
        if (p.getNombre_par()> 0 )
        {
            l.setText("Oui");
        }
        else {
            l.setText("Non");
        } 
         String img="";
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
                System.out.println(p.getTitre());
      
                EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(this.getWidth()/ 2 , this.getHeight() / 8, 0xFFFFFFFF), true);
                Image image = URLImage.createToStorage(placeholder, p.getPhoto(), img, URLImage.RESIZE_SCALE_TO_FILL);
                Container imgC = new Container();
                imgC.add(image);
            
        l.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
        mb1.setTextLine4("Disponible :"+l.getText()+" ( "+p.getNombre_par()+" En Stock )");
        mb1.setTextLine2(p.getDescription());
        
        mb1.getAllStyles().setBorder(RoundRectBorder.create().strokeColor(0).
                strokeOpacity(120));
        mb1.getAllStyles().setBackgroundType(BACKGROUND_NONE);
        mb1.getAllStyles().setBgTransparency(255);
        mb1.getAllStyles().setBgColor(0x2d283e);
        mb1.getAllStyles().setFgColor(0xd1d7e0);
        mb1.getAllStyles().setMargin(20, 20, 20, 20);
        
     /*  MultiButton mb2 = new MultiButton();
        mb2.getAllStyles().setBorder(RoundRectBorder.create().strokeColor(0).
                strokeOpacity(120));
        mb2.getAllStyles().setBackgroundType(BACKGROUND_NONE);
        mb2.getAllStyles().setBgTransparency(255);
        mb2.getAllStyles().setBgColor(0xff4d4d); 
        mb2.getAllStyles().setFgColor(0xd1d7e0);
        mb2.getAllStyles().setMargin(20, 20, 20, 20);
        mb2.setTextLine1("J'aime : "+p.getLike());
        mb2.setTextLine2("J'aime pas : "+p.getUnlike());*/
      Button b1 = new Button("Evaluer");
        ServiceRating ps = new  ServiceRating();
      Slider slide =ps.createStarRankSlider();
      Rating p1 = new Rating();
     
      
      b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               if( Dialog.show("Evaluation", Integer.toString(slide.getProgress())+" Etoiles pour ce produit ?", "Oui" ,"Annuler"))
               {
                   
                  
                   
                   ps.ajoutPar(p.getId(),slide.getProgress());
                   ToastBar.showMessage( p.getTitre()+" Evalué", FontImage.MATERIAL_THUMB_UP);
               }
                     
            }
        });
       Container b= new Container();
     ArrayList<Rating> products = ServiceRating.getInstance().getAllProductC(p.getId());
       
     if(products ==null)
     {
         
         slide.setProgress(0);
     }
     else{
          System.out.println("size"+products.size());
      // slide.setProgress(Float.valueOf(products.get(0).getStars()).intValue());
     }
     b.add(FlowLayout.encloseCenter(slide));
       this.add(imgC);
        this.add(mb1);
        this.add(b);
       this.add(b1);
        
        this.show();
    }

//-- DON'T EDIT BELOW THIS LINE!!!
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
    }
//-- DON'T EDIT ABOVE THIS LINE!!!
}
