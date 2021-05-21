/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.components.ToastBar;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.codename1.util.Base64;
import com.mycompany.entities.Candidature;
import com.mycompany.entities.Opportunite;
import com.mycompany.entities.Task;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceTask1 {

    public ArrayList<Candidature> tasks;
     public ArrayList<Opportunite> tasks1; 
    public static ServiceTask1 instance;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceTask1() {
         req = new ConnectionRequest();
    }

    public static ServiceTask1 getInstance() {
        if (instance == null) {
            instance = new ServiceTask1();
        }
        return instance;
    }

    public boolean addOppor(Opportunite t) {
        String url = Statics.BASE_URL + "/addOpportunite/" + t.getTitre(); //création de l'URL
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
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Candidature> parseTasks(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
            */
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Candidature ca = new Candidature();
              
                          float id = Float.parseFloat(obj.get("id").toString());
                ca.setId((int)id);

                //int idd = Integer.parseInt(obj.(get("id").toString());
                ca.setTitre_id(obj.get("titre").toString());
             
                //int idd = Integer.parseInt(obj.get("id").toString());
                ca.setFonction(obj.get("fonction").toString());
                  ca.setType_contrat(obj.get("type_contrat").toString());
                  ca.setHoraires(obj.get("horaires").toString());
                  ca.setMode_salaire(obj.get("mode_salaire").toString());
                  ca.setPeriode(obj.get("periode").toString());
                   float annuel = Float.parseFloat(obj.get("annuel_mois").toString());
                ca.setAnnuel_mois((int)annuel);
                   
                  
                //Ajouter la tâche extraite de la réponse Json à la liste
                 tasks.add(ca);
              
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return tasks;
    }
    
    public ArrayList<Candidature> getAllTasks(){
        String url = Statics.BASE_URL+"/lisCand/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
 
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
         return tasks;
    }
 
    
  public boolean getconn(int id)
  {
          Response<Map> value = Rest.post("https://api.twilio.com/2010-04-01/Accounts/ACbfe734375a194ada9accf41bc81b221d/Messages.json").
                queryParam("To", "+21653695025").
                queryParam("From", "+19133801436").
                queryParam("Body", "Eat de la demande :Encours Ref: "+id).
                header("Authorization", "Basic " + Base64.encodeNoNewline(("ACbfe734375a194ada9accf41bc81b221d" + ":" + "6be5dba313be6592e2c2244386198219").getBytes())).
                getAsJsonMap();
                Dialog.show("Alert", "votre message a été envoyé avec succès ", new Command("OK"));

        
        return true;
    
            
  }            
  
}
