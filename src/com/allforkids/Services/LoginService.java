/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.Services;

import com.allforkids.Entities.User;
import com.allforkids.GUI.AdminForm;
import com.allforkids.GUI.ParentForm;
import com.allforkids.GUI.PrestataireForm;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sana
 */
public class LoginService {

    Dialog ip;
    public static User currentUser = null;

    public LoginService() {
        ip = new InfiniteProgress().showInifiniteBlocking();
    }

    public void loginApi(String email, String password) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/allforkid/allforkids/web/app_dev.php/getAllUsers";
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String json = new String(con.getResponseData());
            User currentUser = checkLogin(json, email, password);
            if (currentUser == null) {
                Dialog.show("Connexion invalide", "Veuillez vérifier vos données d'authentification", "valider", null);

            } else {
                if (currentUser.getRolesUser().contains("ROLE_ADMIN")){
                    AdminForm adminForm = new AdminForm();
                    adminForm.getAdminForm().show();
                }
                else if (currentUser.getRolesUser().contains("ROLE_PRESTATAIRE")){
                    PrestataireForm prestataireForm = new PrestataireForm();
                    prestataireForm.getPrestataireForm().show();
                } else {
                    ParentForm parentForm = new ParentForm();
                    parentForm.getParentForm().show();
                }
                
            
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public User checkLogin(String json, String email, String password) {

        currentUser = null;
        try {
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {

                String motdepasse = "";
                String id = obj.get("id").toString();
                String emailUser = obj.get("email").toString();
                String passwordUser = obj.get("password").toString();
                if ((passwordUser.indexOf('{')) >= 0) {
                    motdepasse = passwordUser.substring(0, passwordUser.indexOf("{"));
                } else {
                    motdepasse = passwordUser;
                }

                if (emailUser.equals(email) && password.equals(motdepasse)) {

                    String nom = obj.get("nomUser").toString();
                    String prenom = obj.get("prenomUser").toString();
                    List<String> roleList = (List<String>)obj.get("roles");
                    currentUser = new User(id, nom, prenom, emailUser, motdepasse, roleList);
                    
                    break;
                }

            }

        } catch (IOException ex) {
        }

        ip.dispose();

        return currentUser;

    }

}
