package com.example.easyfood42.modele;

import com.example.easyfood42.modele.user.Restaurateur;

public class Resto {

    private long idR;
    private String nomR;
    private String numAdrR;
    private String rueAdrR;
    private String cpR;
    private String villeR;
    private int idU;

    public Resto(long idR, String nomR, String numAdrR, String rueAdrR, String cpR, String villeR, int idU) {
        this.idR = idR;
        this.nomR = nomR;
        this.numAdrR = numAdrR;
        this.rueAdrR = rueAdrR;
        this.cpR = cpR;
        this.villeR = villeR;
        this.idU = idU;
    }

    public long getIdR() {
        return idR;
    }

    public void setIdR(long idR) {
        this.idR = idR;
    }

    public String getNomR() {
        return nomR;
    }

    public void setNomR(String nomR) {
        this.nomR = nomR;
    }

    public String getNumAdrR() {
        return numAdrR;
    }

    public void setNumAdrR(String numAdrR) {
        this.numAdrR = numAdrR;
    }

    public String getRueAdrR() {
        return rueAdrR;
    }

    public void setRueAdrR(String rueAdrR) {
        this.rueAdrR = rueAdrR;
    }

    public String getCpR() {
        return cpR;
    }

    public void setCpR(String cpR) {
        this.cpR = cpR;
    }

    public String getVilleR() {
        return villeR;
    }

    public void setVilleR(String villeR) {
        this.villeR = villeR;
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    @Override
    public String toString() {
        return "Resto{" +
                "idR=" + idR +
                ", nomR='" + nomR + '\'' +
                ", numAdrR='" + numAdrR + '\'' +
                ", rueAdrR='" + rueAdrR + '\'' +
                ", cpR='" + cpR + '\'' +
                ", villeR='" + villeR + '\'' +
                ", idU=" + idU +
                '}';
    }
}
