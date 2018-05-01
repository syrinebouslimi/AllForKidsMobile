/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.GUI;

import com.allforkids.Entities.Favoris;
import com.allforkids.Entities.Reclamation;
import com.allforkids.Services.FavorisService;
import static com.allforkids.Services.LoginService.currentUser;
import com.allforkids.Services.ReclamationsService;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
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

        reclamationForm.getToolbar().addCommandToOverflowMenu("Envoyer reclamation", null, ev -> {

            NewReclamation newReclamation = new NewReclamation();
            newReclamation.getNewRecForm().show();

        });

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/allforkid/allforkids/web/app_dev.php/allUserReclamations/8");
        System.out.println("urrl " + con.getUrl());
        NetworkManager.getInstance().addToQueue(con);
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                ReclamationsService sr = new ReclamationsService();
                List<Reclamation> list = sr.getListReclamation(new String(con.getResponseData()));
                System.out.println(new String(con.getResponseData()));
  
            }
        });

    }

    public Form getReclamationForm() {
        return reclamationForm;
    }

    public void setReclamationForm(Form reclamationForm) {
        this.reclamationForm = reclamationForm;
    }
    

}
