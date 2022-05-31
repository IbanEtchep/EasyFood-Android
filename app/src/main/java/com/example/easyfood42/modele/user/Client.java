package com.example.easyfood42.modele.user;

import com.example.easyfood42.modele.user.Utilisateur;

public class Client extends Utilisateur {

    private int noteEasyFood;
    private String commentaireEasyFood;
    private boolean commentaireVisible;

    public Client(Utilisateur utilisateur, int noteEasyFood, String commentaireEasyFood, boolean commentaireVisible) {
        super(utilisateur.getIdU(), utilisateur.getMailU(), utilisateur.getPseudoU(), utilisateur.getPasswd(),  utilisateur.getNomU(), utilisateur.getPrenomU(), utilisateur.getNumAdrU(), utilisateur.getNomAdrU(), utilisateur.getCpU(), utilisateur.getVilleU(), utilisateur.getIdTU());
        this.noteEasyFood = noteEasyFood;
        this.commentaireEasyFood = commentaireEasyFood;
        this.commentaireVisible = commentaireVisible;
    }

    public int getNoteEasyFood() {
        return noteEasyFood;
    }

    public void setNoteEasyFood(int noteEasyFood) {
        this.noteEasyFood = noteEasyFood;
    }

    public String getCommentaireEasyFood() {
        return commentaireEasyFood;
    }

    public void setCommentaireEasyFood(String commentaireEasyFood) {
        this.commentaireEasyFood = commentaireEasyFood;
    }

    public boolean getCommentaireVisible() {
        return commentaireVisible;
    }

    public void setCommentaireVisible(boolean commentaireVisible) {
        this.commentaireVisible = commentaireVisible;
    }
}
