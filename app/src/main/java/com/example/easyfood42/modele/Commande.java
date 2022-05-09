package com.example.easyfood42.modele;

import java.text.DateFormat;
import java.util.Date;

public class Commande {
    private long idC;
    private Date dateC;
    private String commentaireClientC;
    private Date dateLivrC;
    private String modeReglementC;
    private boolean commandePreparee;


    public Commande(long idC, Date dateC, String commentaireClientC, Date dateLivrC, String modeReglementC, boolean commandePreparee) {
        this.idC = idC;
        this.dateC = dateC;
        this.commentaireClientC = commentaireClientC;
        this.dateLivrC = dateLivrC;
        this.modeReglementC = modeReglementC;
        this.commandePreparee = commandePreparee;
    }

    public long getIdC() {
        return idC;
    }

    public void setIdC(long idC) {
        this.idC = idC;
    }

    public Date getDateC() {
        return dateC;
    }

    public void setDateC(Date dateC) {
        this.dateC = dateC;
    }

    public String getCommentaireClientC() {
        return commentaireClientC;
    }

    public void setCommentaireClientC(String commentaireClientC) {
        this.commentaireClientC = commentaireClientC;
    }

    public Date getDateLivrC() {
        return dateLivrC;
    }

    public void setDateLivrC(Date dateLivrC) {
        this.dateLivrC = dateLivrC;
    }

    public String getModeReglementC() {
        return modeReglementC;
    }

    public void setModeReglementC(String modeReglementC) {
        this.modeReglementC = modeReglementC;
    }

    public boolean isCommandePreparee() {
        return commandePreparee;
    }

    public void setCommandePreparee(boolean commandePreparee) {
        this.commandePreparee = commandePreparee;
    }
}
