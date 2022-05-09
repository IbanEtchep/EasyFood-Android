package com.example.easyfood42.modele;

public class Evaluer {
    private long idE;
    private String commentaire;
    private boolean commentaireVisible;
    private boolean commentaireModere;
    private int respectRecette;
    private int estetiquePlat;
    private int cout;
    private int qualiteNourriture;

    public Evaluer(long idE,String commentaire, int respectRecette, int estetiquePlat, int cout, int qualiteNourriture) {
        this.idE = idE;
        this.commentaire = commentaire;
        this.commentaireVisible = false;
        this.commentaireModere = false;
        this.respectRecette = respectRecette;
        this.estetiquePlat = estetiquePlat;
        this.cout = cout;
        this.qualiteNourriture = qualiteNourriture;
    }

    public long getIdE() {
        return idE;
    }

    public void setIdE(long idE) {
        this.idE = idE;
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

    public void setQualiteNourriture(int qualiteNourriture) {
        this.qualiteNourriture = qualiteNourriture;
    }
}
