/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.io.Preferences;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Publication;
import com.mycompany.myapp.services.ServicePublication;

/**
 *
 * @author ASUS
 */
public class ModifForumForm extends Form {

    static TextField tfTitleM = new TextField();
    static TextField tfDescriptionM = new TextField();
    static TextField tfIdM = new TextField();
    Publication current;

    public ModifForumForm(Form previous) {

        setTitle("Update Post");
        setLayout(BoxLayout.y());

        Button btnValider = new Button("Update Post");
        addAll(tfTitleM, tfDescriptionM, btnValider);
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfTitleM.getText().length() == 0) || (tfDescriptionM.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {

                        Publication f = new Publication(tfDescriptionM.getText(), tfTitleM.getText());
                        if (ServicePublication.getInstance().modifPub(f, Integer.parseInt(tfIdM.getText()))) {
                            Dialog.show("Success", "Connection accepted", new Command("OK"));
                            Preferences.clearAll();
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }

            }
        });

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                e -> previous.showBack()); // Revenir vers l'interface précédente

    }
}
