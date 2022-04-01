package com.example.easyfood42.modele;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.easyfood42.controleur.Administrateur;
import com.example.easyfood42.controleur.Client;
import com.example.easyfood42.controleur.Moderateur;
import com.example.easyfood42.controleur.Restaurateur;
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
					curseur.getLong(9)
			);
		}
		return getTypedUser(unUtil);
	}

	private Client getClientByUtilisateur(Utilisateur utilisateur) {
		String sql = "select noteEasyFood, commentaireEasyFood, commentaireVisible from utilisateur where idU='"+utilisateur.getIdU()+"';";
		Cursor curseur;
		curseur = accesBD.getReadableDatabase().rawQuery(sql,null);
		if (curseur.getCount() > 0) {
			curseur.moveToFirst();
			int noteEasyFood = curseur.getInt(0);
			String commentaireEasyFood = curseur.getString(2);
			boolean commentaireVisible = curseur.getInt(3) != 0;
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
