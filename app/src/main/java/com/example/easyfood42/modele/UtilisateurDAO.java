package com.example.easyfood42.modele;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.easyfood42.controleur.Utilisateur;

public class UtilisateurDAO {

	private static String base = "BDeasyfood";
	private static int version = 1;
	private BdSQLiteOpenHelper accesBD;
	
	public UtilisateurDAO(Context ct){
		accesBD = new BdSQLiteOpenHelper(ct, base, null, version);
	}

	public Utilisateur getUtilisateurByMail(String mailU){
		Utilisateur unUtil = null;
		Cursor curseur;
		curseur = accesBD.getReadableDatabase().rawQuery("select * from utilisateur where mailU='"+mailU+"';",null);
		if (curseur.getCount() > 0) {
			curseur.moveToFirst();
			unUtil = new Utilisateur(curseur.getLong(0),curseur.getString(1),curseur.getString(2),curseur.getLong(3));
		}
		return unUtil;
	}
}
