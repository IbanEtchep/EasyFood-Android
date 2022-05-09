package com.example.easyfood42.modele.dao;

import android.content.Context;
import android.database.Cursor;

import com.example.easyfood42.modele.TypeUtilisateur;
import com.example.easyfood42.modele.BdSQLiteOpenHelper;

public class TypeUtilisateurDAO {

	private static String base = "BDeasyfood";
	private static int version = 1;
	private BdSQLiteOpenHelper accesBD;

	public TypeUtilisateurDAO(Context ct){
		accesBD = new BdSQLiteOpenHelper(ct, base, null, version);
	}

	public TypeUtilisateur getTypeUtilisateurById(long idTU){
		TypeUtilisateur unTU = null;
		Cursor curseur;
		curseur = accesBD.getReadableDatabase().rawQuery("select * from type_utilisateur where idTU="+idTU+";",null);
		if (curseur.getCount() > 0) {
			curseur.moveToFirst();
			unTU = new TypeUtilisateur(idTU,curseur.getString(1));
		}
		return unTU;
	}

}
