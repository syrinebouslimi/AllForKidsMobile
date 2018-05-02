/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.Services;

import com.allforkids.Entities.Reclamation;
import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.text.*;
import java.util.Calendar;

/**
 *
 * @author sana
 */
public class ReclamationsService {

    public ReclamationsService() {
    }

    public ArrayList<Reclamation> getListReclamation(String json) {

        ArrayList<Reclamation> listReclamation = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                Reclamation r = new Reclamation();

                String id = obj.get("id").toString();
                String subject = obj.get("subject").toString();
                r.setId(id);
                r.setSubject(subject);

                Map<String, Object> data = (Map<String, Object>) obj.get("createdAt");

                String timestamp = data.get("timestamp").toString();
                String beforeComma = timestamp.substring(0, timestamp.indexOf("."));
                String afterComma = timestamp.substring(timestamp.indexOf(".") + 1, timestamp.length());
                SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                long batch_date = Long.parseLong(beforeComma+afterComma.substring(0,9));
                Date dt = new Date(batch_date * 1000);
                 Calendar cal = Calendar.getInstance();
                cal.setTime(dt);
                cal.add(Calendar.HOUR_OF_DAY, -1);
                r.setCreatedAt(cal.getTime().toString());
                
                listReclamation.add(r);

            }

        } catch (IOException ex) {
        }
   
        return listReclamation;

    }

}
