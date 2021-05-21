/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
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
public class ServiceTask {

    public ArrayList<Opportunite> tasks;
        public ArrayList<Candidature> tasks1;
    public static ServiceTask instance;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceTask() {
         req = new ConnectionRequest();
    }

    public static ServiceTask getInstance() {
        if (instance == null) {
            instance = new ServiceTask();
        }
        return instance;
    }

    public boolean addOpportunite(Opportunite t) {
                String url = Statics.BASE_URL+"/addOpportunite?opEmployeur="+t.getOp_employeur_id()+"&&titre="+t.getTitre()+"&&lieu="+t.getLieu()+"&&description="+t.getDescription()+"&&nom_entreprise="+t.getNom_entreprise()+"&&taille_entreprise="+t.getTaille_entreprise()+"&&poste="+t.getPoste()+"&&media="+t.getMedia()+"&&nombre_recrutement="+t.getNombre_recrutement()+"&&logo="+t.getLogo();
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
         public boolean UpdateOpportunite(Opportunite t,int id) {
                String url = "http://192.168.1.5:8000/UpdateOpportunite?id="+id+"&&titre="+t.getTitre()+"&&lieu="+t.getLieu()+"&&desc="+t.getDescription()+"&&nom_ent="+t.getNom_entreprise()+"&&taille="+t.getTaille_entreprise()+"&&poste="+t.getPoste()+"&&media="+t.getMedia()+"&&recru="+t.getNombre_recrutement();
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
 public boolean addCandidature(Candidature t) {
                String url = "http://192.168.1.5:8000/addCandidature/"+t.getTitre_id()+"?fonction="+t.getFonction()+"&&type_contrat="+t.getType_contrat()+"&&horaires="+t.getHoraires()+"&&mode_salaire="+t.getMode_salaire()+"&&periode="+t.getPeriode()+"&&annuel_mois="+t.getAnnuel_mois();
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
 public boolean DeleteCandidature(int identif) {
                String url = "http://192.168.1.5:8000/deleteCand/"+identif;
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
    public ArrayList<Opportunite> parseTasks(String jsonText){
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
                Opportunite t = new Opportunite();
                float id = Float.parseFloat(obj.get("id").toString());
                //int idd = Integer.parseInt(obj.get("id").toString());
                t.setId((int)id);
                t.setTitre(obj.get("titre").toString());
                t.setLieu(obj.get("lieu").toString());
                t.setDescription(obj.get("description").toString());
                t.setNom_entreprise(obj.get("nom_entreprise").toString());
                 t.setPoste(obj.get("poste").toString());
                t.setMedia(obj.get("media").toString());
                float nbr_recru = Float.parseFloat(obj.get("nombre_recrutement").toString());
                //int idd = Integer.parseInt(obj.get("id").toString());
                 t.setNombre_recrutement((int)nbr_recru);
                       t.setTaille_entreprise(obj.get("taille_entreprise").toString());
                //Ajouter la tâche extraite de la réponse Json à la liste
                 tasks.add(t);
                
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return tasks;
    }
    
    public ArrayList<Opportunite> getAllTasks(){
        String url = Statics.BASE_URL+"/lis/";
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
 
    
  
}
