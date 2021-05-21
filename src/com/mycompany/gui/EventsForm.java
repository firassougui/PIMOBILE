/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.entities.Evenement;
import com.mycompany.services.ServiceEvenement;
import java.util.ArrayList;

/**
 * GUI builder created Form
 *
 * @author Firas
 */
public class EventsForm extends BaseForm{
Form current;
    public EventsForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    
    public EventsForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        current=this;
        initGuiBuilderComponents(resourceObjectInstance);
        installSidemenu(resourceObjectInstance);
 
        Container Product = new Container(BoxLayout.y());
        // gui_Container_3_3.setUIID("List");
        //  gui_Container_3_3.setScrollableY(true);
        ArrayList<Evenement> products = ServiceEvenement.getInstance().getAllProduct();
        
        String img="";
        if (products != null) {
            
            for (Evenement p : products) {
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
                Button par = new Button("participer");
                
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
                      mb.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                     
                        
                        new DetailEvent(p,current).show();
                    
    }
                });
      par.getAllStyles().setAlignment(Component.CENTER);
      
       par.addActionListener((e) -> {
           
            System.out.println(p.getId());
       boolean x=ServiceEvenement.getInstance().ajoutPar(p.getId());
          
       if(x)
       
       Dialogshow(p.getTitre());
       else
           Dialogshow1(p.getTitre());
        
       });
              

                
                
                /*      Depot.add(BorderLayout.CENTER, new Label("Center")).
    add(BorderLayout.SOUTH, new Label("South")).
    add(BorderLayout.NORTH, new Label("North")).
    add(BorderLayout.EAST, new Label("East")).
    add(BorderLayout.WEST, new Label("West"));
                
                */
                Product.add(FlowLayout.encloseCenter(mb));
                Product.add(par);

            }

            this.add(Product);
        }
    }
    public void Dialogshow(String titre)
    {
         Dialog dlg = new Dialog("participation succès");
    Style dlgStyle = dlg.getDialogStyle();
    dlgStyle.setBorder(Border.createEmpty());
    dlgStyle.setBgTransparency(255);
    dlgStyle.setBgColor(0xffffff);

    Label title = dlg.getTitleComponent();
    //title.setIcon(finalDuke.scaledHeight(title.getPreferredH()));
    title.getUnselectedStyle().setFgColor(0xff);
    title.getUnselectedStyle().setAlignment(Component.LEFT);

    dlg.setLayout(BoxLayout.y());
    Label blueLabel = new Label();
    blueLabel.setShowEvenIfBlank(true);
    blueLabel.getUnselectedStyle().setBgColor(0xff);
    blueLabel.getUnselectedStyle().setPadding(1, 1, 1, 1);
    blueLabel.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
    dlg.add(blueLabel);
    TextArea ta = new TextArea("A l'evenement:   "+titre);
    ta.setEditable(false);
    ta.setUIID("DialogBody");
    ta.getAllStyles().setFgColor(0);
    dlg.add(ta);

    Label grayLabel = new Label();
    grayLabel.setShowEvenIfBlank(true);
    grayLabel.getUnselectedStyle().setBgColor(0xcccccc);
    grayLabel.getUnselectedStyle().setPadding(1, 1, 1, 1);
    grayLabel.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
    dlg.add(grayLabel);

    Button ok = new Button(new Command("OK"));
    ok.getAllStyles().setBorder(Border.createEmpty());
    ok.getAllStyles().setFgColor(0);
    dlg.add(ok);
    dlg.showDialog();
    }
        public void Dialogshow1(String titre)
    {
         Dialog dlg = new Dialog("participation deja !!");
    Style dlgStyle = dlg.getDialogStyle();
    dlgStyle.setBorder(Border.createEmpty());
    dlgStyle.setBgTransparency(255);
    dlgStyle.setBgColor(0xfd1100);

    Label title = dlg.getTitleComponent();
    //title.setIcon(finalDuke.scaledHeight(title.getPreferredH()));
    title.getUnselectedStyle().setFgColor(0xff);
    title.getUnselectedStyle().setAlignment(Component.LEFT);

    dlg.setLayout(BoxLayout.y());
    Label blueLabel = new Label();
    blueLabel.setShowEvenIfBlank(true);
    blueLabel.getUnselectedStyle().setBgColor(0xff);
    blueLabel.getUnselectedStyle().setPadding(1, 1, 1, 1);
    blueLabel.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
    dlg.add(blueLabel);
    TextArea ta = new TextArea("A l'evenement"+titre);
    ta.setEditable(false);
    ta.setUIID("DialogBody");
    ta.getAllStyles().setFgColor(0);
    dlg.add(ta);

    Label grayLabel = new Label();
    grayLabel.setShowEvenIfBlank(true);
    grayLabel.getUnselectedStyle().setBgColor(0xcccccc);
    grayLabel.getUnselectedStyle().setPadding(1, 1, 1, 1);
    grayLabel.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
    dlg.add(grayLabel);

    Button ok = new Button(new Command("OK"));
    ok.getAllStyles().setBorder(Border.createEmpty());
    ok.getAllStyles().setFgColor(0);
    dlg.add(ok);
    dlg.showDialog();
    }

////-- DON'T EDIT BELOW THIS LINE!!!

 @Override
    protected boolean isCurrentInbox() {
        return true;
    }
// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("EventsForm");
        setName("EventsForm");
    }// </editor-fold>

    
  
}
