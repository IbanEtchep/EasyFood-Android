package com.example.easyfood42.modele;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class BdSQLiteOpenHelper extends SQLiteOpenHelper {

	private String table_type_utilisateur="create table type_utilisateur ("
			+ "idTU INTEGER PRIMARY KEY,"
			+ "libelleTU TEXT NOT NULL);";

	private String table_utilisateur="create table utilisateur ("
			+ "idU INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ "mailU TEXT NOT NULL,"
			+ "pseudoU TEXT,"
			+ "passwd TEXT NOT NULL,"
			+ "nomU TEXT,"
			+ "prenomU TEXT,"
			+ "numAdrU TEXT,"
			+ "nomAdrU TEXT,"
			+ "cpU TEXT,"
			+ "villeU TEXT,"
			+ "idTU INTEGER,"
			+ "foreign key (idTU) references type_utilisateur(idTU));";


	public BdSQLiteOpenHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(table_type_utilisateur);
		db.execSQL(table_utilisateur);

		db.execSQL("insert into type_utilisateur (idTU,libelleTU) values(1,'client');");
		db.execSQL("insert into type_utilisateur (idTU,libelleTU) values(2,'restaurateur');");
		db.execSQL("insert into type_utilisateur (idTU,libelleTU) values(3,'moderateur');");
		db.execSQL("insert into type_utilisateur (idTU,libelleTU) values(4,'administrateur');");


		String mdpChiffre = BdSQLiteOpenHelper.md5("motdepasse");

		db.execSQL("insert into utilisateur (mailU,passwd,idTU) values('client1@sio.fr','"+mdpChiffre+"',1);");
		db.execSQL("insert into utilisateur (mailU,passwd,idTU) values('restaurateur1@sio.fr','"+mdpChiffre+"',2);");
		db.execSQL("insert into utilisateur (mailU,passwd,idTU) values('moderateur1@sio.fr','"+mdpChiffre+"',3);");


	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	public static final String md5(final String s) {
		final String MD5 = "MD5";
		try {
			// Create MD5 Hash
			MessageDigest digest = MessageDigest
					.getInstance(MD5);
			digest.update(s.getBytes());
			byte messageDigest[] = digest.digest();

			// Create Hex String
			StringBuilder hexString = new StringBuilder();
			for (byte aMessageDigest : messageDigest) {
				String h = Integer.toHexString(0xFF & aMessageDigest);
				while (h.length() < 2)
					h = "0" + h;
				hexString.append(h);
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

}
