package com.example.easyfood42.modele;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class BdSQLiteOpenHelper extends SQLiteOpenHelper {


    private final String table_type_utilisateur = "CREATE TABLE type_utilisateur ("
            + "idTU INTEGER PRIMARY KEY,"
            + "libelleTU TEXT NOT NULL);";

    private final String table_type_plat = "CREATE TABLE type_plat ("
            + "idTP INTEGER PRIMARY KEY,"
            + "libelleTP TEXT NOT NULL);";

    private final String table_contenu_commande = "CREATE TABLE contenu_commande(" +
            "idC INTEGER," +
            "idP INTEGER," +
            "quantity INTEGER," +
            "PRIMARY KEY (idC, idP)," +
            "FOREIGN KEY (idC) REFERENCES commande(idC)," +
            "FOREIGN KEY (idP) REFERENCES plat(idP)" +
            ");";

    private final String table_resto = "CREATE TABLE resto (" +
            "idR INTEGER PRIMARY KEY," +
            "nomR TEXT," +
            "numAdrR TEXT," +
            "rueAdrR TEXT," +
            "cpR TEXT," +
            "villeR TEXT," +
            "idU TEXT," +
            "FOREIGN KEY (idU) REFERENCES utilisateur(idU)" +
            ");";

    private final String table_commande = "CREATE TABLE commande (" +
            "idC INTEGER PRIMARY KEY AUTOINCREMENT," +
            "dateC BIGINT," +
            "commentaireClientC TEXT," +
            "dateLivrC BIGINT DEFAULT 0," +
            "datePrepC BIGINT DEFAULT 0," +
            "modeReglementC TEXT," +
            "idU INTEGER," +
            "idR INTEGER," +
            "FOREIGN KEY (idU) REFERENCES utilisateur(idU)," +
            "FOREIGN KEY (idR) REFERENCES resto(idR)" +
            ");";

    private final String table_utilisateur = "CREATE TABLE utilisateur ("
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
            + "noteEasyFood INTEGER,"
            + "commentaireEasyFood TEXT,"
            + "commentaireVisible BOOLEAN DEFAULT 0,"
            + "foreign key (idTU) references type_utilisateur(idTU));";

    private final String table_plat = "CREATE TABLE plat (" +
            "idP INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nomP TEXT NOT NULL," +
            "prixFournisseurP FLOAT NOT NULL," +
            "prixClientP FLOAT NOT NULL," +
            "platVisible BOOLEAN DEFAULT 0," +
            "photoP TEXT," +
            "descriptionP TEXT," +
            "idR INTEGER," +
            "idTP INTEGER," +
            "FOREIGN KEY (idR) REFERENCES resto(idR)," +
            "FOREIGN KEY (idTP) REFERENCES type_plat(idTP)" +
            ");";

    //EVALUER(#idR,#mailU, commentaire, commentaireVisible, commentaireModere, respectRecette, esthetiquePlat, cout, qualiteNourriture)
    private final String table_evaluation = "CREATE TABLE evaluation (" +
            "idR INTEGER," +
            "idU INTEGER," +
            "date BIGINT," +
            "commentaire TEXT," +
            "commentaireVisible BOOLEAN DEFAULT 0," +
            "commentaireModere BOOLEAN DEFAULT 0," +
            "respectRecette INTEGER," +
            "esthetiquePlat INTEGER," +
            "cout INTEGER," +
            "qualiteNourriture INTEGER," +
            "PRIMARY KEY (idR, idU)," +
            "FOREIGN KEY (idU) REFERENCES utilisateur(idU)," +
            "FOREIGN KEY (idR) REFERENCES resto(idR)" +
            ");";

    public BdSQLiteOpenHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(table_type_utilisateur);
        db.execSQL(table_utilisateur);
        db.execSQL(table_resto);
        db.execSQL(table_type_plat);
        db.execSQL(table_plat);
        db.execSQL(table_commande);
        db.execSQL(table_contenu_commande);
        db.execSQL(table_evaluation);

        db.execSQL("INSERT INTO type_utilisateur (idTU,libelleTU) VALUES (1,'client');");
        db.execSQL("INSERT INTO type_utilisateur (idTU,libelleTU) VALUES (2,'restaurateur');");
        db.execSQL("INSERT INTO type_utilisateur (idTU,libelleTU) VALUES (3,'moderateur');");
        db.execSQL("INSERT INTO type_utilisateur (idTU,libelleTU) VALUES (4,'administrateur');");


        String mdpChiffre = BdSQLiteOpenHelper.md5("motdepasse");

        db.execSQL("INSERT INTO utilisateur (idU, mailU,passwd,idTU, pseudoU, nomU, prenomU, nomAdrU numAdrU, cpU, villeU) VALUES (1, 'client1@sio.fr','" + mdpChiffre + "',1, 'Client1', 'NomClient', 'PrenomClient', 'Avenue Jean Rostand', '4', '64100', 'Bayonne' );");
        db.execSQL("INSERT INTO utilisateur (idU,mailU,passwd,idTU, pseudoU, nomU, prenomU, nomAdrU numAdrU, cpU, villeU) VALUES (2, 'restaurateur1@sio.fr','" + mdpChiffre + "',2, 'Restaurateur1', 'NomRestaurateur', 'PrenomRestaurateur', 'Avenue Jean Rostand', '4', '64100', 'Bayonne' );");
        db.execSQL("INSERT INTO utilisateur (idU,mailU,passwd,idTU, pseudoU, nomU, prenomU, nomAdrU numAdrU, cpU, villeU) VALUES (3, 'moderateur1@sio.fr','" + mdpChiffre + "',3, 'Moderateur1', 'NomModerateur', 'PrenomModerateur', 'Avenue Jean Rostand', '4', '64100', 'Bayonne' );");

        db.execSQL("INSERT INTO type_plat (idTP, libelleTP) VALUES (1,'viande rouge');");
        db.execSQL("INSERT INTO type_plat (idTP, libelleTP) VALUES (2,'burger');");
        db.execSQL("INSERT INTO type_plat (idTP, libelleTP) VALUES (3,'pizza');");
        db.execSQL("INSERT INTO type_plat (idTP, libelleTP) VALUES (4,'pates');");

        db.execSQL("INSERT INTO resto (idR, nomR, numAdrR, rueAdrR, cpR, villeR, idU) VALUES (1,'Kalostrape','22','rue Marengo','64100','Bayonne',2);");
        db.execSQL("INSERT INTO resto (idR, nomR, numAdrR, rueAdrR, cpR, villeR, idU) VALUES (2,'Pépé Joe','50','avenue Louis De Foix','64100','Bayonne',2);");

        db.execSQL("INSERT INTO plat (idP, nomP, prixFournisseurP, prixClientP, platVisible, photoP, descriptionP, idR, idTP) values(1, 'Kalos burger - frites', 7.5, 14.0, 1,'burger.jpg','Un délicieux burger !',1,1);");
        db.execSQL("INSERT INTO plat (idP, nomP, prixFournisseurP, prixClientP, platVisible, photoP, descriptionP, idR, idTP) values(2, 'Grand pepe', 5.3, 11.90, 1,'burger.jpg','Pain aux sésames, steak black Angus 200G, triple cheddar, bacon, oignons frits, tomate, iceberg, sauce pépé maison.',2,1);");
        db.execSQL("INSERT INTO plat (idP, nomP, prixFournisseurP, prixClientP, platVisible, photoP, descriptionP, idR, idTP) values(3, 'Pizza Margherita LG', 4.8, 12.90, 1,'burger.jpg','Pizza Tomate, double mozzarella.',2, 3);");

        String insertCommandeSql = "INSERT INTO commande " +
                "(idC, dateC, commentaireClientC, datePrepC, dateLivrC, modeReglementC, idU, idR) " +
                "VALUES(%s, %s, '%s', %s, %s, '%s', %s, %s)";

        db.execSQL(String.format(insertCommandeSql,
               1, System.currentTimeMillis() - 86400000L, "Les steaks saignant svp", System.currentTimeMillis() - 81200000L, System.currentTimeMillis() - 83200000L, "PayPal", 1, 1
        ));

        db.execSQL(String.format(insertCommandeSql,
                2, System.currentTimeMillis() - 86400000L, "", System.currentTimeMillis() - 81200000L, 0L, "CB", 1, 2
        ));

        db.execSQL("INSERT INTO contenu_commande (idC, idP, quantity) VALUES (1, 1, 3)");
        db.execSQL("INSERT INTO contenu_commande (idC, idP, quantity) VALUES (2, 2, 3)");
        db.execSQL("INSERT INTO contenu_commande (idC, idP, quantity) VALUES (2, 3, 1)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

    public static String md5(final String s) {
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
