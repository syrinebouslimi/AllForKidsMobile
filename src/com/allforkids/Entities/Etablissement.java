/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allforkids.Entities;

/**
 *
 * @author Syrine
 */
public class Etablissement {
    
    String id;
    String nomEtablissement;
    String imageEtablissement;
    String descriptionEtablissement;
    String phoneEtablissement;
    String adresseEtablissement;
    String typeEtablissement;
    String avgRating;
    

      public Etablissement() {
       
    }

    public Etablissement(String id, String nomEtablissement, String imageEtablissement, String descriptionEtablissement, String phoneEtablissement, String adresseEtablissement, String typeEtablissement) {
        this.id = id;
        this.nomEtablissement = nomEtablissement;
        this.imageEtablissement = imageEtablissement;
        this.descriptionEtablissement = descriptionEtablissement;
        this.phoneEtablissement = phoneEtablissement;
        this.adresseEtablissement = adresseEtablissement;
        this.typeEtablissement = typeEtablissement;
    }

    public Etablissement(String nomEtablissement, String imageEtablissement, String adresseEtablissement, String typeEtablissement) {
        this.nomEtablissement = nomEtablissement;
        this.imageEtablissement = imageEtablissement;
        this.adresseEtablissement = adresseEtablissement;
        this.typeEtablissement = typeEtablissement;
    }
    

    public String getId() {
        return id;
    }

    public String getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(String avgRating) {
        this.avgRating = avgRating;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomEtablissement() {
        return nomEtablissement;
    }

    public void setNomEtablissement(String nomEtablissement) {
        this.nomEtablissement = nomEtablissement;
    }

    public String getImageEtablissement() {
        return imageEtablissement;
    }

    public void setImageEtablissement(String imageEtablissement) {
        this.imageEtablissement = imageEtablissement;
    }

    public String getDescriptionEtablissement() {
        return descriptionEtablissement;
    }

    public void setDescriptionEtablissement(String descriptionEtablissement) {
        this.descriptionEtablissement = descriptionEtablissement;
    }

    public String getPhoneEtablissement() {
        return phoneEtablissement;
    }

    public void setPhoneEtablissement(String phoneEtablissement) {
        this.phoneEtablissement = phoneEtablissement;
    }

    public String getAdresseEtablissement() {
        return adresseEtablissement;
    }

    public void setAdresseEtablissement(String adresseEtablissement) {
        this.adresseEtablissement = adresseEtablissement;
    }

    public String getTypeEtablissement() {
        return typeEtablissement;
    }

    public void setTypeEtablissement(String typeEtablissement) {
        this.typeEtablissement = typeEtablissement;
    }
    
    
    
}
