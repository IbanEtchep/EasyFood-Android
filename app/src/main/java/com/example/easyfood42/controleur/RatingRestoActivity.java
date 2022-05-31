package com.example.easyfood42.controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.easyfood42.R;
import com.example.easyfood42.modele.Evaluation;
import com.example.easyfood42.modele.Resto;
import com.example.easyfood42.modele.dao.CommandeDAO;
import com.example.easyfood42.modele.dao.EvaluationDAO;
import com.example.easyfood42.modele.dao.RestoDAO;

import java.util.Date;

public class RatingRestoActivity extends AppCompatActivity {

    private EasyFoodApplication easyFoodApplication;
    private Evaluation evaluation;
    private Resto resto;

    private RatingBar ratingRecipe;
    private RatingBar ratingCost;
    private RatingBar ratingEsthetic;
    private RatingBar ratingQuality;
    private EditText etComment;
    private Button btnSaveRating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_resto);
        easyFoodApplication = (EasyFoodApplication) getApplication();

        EvaluationDAO evaluationDAO = new EvaluationDAO(this);
        RestoDAO restoDAO = new RestoDAO(this);
        Bundle bundle = this.getIntent().getExtras();
        long idR = bundle.getLong("idR");
        long idU = easyFoodApplication.getConnectedUtilisateur().getIdU();

        Resto resto = restoDAO.findById(idR);
        setTitle("Avis restaurant : " + resto.getNomR());

        evaluation = evaluationDAO.findByUserAndResto(idU, idR);
        if (evaluation == null) {
            evaluation = new Evaluation(
                    idR, idU, new Date(System.currentTimeMillis()), "", false, false,
                    0, 0, 0, 0
            );
        }

        ratingCost = findViewById(R.id.ratingCost);
        ratingCost.setRating(evaluation.getCout());

        ratingRecipe = findViewById(R.id.ratingRecipe);
        ratingRecipe.setRating(evaluation.getRespectRecette());

        ratingEsthetic = findViewById(R.id.ratingEsthetic);
        ratingEsthetic.setRating(evaluation.getEstetiquePlat());

        ratingQuality = findViewById(R.id.ratingQuality);
        ratingQuality.setRating(evaluation.getQualiteNourriture());

        etComment = findViewById(R.id.etComment);
        etComment.setText(evaluation.getCommentaire());

        btnSaveRating = findViewById(R.id.btnSaveRating);

        btnSaveRating.setOnClickListener(view -> {
            evaluation.setDate(new Date(System.currentTimeMillis()));
            evaluation.setCommentaire(etComment.getText().toString());
            evaluation.setCommentaireModere(false);
            evaluation.setCommentaireVisible(false);
            evaluation.setRespectRecette((int) ratingRecipe.getRating());
            evaluation.setCout((int) ratingCost.getRating());
            evaluation.setQualiteNourriture((int) ratingQuality.getRating());
            evaluation.setEstetiquePlat((int) ratingEsthetic.getRating());

            evaluationDAO.addEvaluation(evaluation);
            finish();
        });
    }
}