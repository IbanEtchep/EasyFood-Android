package com.example.easyfood42.controleur;

import android.app.Application;

import com.example.easyfood42.modele.user.Utilisateur;

public class EasyFoodApplication extends Application {

    private Utilisateur utilisateur;

    public Utilisateur getConnectedUtilisateur() {
        return utilisateur;
    }

    public void setConnectedUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

}
