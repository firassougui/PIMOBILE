/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Publication;
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.gui.ListPublicForm;
//import com.mycompany.myapp.entities.Task;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServicePublication {

    public ArrayList<Publication> forums;
 
    public static ServicePublication instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServicePublication() {
        req = new ConnectionRequest();
    }

    public static ServicePublication getInstance() {
        if (instance == null) {
            instance = new ServicePublication();
        }
        return instance;
    }

    public boolean addPub(Publication f) {
        String url = Statics.BASE_URL + "/addPubJSON/new?titre=" + f.getTitre()+ "&description=" + f.getDescription(); //création de l'URL
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Publication> parseTasks(String jsonText) {
        try {
            forums = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Publication f = new Publication();
               

                float id = Float.parseFloat(obj.get("id").toString());
                f.setId((int) id);
                f.setTitre(obj.get("titre").toString());

                f.setDescription(obj.get("description").toString());
                
                System.out.println("hellooo"+obj.get("posts").getClass());
                
               // f.setPosts((ArrayList<Post>[]) obj.get("posts"));
                forums.add(f);
            }
        } catch (IOException ex) {

        }
        return forums;
    }

    public ArrayList<Publication> getAllForums() {
        String url = Statics.BASE_URL +"publication/AllPubs";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                forums = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return forums;
    }

    public void deletePub(int id) {
        ConnectionRequest req = new ConnectionRequest();
        String url = Statics.BASE_URL + "/deletePubJSON/" + id;
        req.setUrl(url);

        // req.setPost(false);
        req.addResponseListener((NetworkEvent evt) -> {
            String str = new String(req.getResponseData());
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
       
    }
    public boolean modifPub(Publication f,int id ) {
        String url = Statics.BASE_URL + "/updatePubJSON/"+id+"?titre=" + f.getTitre() + "&description=" + f.getDescription(); //création de l'URL
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    public void detailForum(int id ) {
        String url = Statics.BASE_URL + "/showPubJSON/"+id; //création de l'URL
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
       
    }
}
