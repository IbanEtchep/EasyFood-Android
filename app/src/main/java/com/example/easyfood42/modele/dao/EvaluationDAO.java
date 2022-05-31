package com.example.easyfood42.modele.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.easyfood42.modele.Evaluation;
import com.example.easyfood42.util.DateUtils;

public class EvaluationDAO extends DAO {

    /*
        evaluation (#_idR_, #_idU_, date, commentaire, commentaireVisible, commentaireModere,
                    respectRecette, esthetiquePlat, cout, quanliteNourriture)
     */

    public EvaluationDAO(Context context) {
        super(context);
    }

    @Nullable
    public Evaluation findByUserAndResto(long idU, long idR) {
        Evaluation uneEval = null;

        try (Cursor cursor = accesBD.getReadableDatabase().rawQuery("SELECT * FROM evaluation WHERE idU=? AND idR=?;",
                new String[]{idU + "", idR + ""})) {
            if (cursor.moveToFirst()) {
                uneEval = getEvaluationFromCursor(cursor);
            }
        }

        return uneEval;
    }

    private Evaluation getEvaluationFromCursor(Cursor cursor) {
        return new Evaluation(
                cursor.getLong(0),
                cursor.getLong(1),
                DateUtils.getDateFromTimeMillis(cursor.getLong(2)),
                cursor.getString(3),
                cursor.getInt(4) == 1,
                cursor.getInt(5) == 1,
                cursor.getInt(6),
                cursor.getInt(7),
                cursor.getInt(8),
                cursor.getInt(9)
        );
    }



    public void addEvaluation(Evaluation evaluation) {
        if(findByUserAndResto(evaluation.getIdU(), evaluation.getIdR()) == null) {
            String insertSql = "INSERT INTO evaluation (idR, idU, commentaire, commentaireVisible, " +
                    "commentaireModere, respectRecette, esthetiquePlat, cout, qualiteNourriture, date) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

            SQLiteDatabase db = accesBD.getWritableDatabase();
            db.execSQL(insertSql, new String[]{
                    evaluation.getIdR() +"",
                    evaluation.getIdU()+"",
                    evaluation.getCommentaire(),
                    evaluation.isCommentaireVisible()+"",
                    evaluation.isCommentaireModere()+"",
                    evaluation.getRespectRecette()+"",
                    evaluation.getEstetiquePlat()+"",
                    evaluation.getCout()+"",
                    evaluation.getQualiteNourriture()+"",
                    evaluation.getDate().getTime()+""
            });
        } else {
          updateEvaluation(evaluation);
        }
    }

    public void updateEvaluation(Evaluation evaluation) {
        String insertSql = "UPDATE evaluation SET " +
                "commentaire = ?, " +
                "commentaireVisible = ?, " +
                "commentaireModere = ?, " +
                "respectRecette = ?, " +
                "esthetiquePlat = ?, " +
                "cout = ?, " +
                "qualiteNourriture = ?, " +
                "date = ?" +
                "WHERE idU=? AND idR=?";

        SQLiteDatabase db = accesBD.getWritableDatabase();
        db.execSQL(insertSql, new String[]{
                evaluation.getCommentaire(),
                evaluation.isCommentaireVisible()+"",
                evaluation.isCommentaireModere()+"",
                evaluation.getRespectRecette()+"",
                evaluation.getEstetiquePlat()+"",
                evaluation.getCout()+"",
                evaluation.getQualiteNourriture()+"",
                evaluation.getDate().getTime()+"",
                evaluation.getIdR() +"",
                evaluation.getIdU()+""
        });
    }

    public void deleteEvaluation(Evaluation evaluation) {
        String sql = "DELETE FROM evaluation WHERE idR=? AND idU=?";
        SQLiteDatabase db = accesBD.getWritableDatabase();
        db.execSQL(sql, new String[]{
                evaluation.getIdR() +"",
                evaluation.getIdU()+""
        });
    }


}
