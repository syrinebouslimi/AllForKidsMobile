/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.GUI;

import com.allforkids.Entities.Etablissement;
import com.allforkids.Entities.Reclamation;
import com.allforkids.Services.ReclamationsService;
import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import java.util.List;

/**
 *
 * @author Syrine
 */
public class ReclamationForm {

    Form reclamationForm;

    public ReclamationForm() {
        System.out.println("kkk");
        reclamationForm = new Form("Liste des reclamations", BoxLayout.y());

        Toolbar.setGlobalToolbar(true);
        reclamationForm.setScrollableY(true);
        reclamationForm.setSmoothScrolling(true);

  

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/allforkid/allforkids/web/app_dev.php/allUserReclamations/8");
        System.out.println("urrl " + con.getUrl());
        NetworkManager.getInstance().addToQueue(con);
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                ReclamationsService sr = new ReclamationsService();
                List<Reclamation> list = sr.getListReclamation(new String(con.getResponseData()));
                for (int i = 0; i < list.size(); i++) {
                    reclamationForm.addComponent(addItem(list.get(i)));
                }

                reclamationForm.refreshTheme();

            }
        });

    }
    
        public Container addItem(Reclamation r) {
        Container ctmain = new Container(BoxLayout.y());

        Label lbsubject = new Label("Sujet : " +r.getSubject());
        Label lbdate = new Label("Date d'envoi : "+r.getCreatedAt());
        Button b = new Button("Voir messages");
        b.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
        ctmain.add(lbsubject);
        ctmain.add(lbdate);
        ctmain.add(b);
        
        

   
        b.addActionListener((evt) -> {
          
            MessagesForm msg = new MessagesForm(r.getId());
            msg.getMessageForm().show();
            msg.getMessageForm().getToolbar().addCommandToLeftBar("retour", null, (ev) -> reclamationForm.showBack());

              });

        ctmain.setLeadComponent(b);

        return ctmain;

    }


    public Form getReclamationForm() {
        return reclamationForm;
    }

    public void setReclamationForm(Form reclamationForm) {
        this.reclamationForm = reclamationForm;
    }

}
