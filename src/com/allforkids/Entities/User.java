/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.Entities;

import java.util.List;

/**
 *
 * @author sana
 */
public class User {
   private String id;
   private String nomUser;
   private String prenomUser;
   private String emailUser;
   private String passwordUser;
   private List<String> rolesUser;

    public User() {
    }

    public User(String id, String nomUser, String prenomUser, String emailUser, String passwordUser, List<String> rolesUser) {
        this.id = id;
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
        this.emailUser = emailUser;
        this.passwordUser = passwordUser;
        this.rolesUser = rolesUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public String getPrenomUser() {
        return prenomUser;
    }

    public void setPrenomUser(String prenomUser) {
        this.prenomUser = prenomUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    public List<String> getRolesUser() {
        return rolesUser;
    }

    public void setRolesUser(List<String> rolesUser) {
        this.rolesUser = rolesUser;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nomUser=" + nomUser + ", prenomUser=" + prenomUser + ", emailUser=" + emailUser + ", passwordUser=" + passwordUser + ", rolesUser=" + rolesUser + '}';
    }
    
    
    

   
}
