/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.GUI;

import static com.allforkids.Services.LoginService.currentUser;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Syrine
 */
public class ParentForm {

    Form parentForm;
    private Resources theme;

    public ParentForm() {
        theme = UIManager.initFirstTheme("/theme");

        parentForm = new Form("Welcome " + currentUser.getNomUser() + " " + currentUser.getPrenomUser());
        Toolbar tb = parentForm.getToolbar();
        Image icon = theme.getImage("logo.png");
        Container topBar = BorderLayout.east(new Label(icon));
        topBar.add(BorderLayout.SOUTH, new Label(currentUser.getEmailUser()));
        topBar.setUIID("SideCommand");
        tb.addComponentToSideMenu(topBar);

        tb.addMaterialCommandToSideMenu("Etablissement", FontImage.MATERIAL_HOME, e -> {
        });
        tb.addMaterialCommandToSideMenu("Reclamations", FontImage.MATERIAL_WEB, e -> {
        });
        tb.addMaterialCommandToSideMenu("Se deconnecter", FontImage.MATERIAL_SETTINGS, e -> {
        });

    }

    public Form getParentForm() {
        return parentForm;
    }

    public void setParentForm(Form parentForm) {
        this.parentForm = parentForm;
    }
    

}
