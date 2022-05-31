package com.example.easyfood42.modele.dao;

import android.content.Context;

import com.example.easyfood42.modele.BdSQLiteOpenHelper;

public abstract class DAO {

    private final String base = "BDeasyfood";
    private final int version = 1;
    protected final Context context;
    protected BdSQLiteOpenHelper accesBD;

    public DAO(Context context) {
        this.context = context;
        accesBD = new BdSQLiteOpenHelper(context, base, null, version);
    }
}
