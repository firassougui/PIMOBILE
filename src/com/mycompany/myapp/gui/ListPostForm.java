/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.Preferences;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.GenericListCellRenderer;
import com.mycompany.myapp.entities.Publication;
import com.mycompany.myapp.entities.Post;
import static com.mycompany.myapp.gui.ModifForumForm.tfTitleM;
import com.mycompany.myapp.services.ServicePublication;
import com.mycompany.myapp.services.ServicePost;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class ListPostForm extends Form {

    public ArrayList<Publication> forums;
    public ArrayList<Post> posts;
    Form current;
    // static  TextField tfIdF = new TextField();
//liste des post (forum id ) 

    public ListPostForm(Form previous, Publication f) {

        setTitle("List Postes");

        setLayout(BoxLayout.y());

        TextField Title = new TextField("", "Post Title");
        TextField Description = new TextField("", "description Post");
        Button btnValider = new Button("Add Post");
        Button Modifbtn = new Button("Modif valider ");
               
        addAll(Title, Description, btnValider,Modifbtn);

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((Title.getText().length() == 0) || (Description.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Post p = new Post(Description.getText(), Title.getText(), f.getId());
                        if (ServicePost.getInstance().addPost(p, f.getId())) {
                            Dialog.show("connectedd", "succed", new Command("OK"));
                            new ListPostForm(previous, f).show();
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }

            }
        });
        System.out.println("Id Forum=> " + f.getId());
        posts = ServicePost.getInstance().getPosts(f.getId());
       // System.out.println(f.getPosts());

        /*for (Post p : f.getPosts()){
            post.setId(p.getId());
            post.setTitle(p.getTitle());
            post.setDescription(p.getDescription());
             System.out.println("poooostt "+ p);
            posts.add(p);
         }*/
        for (Post obj : posts) {

           // System.out.println("postttt=> " + f.getPosts());
            setLayout(BoxLayout.y());

            Button spTitle = new Button();
            SpanLabel sp = new SpanLabel();
            Button Delete = new Button("D");
            Button Modif = new Button("M");

            spTitle.setText("Title : " + obj.getTitle());

            sp.setText("Description : " + obj.getDescription());
            Delete.addActionListener(e
                    -> {
                System.out.println(obj.getId());

                ServicePost.getInstance().deletePost(obj.getId());
                new ListPostForm(previous, f).show();
            });
            Modif.addActionListener((ActionEvent evt) -> {
                Title.setText(obj.getTitle());
                Description.setText(obj.getDescription());

                
                
                Modifbtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if ((Title.getText().length() == 0) || (Description.getText().length() == 0)) {
                            Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                        } else {
                            try {

                                Post p  = new Post(Description.getText(), Title.getText());
                                if (ServicePost.getInstance().modifPost(p,f.getId())) {
                                    System.out.println("modiff->"+p);
                                    Dialog.show("Success", "Connection accepted", new Command("OK"));
                                     
                                } else {
                                    Dialog.show("ERROR", "Server error", new Command("OK"));
                                }
                            } catch (NumberFormatException e) {
                                Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                            }

                        }

                    }
                });
                

            });
            addAll(spTitle, Delete, Modif, sp);
        }
        // sp.setText(new ServicePublication().getAllForums().toString());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}
