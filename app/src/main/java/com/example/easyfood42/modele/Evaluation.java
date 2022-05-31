package com.example.easyfood42.modele;

import java.util.Date;

public class Evaluation {

    private long idE;
    private long idR;
    private long idU;
    private Date date;
    private String commentaire;
    private boolean commentaireVisible;
    private boolean commentaireModere;
    private int respectRecette;
    private int estetiquePlat;
    private int cout;
    private int qualiteNourriture;

    public Evaluation(long idR, long idU, Date date, String commentaire, boolean commentaireModere, boolean commentaireVisible, int respectRecette, int estetiquePlat, int cout, int qualiteNourriture) {
        this.idR = idR;
        this.idU = idU;
        this.commentaire = commentaire;
        this.commentaireVisible = commentaireVisible;
        this.commentaireModere = commentaireModere;
        this.respectRecette = respectRecette;
        this.estetiquePlat = estetiquePlat;
        this.cout = cout;
        this.qualiteNourriture = qualiteNourriture;
    }

    public long getIdU() {
        return idU;
    }

    public long getIdR() {
        return idR;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public boolean isCommentaireVisible() {
        return commentaireVisible;
    }

    public void setCommentaireVisible(boolean commentaireVisible) {
        this.commentaireVisible = commentaireVisible;
    }

    public boolean isCommentaireModere() {
        return commentaireModere;
    }

    public void setCommentaireModere(boolean commentaireModere) {
        this.commentaireModere = commentaireModere;
    }

    public int getRespectRecette() {
        return respectRecette;
    }

    public void setRespectRecette(int respectRecette) {
        this.respectRecette = respectRecette;
    }

    public int getEstetiquePlat() {
        return estetiquePlat;
    }

    public void setEstetiquePlat(int estetiquePlat) {
        this.estetiquePlat = estetiquePlat;
    }

    public int getCout() {
        return cout;
    }

    public void setCout(int cout) {
        this.cout = cout;
    }

    public int getQualiteNourriture() {
        return qualiteNourriture;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setQualiteNourriture(int qualiteNourriture) {
        this.qualiteNourriture = qualiteNourriture;
    }
}
