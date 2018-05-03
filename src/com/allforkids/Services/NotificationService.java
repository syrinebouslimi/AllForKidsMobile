/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.Services;

import com.allforkids.Entities.Notification;
import com.allforkids.GUI.FavorisForm;
import com.allforkids.GUI.NotificationForm;
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
 * @author Syrine
 */
public class NotificationService {

        Dialog ip;

    public NotificationService() {
                ip = new InfiniteProgress().showInifiniteBlocking();

    }

        
    public List<Notification> getListNotif(String json) {

        ArrayList<Notification> listNotif = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                Notification n = new Notification();

                String id = obj.get("id").toString();
                n.setId(id);

                String body = obj.get("subject").toString();
                n.setSubject(body);

                String msg = obj.get("message").toString();
                n.setMessage(msg);

                List<Map<String, Object>> notif = (List<Map<String, Object>>) obj.get("notifiableNotifications");
                for (int i = 0; i < notif.size(); i++) {

                    String seen = notif.get(i).get("seen").toString();
                    n.setIsSeen(seen);
                }

                listNotif.add(n);

            }

        } catch (IOException ex) {
        }
        ip.dispose();

        return listNotif;

    }

    public void supprimerNotif(String id) {
           ConnectionRequest con = new ConnectionRequest();

        con.setUrl("http://localhost/allforkid/allforkids/web/app_dev.php/supprimerNotifById/"+id.substring(0,id.indexOf(".")));
        System.out.println("aa" +con.getUrl());
        NetworkManager.getInstance().addToQueue(con);
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
               String response = new String(con.getResponseData());
            if (response.equals("Suppression effectué avec succés")) {
                Dialog.show("Notification", response, "consulter liste notifications", "Ok");
            }
            if (Dialog.show("Notification", response, "consulter liste notifications", "Ok")){
                
                new NotificationForm().getNotiForm().show();
               
            }
            }
        });
                ip.dispose();

    }

}
