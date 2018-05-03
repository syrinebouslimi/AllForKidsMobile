/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.Services;

import com.allforkids.Entities.Etablissement;
import com.allforkids.Entities.Favoris;
import com.allforkids.Entities.Statistique;
import com.allforkids.GUI.FavorisForm;
import static com.allforkids.Services.LoginService.currentUser;
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
public class StatistiqueService {

    public StatistiqueService() {
    }

    public List<Statistique> getListStatistique(String json) {
        ArrayList<Statistique> liststat = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();
            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                Statistique s = new Statistique();
                String nom = obj.get("nomEtablissement").toString();
                s.setNomEtab(nom);
                float rating = Float.parseFloat(obj.get("avgRating").toString());
                s.setAvgrating(rating);
                liststat.add(s);

            }

        } catch (IOException ex) {
        }

        return liststat;
    }
}
