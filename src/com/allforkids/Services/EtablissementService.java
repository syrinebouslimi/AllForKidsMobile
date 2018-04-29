/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.Services;

import com.allforkids.Entities.Etablissement;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sana
 */
public class EtablissementService {

    public EtablissementService() {
    }

    public ArrayList<Etablissement> getListEablissement(String json) {

        ArrayList<Etablissement> listEtablissement = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();
            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                Etablissement e = new Etablissement();

                String id = obj.get("id").toString();
                String nomEtab = obj.get("nomEtablissement").toString();
                String typeEtab = obj.get("typeEtablissement").toString();
                String imageEtab = obj.get("imageEtablissement").toString();
                String adresseEtab = obj.get("adresseEtablissement").toString();
                String phone = obj.get("phone").toString();
                String description = obj.get("descriptionEtablissement").toString();
                String avgRating = obj.get("avgRating").toString();

                e.setId(id);
                e.setNomEtablissement(nomEtab);
                e.setTypeEtablissement(typeEtab);
                e.setImageEtablissement(imageEtab);
                e.setAdresseEtablissement(adresseEtab);
                e.setDescriptionEtablissement(description);
                e.setPhoneEtablissement(phone);
                e.setAvgRating(avgRating);

                listEtablissement.add(e);

            }

        } catch (IOException ex) {
        }

        return listEtablissement;

    }

}
