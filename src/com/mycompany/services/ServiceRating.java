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
    private final ConnectionRequest req;

    public ServiceRating() {
        req = new ConnectionRequest();
    }

    public static ServiceRating getInstance() {
        if (instance == null) {
            instance = new ServiceRating();
        }
        return instance;
    }
    


    public ArrayList<Rating> parseProduct(String jsonText) throws IOException {
        

            products = new ArrayList<>();
             JSONParser j = new JSONParser();
                             System.out.println("json"+jsonText);

            Map<String, Object> fournisseurListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) fournisseurListJson.get("root");
            
            if(list!=null)
            {
            for (Map<String, Object> obj : list) {
                Rating p;
                p = new Rating();
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
   
            

         
        return products;
    }
    public ArrayList<Rating> getAllProductC(int idEV) {
        String url = "http://192.168.1.5:8000/star?id="+idEV;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    products = parseProduct(new String(req.getResponseData()));
                } catch (IOException ex) {
                   
                }
                req.removeResponseListener(this);

            }
        }
        );
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
