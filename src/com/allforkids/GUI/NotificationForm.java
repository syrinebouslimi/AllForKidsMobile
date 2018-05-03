/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.GUI;

import com.allforkids.Entities.Notification;
import com.allforkids.Entities.Reclamation;
import com.allforkids.Services.NotificationService;
import com.allforkids.Services.ReclamationsService;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import java.util.List;

/**
 *
 * @author Syrine
 */
public class NotificationForm {

    Form notiForm;
    NotificationService sr;

    public NotificationForm() {
        System.out.println("kkk");
        notiForm = new Form("Liste des notifications", BoxLayout.y());
        notiForm.getToolbar().addCommandToLeftBar("retour", null, (ev) -> new AdminForm().getAdminForm().showBack());

        Toolbar.setGlobalToolbar(true);
        notiForm.setScrollableY(true);
        notiForm.setSmoothScrolling(true);

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/allforkid/allforkids/web/app_dev.php/afficherNotif");
        System.out.println("urrl " + con.getUrl());
        NetworkManager.getInstance().addToQueue(con);
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

                sr = new NotificationService();
                List<Notification> list = sr.getListNotif(new String(con.getResponseData()));
                for (int i = 0; i < list.size(); i++) {
                    notiForm.addComponent(addItem(list.get(i)));
                }

                notiForm.refreshTheme();

            }
        });

    }

    public Container addItem(Notification n) {
        Container ctmain = new Container(BoxLayout.y());

        Label lbsubject = new Label("Sujet : " + n.getSubject());
        Label lbdate = new Label("Message : " + n.getMessage());
        Button seen = new Button();
        if (n.getIsSeen().equals("true")) {
            seen.setText("Déja vu");
        } else {
            seen.setText("Nouveau");
        }
        seen.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
        Button supprimer = new Button("Supprimer");
        seen.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
        ctmain.add(lbsubject);
        ctmain.add(lbdate);
        ctmain.add(seen);
        ctmain.add(supprimer);

        supprimer.addActionListener((evt) -> {
            sr.supprimerNotif(n.getId());

        });

        ctmain.setLeadComponent(supprimer);

        return ctmain;

    }

    public Form getNotiForm() {
        return notiForm;
    }

    public void setNotiForm(Form notiForm) {
        this.notiForm = notiForm;
    }

}
