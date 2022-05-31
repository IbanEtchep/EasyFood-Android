package com.example.easyfood42.modele.dao;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.easyfood42.modele.Resto;

public class RestoDAO extends DAO {

    /**
     * resto (_idR_, nomR, numAdrR, rueAdrR, cpR, villeR, #idU)
     */

    public RestoDAO(Context ct) {
        super(ct);
    }

    public Resto findById(long idR) {
        Resto resto = null;
        String sql = "SELECT idR, nomR, numAdrR, rueAdrR, cpR, villeR, idU FROM resto WHERE idR=?";


        try (Cursor cursor = accesBD.getReadableDatabase().rawQuery(sql, new String[]{idR + ""})) {

            if (cursor.moveToFirst()) {
                resto = new Resto(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getInt(6)
                );
            }
        }

        return resto;
    }

}
