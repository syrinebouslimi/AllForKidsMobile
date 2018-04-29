/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.GUI;

import com.allforkids.Entities.Etablissement;
import com.allforkids.Services.EtablissementService;
import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.util.List;

/**
 *
 * @author Syrine
 */
public class EtablissementForm {

    Form etablissementForm;
    String urlForImage = "http://localhost/allforkid/allforkids/web/uploads/images/";

    private Resources theme;

    public EtablissementForm() {

        theme = UIManager.initFirstTheme("/theme");
        etablissementForm = new Form("Liste des etablissements");
        Toolbar tb = etablissementForm.getToolbar();
        tb.addCommandToLeftBar("retour", null, (ev) -> {
            ParentForm parentForm = new ParentForm();
            parentForm.getParentForm().showBack();
        });
        ConnectionRequest con = new ConnectionRequest();

        con.setUrl("http://localhost/allforkid/allforkids/web/app_dev.php/getAllEtablissement");
        NetworkManager.getInstance().addToQueue(con);
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                EtablissementService ser = new EtablissementService();
                List<Etablissement> list = ser.getListEablissement(new String(con.getResponseData()));

                for (int i = 0; i < list.size(); i++) {
                    etablissementForm.addComponent(addItem(list.get(i)));
                }

                etablissementForm.refreshTheme();
            }
        });

    }

    public Container addItem(Etablissement e) {
        Container ctmain = new Container(BoxLayout.x());

        EncodedImage enco = EncodedImage.createFromImage(theme.getImage("round.png"), false);
        URLImage imageserveur = URLImage.createToStorage(enco, urlForImage + e.getImageEtablissement(), urlForImage + e.getImageEtablissement(), URLImage.RESIZE_SCALE_TO_FILL);
        ImageViewer img = new ImageViewer(imageserveur);
        ctmain.add(img);
        Container cttexts = new Container(BoxLayout.y());
        Label lbnom = new Label(e.getNomEtablissement());
        Label lbtype = new Label(e.getTypeEtablissement());
        cttexts.add(lbnom);
        cttexts.add(lbtype);
        ctmain.add(cttexts);

       /* Form result = new Form("", new FlowLayout());
        Toolbar.setGlobalToolbar(true);
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, s);
        result.getToolbar().addCommandToLeftBar(cb.getSelectedItem().toString(), icon, (e) -> hi.show());
        ImageViewer teacher = new ImageViewer();
        TextField tf = new TextField();
        Slider sl = new Slider();
        sl.setMinValue(0);
        sl.setMaxValue(10);
        sl.setEditable(true);
        Button btn = new Button("Valider");
        result.add(teacher);
        result.add(tf);
        result.add(sl);
*/
      //  result.add(btn);

        lbnom.addPointerPressedListener((evt) -> {
            System.out.println(e);
            EtablissementDetailsForm detail = new EtablissementDetailsForm(e);
            detail.getDetail().show();
            detail.getDetail().getToolbar().addCommandToLeftBar("retour", null, (ev) -> etablissementForm.showBack());

            
            
            
            
            
            System.out.println("yeah clicked");
        });

        ctmain.setLeadComponent(lbnom);

        return ctmain;

    }

    public Form getEtablissementForm() {
        return etablissementForm;
    }

    public void setEtablissementForm(Form etablissementForm) {
        this.etablissementForm = etablissementForm;
    }

}
