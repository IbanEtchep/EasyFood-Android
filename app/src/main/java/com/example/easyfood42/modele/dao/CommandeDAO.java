package com.example.easyfood42.modele.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.easyfood42.modele.Commande;
import com.example.easyfood42.modele.Plat;
import com.example.easyfood42.modele.user.Utilisateur;
import com.example.easyfood42.util.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandeDAO extends DAO {

    /*
    commande (_idC_, dateC, commentaireClientC, datePrepC,  dateLivrC, modeReglementC, #idU, #idR)
     */

    public CommandeDAO(Context context) {
        super(context);
    }

    public Commande findById(long idC) {
        Commande commande = null;
        String sql = "SELECT * FROM commande WHERE idC=?;";
        SQLiteDatabase db = accesBD.getReadableDatabase();
        RestoDAO restoDAO = new RestoDAO(context);

        try (Cursor cursor = db.rawQuery(sql, new String[]{idC + ""})) {
            if (cursor.moveToFirst()) {
                commande = getCommandFromCursor(cursor, restoDAO);
            }
        }

        return commande;
    }

    public List<Commande> findByUser(Utilisateur utilisateur) {
        List<Commande> commandes = new ArrayList<>();
        String sql = "SELECT * FROM commande WHERE idU=?;";
        SQLiteDatabase db = accesBD.getReadableDatabase();
        RestoDAO restoDAO = new RestoDAO(context);

        try (Cursor cursor = db.rawQuery(sql, new String[]{utilisateur.getIdU() + ""})) {
            while (cursor.moveToNext()) {
                commandes.add(getCommandFromCursor(cursor, restoDAO));
            }
        }

        return commandes;
    }

    private Commande getCommandFromCursor(Cursor cursor, RestoDAO restoDAO) {
        Log.d("cursor", cursor.getInt(7)+"");
        Commande commande = new Commande(
                cursor.getLong(0),
                DateUtils.getDateFromTimeMillis(cursor.getLong(1)),
                cursor.getString(2),
                DateUtils.getDateFromTimeMillis(cursor.getLong(3)),
                DateUtils.getDateFromTimeMillis(cursor.getLong(4)),
                cursor.getString(5),
                restoDAO.findById(cursor.getInt(7)),
                cursor.getLong(6)
        );
        commande.setContenu(getPlatsInCommande(commande.getIdC()));
        return commande;
    }

    public Commande addCommande(Commande commande) {
        String sql = "INSERT INTO commande (dateC, commentaireClientC, datePrepC, dateLivrC, modeReglementC, idU, idR) " +
                "VALUES(?, ?, ?, ?, ?, ?) ";
        SQLiteDatabase db = accesBD.getReadableDatabase();
        db.execSQL(sql, new String[]{
                commande.getDateC().getTime() + "",
                commande.getCommentaireClientC(),
                commande.getDatePrepC().getTime() + "",
                commande.getDateLivrC().getTime() + "",
                commande.getModeReglementC(),
                commande.getIdU()+"",
                commande.getResto().getIdR()+"",
        });

        savePlatsInCommande(commande);
        return commande;
    }

    public Map<Plat, Integer> getPlatsInCommande(long idC) {
        Map<Plat, Integer> commandes = new HashMap<>();
        String sql = "SELECT P.idP, P.nomP, P.prixFournisseurP, P.prixClientP, P.platVisible, P.photoP, P.descriptionP, CC.quantity " +
                "FROM contenu_commande CC " +
                "JOIN commande C ON C.idC=CC.idC " +
                "JOIN plat P ON P.idP=CC.idP " +
                "WHERE CC.idC=?;";

        SQLiteDatabase db = accesBD.getReadableDatabase();

        try (Cursor cursor = db.rawQuery(sql, new String[]{idC + ""})) {
            while (cursor.moveToNext()) {
                Plat plat = new Plat(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getInt(3),
                        cursor.getInt(4) != 0,
                        cursor.getString(5),
                        cursor.getString(6)
                );
                int quantity = cursor.getInt(7);
                commandes.put(plat, quantity);
            }
        }

        return commandes;
    }

    public void savePlatsInCommande(Commande commande) {
        SQLiteDatabase db = accesBD.getWritableDatabase();

        String deleteSQL = "DELETE FROM contenu_commande WHERE idC=?;";
        db.execSQL(deleteSQL, new String[]{commande.getIdC() + ""});

        String insertSql = "INSERT INTO contenu_commande(idC, idP, quantity) VALUES (?, ?, ?);";
        for (Map.Entry<Plat, Integer> entry : commande.getContenu().entrySet()) {
            db.execSQL(insertSql, new String[]{
                    commande.getIdC() + "",
                    entry.getKey().getIdP() + "",
                    entry.getValue() + ""
            });
        }
    }

}
