package com.example.easyfood42.modele;

import com.example.easyfood42.util.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class Commande {

    private long idC;
    private String commentaireClientC;
    private Date dateC;
    private Date datePrepC;
    private Date dateLivrC;
    private String modeReglementC;
    private Resto resto;
    private long idU;
    private Map<Plat, Integer> contenu;

    public Commande(long idC, Date dateC, String commentaireClientC, Date dateLivrC, Date datePrepC, String modeReglementC, Resto resto, long idU) {
        this.idC = idC;
        this.dateC = dateC;
        this.dateLivrC = dateLivrC;
        this.datePrepC = datePrepC;
        this.commentaireClientC = commentaireClientC;
        this.modeReglementC = modeReglementC;
        this.resto = resto;
        this.idU = idU;
    }

    public long getIdC() {
        return idC;
    }

    public void setIdC(long idC) {
        this.idC = idC;
    }

    public long getIdU() {
        return idU;
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
        return datePrepC != null;
    }

    public boolean isCommandeLivree() {
        return dateLivrC != null;
    }

    public Date getDatePrepC() {
        return datePrepC;
    }

    public void setDatePrepC(Date datePrepC) {
        this.datePrepC = datePrepC;
    }

    public void setContenu(Map<Plat, Integer> contenu) {
        this.contenu = contenu;
    }

    public Resto getResto() {
        return resto;
    }

    public void setResto(Resto resto) {
        this.resto = resto;
    }

    public Map<Plat, Integer> getContenu() {
        return contenu;
    }

    public int getQuantity(Plat plat) {
        if(getContenu().containsKey(plat)) {
            return getContenu().get(plat);
        }
        return 0;
    }

    public int getNbPlats() {
        int nbPlats = 0;

        for (Map.Entry<Plat, Integer> entry : contenu.entrySet()) {
            nbPlats += entry.getValue();
        }

        return  nbPlats;
    }

    public int getPrixTotal() {
        int prixTotal = 0;

        for (Map.Entry<Plat, Integer> entry : contenu.entrySet()) {
            prixTotal += entry.getValue() * entry.getKey().getPrixClientP();
        }

        return  prixTotal;
    }

    public String getStatut() {
        if(!isCommandePreparee()) {
            return "En cours de préparation.";
        }else if(!isCommandeLivree()) {
            return "En cours de livraison";
        }else {
            return "Livré le " + DateUtils.format(dateLivrC);
        }
    }
}
