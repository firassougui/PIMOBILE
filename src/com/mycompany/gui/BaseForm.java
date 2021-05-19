/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;

/**
 * GUI builder created Form
 *
 * @author Firas
 */
public class BaseForm extends Form {

     public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
    
    public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }
    public void installSidemenu(Resources res) {
       Image selection = res.getImage("selection-in-sidemenu.png");
        
        
        Image inboxImage = null;
        if(isCurrentInbox()) inboxImage = selection;

        Image trendingImage = null;
        if(isCurrentTrending()) trendingImage = selection;
        
        Image calendarImage = null;
        if(isCurrentCalendar()) calendarImage = selection;
        
        Image statsImage = null;
        if(isCurrentStats()) statsImage = selection;
        
       
        
       
        getToolbar().addCommandToSideMenu("Produits", trendingImage, e -> new EventsForm(res).show());
          getToolbar().addCommandToSideMenu("Produitback", trendingImage, e -> new EventsBack(res).show());
            getToolbar().addCommandToSideMenu("Produits", trendingImage, e -> new EventsBack(res).show());
    
        
             // spacer
        getToolbar().addComponentToSideMenu(new Label(" ", "SideCommand"));
     
    }

        
    protected boolean isCurrentInbox() {
        return false;
    }
    
    protected boolean isCurrentTrending() {
        return false;
    }

    protected boolean isCurrentCalendar() {
        return false;
    }

    protected boolean isCurrentStats() {
        return false;
    }
}
