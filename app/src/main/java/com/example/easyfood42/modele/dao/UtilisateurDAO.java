package com.example.easyfood42.modele.dao;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.easyfood42.modele.user.Administrateur;
import com.example.easyfood42.modele.user.Client;
import com.example.easyfood42.modele.user.Moderateur;
import com.example.easyfood42.modele.user.Restaurateur;
import com.example.easyfood42.modele.user.Utilisateur;
import com.example.easyfood42.modele.BdSQLiteOpenHelper;

public class UtilisateurDAO extends DAO{

    public UtilisateurDAO(Context context) {
        super(context);
    }

    public Utilisateur findByMail(String mailU) {
        Utilisateur unUtil = null;
        //, String nomU, String numAdrU, String nomAdrU, String cpU, String villeU, long idTU) {
        String sql =
                "select idU, mailU, pseudoU, passwd,  nomU,prenomU, numAdrU, nomAdrU, cpU, villeU, idTu " +
                        "from utilisateur where mailU=?;";
        try (Cursor cursor = accesBD.getReadableDatabase().rawQuery(sql, new String[] {mailU})) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                unUtil = new Utilisateur(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getLong(10)
                );
                Log.d("testLog", "--------------------" + unUtil.getPasswd());
                return getTypedUser(unUtil);
            }
        }
        return null;
    }

    private Client findClientByUtilisateur(Utilisateur utilisateur) {
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
                return findClientByUtilisateur(utilisateur);
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
