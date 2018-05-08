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
public class AdminForm {

    Form adminForm;

    private Resources theme;

    public AdminForm() {
        theme = UIManager.initFirstTheme("/theme");

        adminForm = new Form("Welcome " + currentUser.getNomUser() + " " + currentUser.getPrenomUser());
        Toolbar tb = adminForm.getToolbar();
        Image icon = theme.getImage("logo.png");
        Container topBar = BorderLayout.east(new Label(icon));
        topBar.add(BorderLayout.SOUTH, new Label(currentUser.getEmailUser()));
        topBar.setUIID("SideCommand");
        tb.addComponentToSideMenu(topBar);

        tb.addMaterialCommandToSideMenu("Notifications", FontImage.MATERIAL_HOME, e -> {
            NotificationForm notifForm = new NotificationForm();
            notifForm.getNotiForm().show();
            
            
            
        });
        tb.addMaterialCommandToSideMenu("Reclamations", FontImage.MATERIAL_WEB, e -> {
               ReclamationForm reclamationForm = new ReclamationForm();
            reclamationForm.getReclamationForm().show();
        });
        tb.addMaterialCommandToSideMenu("Demande d'ajout d'etablissement", FontImage.MATERIAL_SETTINGS, e -> {
        });
        tb.addMaterialCommandToSideMenu("Statistiques", FontImage.MATERIAL_SETTINGS, e -> {
            ChartsForm chartsForm = new ChartsForm();
            chartsForm.createPieChartForm();
        });
        tb.addMaterialCommandToSideMenu("Se déconnecter", FontImage.MATERIAL_SETTINGS, e -> {
            LoginForm loginForm = new LoginForm();
            loginForm.getLoginForm().showBack();
        });

    }

    public Form getAdminForm() {
        return adminForm;
    }

    public void setAdminForm(Form adminForm) {
        this.adminForm = adminForm;
    }

}
