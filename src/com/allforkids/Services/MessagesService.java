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
