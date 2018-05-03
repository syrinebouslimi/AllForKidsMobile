/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.GUI;

import com.allforkids.Entities.Message;
import static com.allforkids.Services.LoginService.currentUser;
import com.allforkids.Services.MessagesService;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author Syrine
 */
public class MessagesForm {

    Form messageForm;
    Form newMessageForm;

    SimpleDateFormat sfd = new SimpleDateFormat("yy-MM-dd HH:mm:ss");

    public MessagesForm(String threadId) {
        messageForm = new Form("Liste des message", BoxLayout.y());

        Toolbar.setGlobalToolbar(true);
        messageForm.setScrollableY(true);
        messageForm.setSmoothScrolling(true);

        messageForm.getToolbar().addCommandToOverflowMenu("Envoyer Message", null, ev -> {
            new MessagesForm(threadId, true);

        });

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/allforkid/allforkids/web/app_dev.php/allMessageByThread/" + threadId.substring(0, threadId.indexOf(".")));
        NetworkManager.getInstance().addToQueue(con);
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                MessagesService ms = new MessagesService();
                List<Message> list = ms.getListMessage(new String(con.getResponseData()));
                for (int i = 0; i < list.size(); i++) {
                    messageForm.addComponent(addItem(list.get(i)));
                }

                messageForm.refreshTheme();

            }
        });

    }

    public MessagesForm(String threadId, boolean fromadd) {
        newMessageForm = new Form("Envoyer message", BoxLayout.y());

        Toolbar.setGlobalToolbar(true);
        newMessageForm.setScrollableY(true);
        newMessageForm.setSmoothScrolling(true);
        
         newMessageForm.getToolbar().addCommandToLeftBar("retour", null, (ev) -> {
            new MessagesForm(threadId).getMessageForm().showBack();
        });

        Label lb = new Label("Contenu du message : ");
        TextField ta = new TextField("Tapez ...");

        Button send = new Button("Envoyer");
        newMessageForm.add(lb);
        newMessageForm.add(ta);
        newMessageForm.add(send);
        
        

        send.addActionListener((evt) -> {

            if (ta.getText().length() != 0) {
                ConnectionRequest con = new ConnectionRequest();
             
                con.setUrl("http://localhost/allforkid/allforkids/web/app_dev.php/sendNewMessage?thread=" + threadId.substring(0, threadId.indexOf("."))
                        + "&sender=" + currentUser.getId().substring(0, currentUser.getId().indexOf(".")) + "&body=" + ta.getText());
                System.out.println("urrl " +con.getUrl());
                NetworkManager.getInstance().addToQueue(con);
                con.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                   String response = new String(con.getResponseData());
                   if (response.equals("Message ajouté avec succés"))
                  Dialog.show("Succés", response, "Valider", null);
                   if (Dialog.show("Succés", response, "Valider", null)){
                       new MessagesForm(threadId).getMessageForm().showBack();
                   }

                    }
                });

            } else {

                Dialog.show("Erreur", "Veuillez ne pas envoyer un message vide", "Valider", null);
            }

        });
        
        newMessageForm.show();

    }

    public Container addItem(Message r) {
        Container ctmain = new Container(BoxLayout.y());

        Label lbsendertitle = new Label("Envoyé par : ");
        Label lbsender = new Label(r.getSenderFirstname() + " " + r.getSenderLastName());

        /* Label lbdatetitle = new Label("Envoyé le : ");
        Label lbdate = new Label(r.getDateEnvoi());*/
        Label lbbodytitle = new Label("Contenu : ");
        Label lbbody = new Label(r.getBody());

        Label l = new Label(" ");

        ctmain.add(lbsendertitle);
        ctmain.add(lbsender);
        /*     ctmain.add(lbdatetitle);
        
        ctmain.add(lbdate);*/
        ctmain.add(lbbodytitle);
        ctmain.add(lbbody);
        ctmain.add(l);

        return ctmain;

    }

    public Form getMessageForm() {
        return messageForm;
    }

    public void setMessageForm(Form messageForm) {
        this.messageForm = messageForm;
    }

    public Form getNewMessageForm() {
        return newMessageForm;
    }

    public void setNewMessageForm(Form newMessageForm) {
        this.newMessageForm = newMessageForm;
    }

}
