/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Publication;
import static com.mycompany.myapp.gui.ModifForumForm.tfDescriptionM;
import static com.mycompany.myapp.gui.ModifForumForm.tfTitleM;
import com.mycompany.myapp.services.ServicePublication;
import com.mycompany.myapp.services.ServicePost;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author M'Amine
 */
public class ListPublicForm extends Form {

    public ArrayList<Publication> publications;
    Form current;

    public ListPublicForm(Form previous) {
        setTitle("List publications");

        publications = ServicePublication.getInstance().getAllPubs();
        for (Publication obj : publications) {
            setLayout(BoxLayout.y());

            Button spTitle = new Button();
            SpanLabel sp = new SpanLabel();
            Button Delete = new Button("Delete");
            Button Modif = new Button("Update");

            spTitle.setText("Titre : " + obj.getTitre());
            spTitle.addActionListener(e -> {
                ServicePublication.getInstance().detailForum(obj.getId());
                System.out.println("heeeere"+obj.getId());
                new ListPostForm(previous,obj).show();
                
                        
            });
            sp.setText("Description : " + obj.getDescription());
            Delete.addActionListener(e
                    -> {
                System.out.println(obj.getId());

                ServicePublication.getInstance().deletePub(obj.getId());
                new ListPublicForm(previous).show();
            });
            Modif.addActionListener((evt) -> {
                //ModifForumForm.tfDescriptionM.setVisible(false);
                ModifForumForm.tfIdM.setText(String.valueOf(obj.getId()));
                //ModifForumForm.tfIdM.setVisible(false);
                ModifForumForm.tfDescriptionM.setText(obj.getDescription());
                ModifForumForm.tfTitleM.setText(obj.getTitre());

                new ModifForumForm(previous).show();

            });

            addAll(spTitle, Delete, Modif, sp);
        }
        // sp.setText(new ServicePublication().getAllForums().toString());

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}
