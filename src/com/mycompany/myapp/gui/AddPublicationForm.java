/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.notifications.LocalNotification;
import com.codename1.notifications.LocalNotificationCallback;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Publication;
import com.mycompany.myapp.services.ServicePublication;
import com.codename1.push.PushCallback;

/**
 *
 * @author m'amine
 */
public class AddPublicationForm extends Form implements LocalNotificationCallback {

    public AddPublicationForm(Form previous) {

        setTitle("Ajouter une publication ");
        setLayout(BoxLayout.y());

        TextField tfTitle = new TextField("", "Titre");
        TextField tfDescription = new TextField("", "Description");
        Button btnValider = new Button("Ajouter Pub");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfTitle.getText().length() == 0) || (tfDescription.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Publication f = new Publication(tfDescription.getText(), tfTitle.getText());
                        if (ServicePublication.getInstance().addPub(f)) {
                            LocalNotification n = new LocalNotification();
                            n.setId("demo-notification");
                            n.setAlertBody("It's time to take a break and look at me");
                            n.setAlertTitle("Break Time!");
                           // n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound
                            
                            Display.getInstance().scheduleLocalNotification(
                                    n,
                                    System.currentTimeMillis() , // fire date/time
                                    LocalNotification.REPEAT_MINUTE // Whether to repeat and what frequency
                            );
                           localNotificationReceived(n.getId());
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }

            }
        });

        addAll(tfTitle, tfDescription, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                e -> previous.showBack()); // Revenir vers l'interface précédente

    }

    @Override
    public void localNotificationReceived(String n) {
        System.out.println("Received local notification " + n);
    }

}
