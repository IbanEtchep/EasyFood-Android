package com.example.easyfood42.controleur;

public class Moderateur extends Utilisateur{

    public Moderateur(long idU, String mailU, String passwd, String pseudoU, String nomU, String prenomU, String numAdrU, String nomAdrU, String cpU, String villeU, long idTU) {
        super(idU, mailU, passwd, pseudoU, nomU, prenomU, numAdrU, nomAdrU, cpU, villeU, idTU);
    }

    public Moderateur(Utilisateur utilisateur) {
        super(utilisateur.getIdU(), utilisateur.getMailU(), utilisateur.getPasswd(), utilisateur.getPseudoU(), utilisateur.getNomU(), utilisateur.getPrenomU(), utilisateur.getNumAdrU(), utilisateur.getNomAdrU(), utilisateur.getCpU(), utilisateur.getVilleU(), utilisateur.getIdTU());
    }
}
