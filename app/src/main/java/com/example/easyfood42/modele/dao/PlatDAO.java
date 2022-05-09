package com.example.easyfood42.modele.dao;

import android.content.Context;

import com.example.easyfood42.modele.Plat;
import com.example.easyfood42.modele.BdSQLiteOpenHelper;

public class PlatDAO {

    private static String base = "BDeasyfood";
    private static int version = 1;
    private BdSQLiteOpenHelper accesBD;

    public PlatDAO(Context ct) {
        accesBD = new BdSQLiteOpenHelper(ct, base, null, version);
    }

    public Plat getPlatById() {

        return null;
    }

}
