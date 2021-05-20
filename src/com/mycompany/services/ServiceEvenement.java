/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import static com.codename1.io.Log.p;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Evenement;
import com.mycompany.entities.Participation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Firas
 */
public class ServiceEvenement {
     public ArrayList<Evenement> products;
          public ArrayList<Participation> products1;

     
    boolean resultOK;
    public static ServiceEvenement instance;
    private ConnectionRequest req;

    public ServiceEvenement() {
        req = new ConnectionRequest();
    }

    public static ServiceEvenement getInstance() {
        if (instance == null) {
            instance = new ServiceEvenement();
        }
        return instance;
    }
    


    public ArrayList<Evenement> parseProduct(String jsonText) {
        try {

            products = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> productListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) productListJson.get("root");
              System.out.println("liste:"+list);

            for (Map<String, Object> obj : list) {
                Evenement p;
                p = new Evenement();
                               float id = Float.parseFloat(obj.get("id").toString());
                            
                p.setId((int) id);
                
       
                                                p.setTitre(obj.get("titre").toString());
                float nbr = Float.parseFloat(obj.get("nombrePar").toString());
                p.setNombre_par((int) nbr);
                
                
             

                                p.setDescription(obj.get("description").toString());   

             
               
               
                //p.setDate_expiration((LocalDate) obj.get("date_expiration"));      

p.setPhoto(obj.get("photo").toString());
                products.add(p);
                System.out.println(products.get(0));

            }
   
            }

               catch (IOException ex) {
        }
        return products;
    }
     public ArrayList<Participation> parseProduct1(String jsonText) {
        try {

            products1 = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> productListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) productListJson.get("root");
              System.out.println("liste:"+list);

            for (Map<String, Object> obj : list) {
                
                Participation p = new Participation();
                                            float id = Float.parseFloat(obj.get("id").toString());
                            
                p.setId((int) id);
                             
                
       
                                                p.setDate(obj.get("date").toString());
                float nbr = Float.parseFloat(obj.get("idEmployer").toString());
                p.setId_employer((int) nbr);
                
                
              p.setDate(obj.get("email").toString());

                                float nbr1 = Float.parseFloat(obj.get("idEvent").toString());
                p.setId_event((int) nbr1);

             
               
               
                //p.setDate_expiration((LocalDate) obj.get("date_expiration"));      


                products1.add(p);
                System.out.println(products1.get(0));

            }
   
            }

               catch (IOException ex) {
        }
        return products1;
    }
     
     public ArrayList<Evenement> getAllProduct() {
        String url = "http://192.168.1.5:8000/partici";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                products = parseProduct(new String(req.getResponseData()));
                req.removeResponseListener(this);

            }
        }
        );
        NetworkManager.getInstance().addToQueue(req);
        NetworkManager.getInstance().addErrorListener((e) -> e.consume());
                      
        return products;
    }
      public boolean ajoutPar(int id) {
      // final User me = new User();
         
String Url = "http://192.168.1.5:8000/participation";
String url2 = "http://192.168.1.5:8000/participation?idEmployer=1&idEvent="+id;


  
       // req.setUrl(Url);
        //req.addArgument("idEmployer", "1");
        //req.addArgument("idEvent", "4");
       
        
//req.setPost(false);
ConnectionRequest con = new ConnectionRequest(url2, true);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
             //products1 = parseProduct1(str);
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
  //      req.addResponseListener((e) -> {
         
            // products1 = parseProduct1(new String(req.getResponseData()));
                
             

        //});
        NetworkManager.getInstance().addToQueue(req);
        NetworkManager.getInstance().addErrorListener((e) -> e.consume());
        //if(products1.isEmpty())
        //{
          //  return false;
        //}
        

return true;

    }
      public boolean DeleteCandidature(int identif) {
                String url = "http://192.168.1.5:8000/deleteevent/"+identif;
                req.setUrl(url);// Insertion de l'URL de notre demande de connexion        
        //req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                                        Dialog.show("Success","Element supprimé avec succés",new Command("OK"));

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
       public void addevent(int user, String titre, Date Date_strat, Date Date_end,int nbpar,int type, String description, String FilenameInserver) {
        String url = "http://192.168.1.5:8000/eventsaj?user="
                + user
                +"&titre="
                +titre
                +"&date_start="
                +Date_strat
                +"&date_end="
                +Date_end
                +"&nombrePar="
                +nbpar
                +"&type="
                +type
                +"&description="
                +description
                +"&photo="
                +FilenameInserver;        
        
        System.out.print(url);
        
        ConnectionRequest con = new ConnectionRequest(url, true);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueue(con);
    }
       public boolean modifierevent(Evenement E)
       {String url = "http://192.168.1.5:8000/updateevent?id="
               +E.getId()
               + "&titre="
                +E.getTitre()
                +"&date_start="
                +E.getDate_start()
                +"&date_end="
                +E.getDate_start()
                +"&nombrePar="
                +E.getNombre_par()
                +"&type="
                +E.getId_type()
                +"&description="
                +E.getDescription();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueue(req);
        return resultOK;
               
           
       }
    
}
