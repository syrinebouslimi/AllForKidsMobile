/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.Services;

import com.allforkids.Entities.Etablissement;
import com.allforkids.Entities.User;
import com.allforkids.GUI.AdminForm;
import com.allforkids.Entities.Favoris;
import com.allforkids.GUI.FavorisForm;
import com.allforkids.GUI.ParentForm;
import com.allforkids.GUI.PrestataireForm;
import static com.allforkids.Services.LoginService.currentUser;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sana
 */
public class FavorisService {

    public FavorisService() {
    }

    public void ajouterFavorisApi(Etablissement etab) {

        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/allforkid/allforkids/web/app_dev.php/addFavoris?idEtab=" + etab.getId() + "&idUser=" + currentUser.getId();
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String response = new String(con.getResponseData());
            if (response.equals("Favoris ajouté avec succés")) {
                Dialog.show("Favoris", response, "consulter liste favoris", "Ok");
            }
            if (Dialog.show("Favoris", response, "consulter liste favoris", "Ok")){
                FavorisForm favoris = new FavorisForm();
                favoris.getFavorisForm().show();
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public List<Favoris> getListFavoris(String json) {
        ArrayList<Favoris> listFavoris = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();
            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");
            ArrayList<Map<String, String>> myList = (ArrayList<Map<String,String>>)etudiants.get("etablissement");
               
                
                

            for (Map<String, Object> obj : list) {
                Favoris f = new Favoris();
                Map<String, Object> data = (Map<String, Object>) obj.get("etablissement");
                String idFavoris = obj.get("id").toString();
                String id = data.get("id").toString();
                String typeEtab = data.get("typeEtablissement").toString();
                String imageEtab = data.get("imageEtablissement").toString();
                String adresseEtab = data.get("adresseEtablissement").toString();
                String phone = data.get("phone").toString();
                String description = data.get("descriptionEtablissement").toString();
                String nom = data.get("nomEtablissement").toString();
                f.setId(id);
                f.setIdFavoris(idFavoris);
                f.setTypeEtablissement(typeEtab);
                f.setImageEtablissement(imageEtab);
                f.setAdresseEtablissement(adresseEtab);
                f.setDescriptionEtablissement(description);
                f.setPhoneEtablissement(phone);
                f.setNomEtablissement(nom);
                listFavoris.add(f);

                
                

            }

        } catch (IOException ex) {
        }

        return listFavoris;

    }

    public void supprimerFavoris(String idFavoris) {
        
           ConnectionRequest con = new ConnectionRequest();

        con.setUrl("http://localhost/allforkid/allforkids/web/app_dev.php/supprimerFavoris/idFavoris/"+idFavoris.substring(0,idFavoris.indexOf(".")));
        System.out.println("aa" +con.getUrl());
        NetworkManager.getInstance().addToQueue(con);
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
               String response = new String(con.getResponseData());
            if (response.equals("Suppression effectué avec succés")) {
                Dialog.show("Favoris", response, "consulter liste favoris", "Ok");
            }
            if (Dialog.show("Favoris", response, "consulter liste favoris", "Ok")){
                FavorisForm favoris = new FavorisForm();
                favoris.getFavorisForm().show();
            }
            }
        });


    }
}
