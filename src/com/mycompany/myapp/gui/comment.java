/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;

/**
 *
 * @author M'Amine
 */
public class comment extends Form {
          Form current;

    public comment (int id){
        TextField com = new TextField("ajouter votre commentaire");
        Button Preview=new Button("Preview");
        Preview.addActionListener(e -> new ListPublicForm(current).show());
Button ADD=new Button("ADD");
        addAll(com,Preview,ADD);
        
    }
    
    }