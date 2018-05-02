/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.Services;

import com.allforkids.Entities.Message;
import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Syrine
 */
public class MessagesService {

    public List<Message> getListMessage(String json) {

        ArrayList<Message> listMessage = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                Message m = new Message();

                String id = obj.get("id").toString();
                m.setId(id);
                
              /*  String timestamp = obj.get("timestamp").toString();
                System.out.println("aaaa " +timestamp);
             
                String beforeComma = timestamp.substring(0, timestamp.indexOf("."));
                String afterComma = timestamp.substring(timestamp.indexOf(".") + 1, timestamp.length());
                SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                if (afterComma.length()<=9){
                
                long batch_date = Long.parseLong(beforeComma + afterComma.substring(0, 9));
                Date dt = new Date(batch_date * 1000);
                Calendar cal = Calendar.getInstance();
                cal.setTime(dt);
                cal.add(Calendar.HOUR_OF_DAY, -1);
                m.setDateEnvoi(cal.getTime().toString()); */

                String body = obj.get("body").toString();
                m.setBody(body);

                Map<String, Object> sender = (Map<String, Object>) obj.get("sender");
                String firstName = sender.get("nomUser").toString();
                String lastName = sender.get("prenomUser").toString();
                
                m.setSenderFirstname(firstName);
                m.setSenderLastName(lastName);
                
                
                listMessage.add(m);

            }

        } catch (IOException ex) {
        }

        return listMessage;

}

}
