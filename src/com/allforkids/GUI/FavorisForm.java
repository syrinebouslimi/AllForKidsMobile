/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.GUI;

import com.allforkids.Entities.Favoris;
import com.allforkids.Services.FavorisService;
import static com.allforkids.Services.LoginService.currentUser;
import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.util.List;

/**
 *
 * @author Syrine
 */
public class FavorisForm {

    Form favorisForm;
    String urlForImage = "http://localhost/allforkid/allforkids/web/uploads/images/";
    FavorisService ser;

    private Resources theme;

    public FavorisForm() {

        theme = UIManager.initFirstTheme("/theme");
        favorisForm = new Form("Liste des favoris");
        Toolbar tb = favorisForm.getToolbar();
        tb.addCommandToLeftBar("retour", null, (ev) -> {
            ParentForm parentForm = new ParentForm();
            parentForm.getParentForm().showBack();
        });
        ConnectionRequest con = new ConnectionRequest();

        con.setUrl("http://localhost/allforkid/allforkids/web/app_dev.php/allUserFavoris/userid/" + currentUser.getId().substring(0, currentUser.getId().indexOf(".")));
        System.out.println("urrl " + con.getUrl());
        NetworkManager.getInstance().addToQueue(con);
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                ser = new FavorisService();
                List<Favoris> list = ser.getListFavoris(new String(con.getResponseData()));
                System.out.println(new String(con.getResponseData()));

                for (int i = 0; i < list.size(); i++) {
                    favorisForm.addComponent(addItem(list.get(i)));
                }

                favorisForm.refreshTheme();
            }
        });

    }

    public Container addItem(Favoris f) {
        Container ctmain = new Container(BoxLayout.x());

        EncodedImage enco = EncodedImage.createFromImage(theme.getImage("round.png"), false);
        URLImage imageserveur = URLImage.createToStorage(enco, urlForImage + f.getImageEtablissement(), urlForImage + f.getImageEtablissement(), URLImage.RESIZE_SCALE_TO_FILL);
        ImageViewer img = new ImageViewer(imageserveur);
        ctmain.add(img);
        Container cttexts = new Container(BoxLayout.y());
        Label lbnom = new Label(f.getNomEtablissement());
        Label lbtype = new Label(f.getTypeEtablissement());
        Button b = new Button("Supprimer");
        b.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
        b.addActionListener((e) -> {
           Dialog.show("Suppression favoris", "Etes-vous sûr de vouloir supprimer ce favoris de la liste", "Valider", "Annuler");
           if (Dialog.show("Suppression favoris", "Etes-vous sûr de vouloir supprimer ce favoris de la liste", "Valider", "Annuler")){
               ser.supprimerFavoris(f.getIdFavoris());
           }

                });
        cttexts.add(lbnom);
        cttexts.add(lbtype);
        cttexts.add(b);
        ctmain.add(cttexts);
        

        return ctmain;

    }

    public Form getFavorisForm() {
        return favorisForm;
    }

    public void setFavorisForm(Form favorisForm) {
        this.favorisForm = favorisForm;
    }

}
