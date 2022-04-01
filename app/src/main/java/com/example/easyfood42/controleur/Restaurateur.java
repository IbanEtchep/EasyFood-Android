package com.example.easyfood42.controleur;

public class Restaurateur extends Utilisateur{

    public Restaurateur(long idU, String mailU, String passwd, String pseudoU, String nomU, String numAdrU, String nomAdrU, String cpU, String villeU, long idTU) {
        super(idU, mailU, passwd, pseudoU, nomU, numAdrU, nomAdrU, cpU, villeU, idTU);
    }

    public Restaurateur(Utilisateur utilisateur) {
        super(utilisateur.getIdU(), utilisateur.getMailU(), utilisateur.getPasswd(), utilisateur.getPseudoU(), utilisateur.getNomU(), utilisateur.getNumAdrU(), utilisateur.getNomAdrU(), utilisateur.getCpU(), utilisateur.getVilleU(), utilisateur.getIdTU());
    }
}
