package com.example.easyfood42.modele;

import android.content.Context;
import android.database.Cursor;

import com.example.easyfood42.controleur.Evaluer;

public class EvaluerDAO {

    private static String base = "BDeasyfood";
    private static int version = 1;
    private BdSQLiteOpenHelper accesBD;

    public EvaluerDAO(Context ct){
        accesBD = new BdSQLiteOpenHelper(ct, base, null, version);
    }

    public Evaluer getEvaluerDAOById(long idE){
        Evaluer uneEval = null;
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select * from evaluer where idE="+idE+";",null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            uneEval = new Evaluer(idE,curseur.getString(1),curseur.getInt(2),curseur.getInt(3),curseur.getInt(4),curseur.getInt(5));
        }
        return uneEval;
    }

}
