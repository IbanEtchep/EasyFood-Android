package com.example.easyfood42.controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.easyfood42.R;
import com.example.easyfood42.adapter.CommandeAdapter;
import com.example.easyfood42.adapter.CommandePlatAdapter;
import com.example.easyfood42.modele.Commande;
import com.example.easyfood42.modele.Evaluation;
import com.example.easyfood42.modele.Plat;
import com.example.easyfood42.modele.dao.CommandeDAO;
import com.example.easyfood42.modele.dao.EvaluationDAO;
import com.example.easyfood42.modele.dao.UtilisateurDAO;
import com.example.easyfood42.util.DateUtils;

public class ViewCommadeActivity extends AppCompatActivity {

    private Commande commande;
    private EasyFoodApplication easyFoodApplication;

    private TextView tvNomRestoTitle;
    private TextView tvDateCommade;
    private TextView tvStatus;
    private TextView tvTotalPrice;
    private TextView tvModeReglement;
    private ListView lvPlats;
    private Button btnAvis;
    private Button btnModifAvis;
    private Button btnSupprAvis;
    private ArrayAdapter<Plat> platArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_commade);
        easyFoodApplication = (EasyFoodApplication) getApplication();

        CommandeDAO commandeDAO = new CommandeDAO(this);

        Bundle bundle = this.getIntent().getExtras();
        commande = commandeDAO.findById(bundle.getLong("commandeId"));
        setTitle("Commande n°" + commande.getIdC());

        tvNomRestoTitle = findViewById(R.id.tvNomRestoTitle);
        tvDateCommade = findViewById(R.id.tvDateCommande);
        tvStatus = findViewById(R.id.tvStatus);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        tvModeReglement = findViewById(R.id.tvModeReglement);
        btnAvis = findViewById(R.id.btnAvis);
        btnModifAvis = findViewById(R.id.btnModifAvis);
        btnSupprAvis = findViewById(R.id.btnSupprAvis);



        tvNomRestoTitle.setText(commande.getResto().getNomR() + " ("+commande.getResto().getVilleR()+")");
        tvTotalPrice.setText("Total : " + commande.getPrixTotal()+"€");
        tvModeReglement.setText("Mode de réglement : " + commande.getModeReglementC());
        tvDateCommade.setText("Créé le " + DateUtils.format(commande.getDateC()));
        tvStatus.setText("Statut : " + commande.getStatut());

        lvPlats = findViewById(R.id.lvPlats);
        platArrayAdapter = new CommandePlatAdapter(this, commande.getContenu());
        lvPlats.setAdapter(platArrayAdapter);

        handleButtons();
    }

    private void handleButtons() {
        EvaluationDAO evaluationDAO = new EvaluationDAO(this);

        if (commande.isCommandeLivree()) {
            Evaluation evaluation = evaluationDAO.findByUserAndResto(commande.getIdU(), commande.getResto().getIdR());

            if (evaluation == null) {

                btnAvis.setVisibility(View.VISIBLE);
                btnModifAvis.setVisibility(View.GONE);
                btnSupprAvis.setVisibility(View.GONE);

                btnAvis.setOnClickListener(view -> evaluerResto());
            } else {

                btnAvis.setVisibility(View.GONE);
                btnModifAvis.setVisibility(View.VISIBLE);
                btnSupprAvis.setVisibility(View.VISIBLE);

                btnModifAvis.setOnClickListener(view -> evaluerResto());
                btnSupprAvis.setOnClickListener(view -> {
                    evaluationDAO.deleteEvaluation(evaluation);
                    handleButtons();
                });
            }
        }
    }

    private void evaluerResto() {
        Intent intent = new Intent(this, RatingRestoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putLong("idR", commande.getResto().getIdR());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handleButtons();
    }
}