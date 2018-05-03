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
public class Statistique {
    private float avgrating;
    private String nomEtab;

    public float getAvgrating() {
        return avgrating;
    }

    public void setAvgrating(float avgrating) {
        this.avgrating = avgrating;
    }

    public String getNomEtab() {
        return nomEtab;
    }

    public void setNomEtab(String nomEtab) {
        this.nomEtab = nomEtab;
    }

    @Override
    public String toString() {
        return "Statistique{" + "avgrating=" + avgrating + ", nomEtab=" + nomEtab + '}';
    }
    
    
    
}
