/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.GUI;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Syrine
 */
public class LoginForm {

    Form loginForm;
    TextField tusername;
    TextField tpassword;
    Button btnlogin;
    
    
    


    public LoginForm() {
        loginForm = new Form("Welcome to all for kids",BoxLayout.y());
        loginForm.setScrollableY(true);
        loginForm.setSmoothScrolling(true);
        
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
           /* ServiceTask ser = new ServiceTask();
            Task t = new Task(0, tnom.getText(), tetat.getText());
            ser.ajoutTask(t);*/

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
