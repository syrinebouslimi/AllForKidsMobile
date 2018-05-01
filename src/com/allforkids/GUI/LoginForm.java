/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.GUI;

import com.allforkids.Services.LoginService;
import static com.allforkids.Services.LoginService.currentUser;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Syrine
 */
public class LoginForm {

    Form loginForm;
    TextField tusername;
    TextField tpassword;
    Button btnlogin;
    private Resources theme;


    public LoginForm() {
        theme = UIManager.initFirstTheme("/theme");

        FlowLayout flowLayout = new FlowLayout(Container.CENTER, Container.CENTER);

        loginForm = new Form("Welcome to all for kids", flowLayout);
        loginForm.setScrollableY(true);
        loginForm.setSmoothScrolling(true);

         ImageViewer ivlogo = new ImageViewer(theme.getImage("logo.png"));

        loginForm.add(ivlogo);
        tusername = new TextField();
        tpassword = new TextField();
        btnlogin = new Button("Se connecter");
        
        loginForm.add(tusername);
        tusername.setHint("Nom d'utilisateur");
        loginForm.add(tpassword);
        tpassword.setHint("Mot de passe");
        tpassword.setConstraint(TextField.PASSWORD);

        loginForm.add(btnlogin);
        btnlogin.addActionListener((e) -> {
            if (tusername.getText().length() > 0 && tpassword.getText().length() > 0) {
                LoginService loginService = new LoginService();
                loginService.loginApi(tusername.getText(), tpassword.getText());
            } else {
                Dialog.show("Connexion echoué", "Veuillez renseigner les deux champs", "ok", null);

            }
            System.out.println(currentUser);

        });

    }

    public Form getLoginForm() {
        return loginForm;
    }

    public void setLoginForm(Form loginForm) {
        this.loginForm = loginForm;
    }

    public TextField getTusername() {
        return tusername;
    }

    public void setTusername(TextField tusername) {
        this.tusername = tusername;
    }

    public TextField getTpassword() {
        return tpassword;
    }

    public void setTpassword(TextField tpassword) {
        this.tpassword = tpassword;
    }

    public Button getBtnlogin() {
        return btnlogin;
    }

    public void setBtnlogin(Button btnlogin) {
        this.btnlogin = btnlogin;
    }

}
