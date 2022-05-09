package com.example.easyfood42.modele.dao;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.easyfood42.modele.Administrateur;
import com.example.easyfood42.modele.Client;
import com.example.easyfood42.modele.Moderateur;
import com.example.easyfood42.modele.Restaurateur;
import com.example.easyfood42.modele.Utilisateur;
import com.example.easyfood42.modele.BdSQLiteOpenHelper;

public class UtilisateurDAO {

    private static String base = "BDeasyfood";
    private static int version = 1;
    private BdSQLiteOpenHelper accesBD;

    public UtilisateurDAO(Context ct) {
        accesBD = new BdSQLiteOpenHelper(ct, base, null, version);
    }

    public Utilisateur getUtilisateurByMail(String mailU) {
        Utilisateur unUtil = null;
        //, String nomU, String numAdrU, String nomAdrU, String cpU, String villeU, long idTU) {
        String sql =
                "select idU, mailU, pseudoU, passwd,  nomU,prenomU, numAdrU, nomAdrU, cpU, villeU, idTu " +
                        "from utilisateur where mailU='" + mailU + "';";
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery(sql, null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            Log.d("testLog", curseur.getLong(0) +
                    curseur.getString(1) +
                    curseur.getString(2) +
                    curseur.getString(3) +
                    curseur.getString(4) +
                    curseur.getString(5) +
                    curseur.getString(6) +
                    curseur.getString(7) +
                    curseur.getString(8) +
                    curseur.getString(9) +
                    curseur.getLong(10));
            unUtil = new Utilisateur(
                    curseur.getLong(0),
                    curseur.getString(1),
                    curseur.getString(2),
                    curseur.getString(3),
                    curseur.getString(4),
                    curseur.getString(5),
                    curseur.getString(6),
                    curseur.getString(7),
                    curseur.getString(8),
                    curseur.getString(9),
                    curseur.getLong(10)
            );
            Log.d("testLog", "--------------------" + unUtil.getPasswd());
        }
        return getTypedUser(unUtil);
    }

    private Client getClientByUtilisateur(Utilisateur utilisateur) {
        String sql =
                "SELECT noteEasyFood, commentaireEasyFood, commentaireVisible " +
                "FROM utilisateur " +
                "WHERE idU='" + utilisateur.getIdU() + "';";
        Cursor cursor;
        cursor = accesBD.getReadableDatabase().rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int noteEasyFood = cursor.getInt(0);
            String commentaireEasyFood = cursor.getString(1);
            boolean commentaireVisible = cursor.getInt(2) != 0;
            return new Client(utilisateur, noteEasyFood, commentaireEasyFood, commentaireVisible);
        }

        return null;
    }

    private Utilisateur getTypedUser(Utilisateur utilisateur) {
        switch ((int) utilisateur.getIdTU()) {
            case 1:
                return getClientByUtilisateur(utilisateur);
            case 2:
                return new Restaurateur(utilisateur);
            case 3:
                return new Moderateur(utilisateur);
            case 4:
                return new Administrateur(utilisateur);
            default:
                return utilisateur;
        }
    }
}
