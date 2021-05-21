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
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Slider;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.mycompany.entities.Evenement;
import com.mycompany.entities.Participation;
import com.mycompany.entities.Rating;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;



/**
 *
 * @author Firas
 */
public class ServiceRating {
        public ArrayList<Rating> products;
          

     
    boolean resultOK;
    public static ServiceRating instance;
    private ConnectionRequest req;

    public ServiceRating() {
        req = new ConnectionRequest();
    }

    public static ServiceRating getInstance() {
        if (instance == null) {
            instance = new ServiceRating();
        }
        return instance;
    }
    


    public ArrayList<Rating> parseProduct(String jsonText)  {
        
            try {
                products=new ArrayList<>();
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
                
                
                if(list!=null)
                {
                    for (Map<String,Object> obj : list) {
                        
                        Rating p = new Rating();
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        p.setId((int) id);
                        
                        
                        
                        float nbr = Float.parseFloat(obj.get("idEv").toString());
                        p.setIdEv((int) nbr);
                        float stars = Float.parseFloat(obj.get("stars").toString());
                        p.setStars( stars);
                        
                        
                        
                        
                        products.add(p);
                        System.out.println(products.get(0));
                        
                    }
                }
                
                
                
                
                
                
                
                
               
            } catch (IOException ex) {
               
            }
             return products;
    }
    public ArrayList<Rating> getAllProductC(int idEV) {
        String url = "http://192.168.1.5:8000/star?id="+idEV;
         req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                products = parseProduct(new String(req.getResponseData()));
                req.removeResponseListener(this);
 
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
         return products;
    }
 
     /*
     public Rating getAllProduct(int idEV) {
        String url = "http://192.168.1.8:8000/star?id="+idEV;
        
        Rating r=new Rating();
        
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                int id,ide,stars;
                //products = parseProduct(new String(req.getResponseData()));
              // String str = new String(Arrays.toString(req.getResponseData()).substring(1,Arrays.toString(req.getResponseData()).length()));
               String str = new String(req.getResponseData());
               System.out.println("iiii"+str);
               String segments[] = str.split(",");
               String i=segments[0];
               String i1=segments[1];
               String i2=segments[2];
               System.out.println("iiii"+i);
               System.out.println("iiii1111"+i1);
               System.out.println("iiii2222"+i2);
                //id=Integer.valueOf(i.substring(ide));
                //ide=Integer.valueOf(str.substring(6,8));
                //stars=Integer.valueOf(str.substring(6,8));
                req.removeResponseListener(this);

            }
        }
        );
        NetworkManager.getInstance().addToQueue(req);
        NetworkManager.getInstance().addErrorListener((e) -> e.consume());
                      
        return r;
    }
     */
       public boolean ajoutPar(int id,float stars) {
      // final User me = new User();
         
String url = "http://192.168.1.5:8000/addrating?idEv="+id+"&stars="+stars;


   ConnectionRequest con = new ConnectionRequest(url, true);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueue(con);
return true;
    }
        public void initStarRankStyle(Style s, Image star) {
    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);

    s.setBorder(Border.createEmpty());
    s.setBgImage(star);
    s.setBgTransparency(0);
        }
       public Slider createStarRankSlider() {
    Slider starRank = new Slider();
    starRank.setEditable(true);
    starRank.setMinValue(0);
    starRank.setMaxValue(5);
    Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
            derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
    Style s = new Style(0xffff33, 0, fnt, (byte)0);
    Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    s.setOpacity(100);
    s.setFgColor(0);
    Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
    initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
    starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
    return starRank;
}
    
}
