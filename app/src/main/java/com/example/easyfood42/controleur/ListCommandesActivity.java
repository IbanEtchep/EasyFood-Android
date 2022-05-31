package com.example.easyfood42.controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.BuddhistCalendar;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.easyfood42.R;
import com.example.easyfood42.adapter.CommandeAdapter;
import com.example.easyfood42.modele.Commande;
import com.example.easyfood42.modele.dao.CommandeDAO;
import com.example.easyfood42.modele.dao.UtilisateurDAO;
import com.example.easyfood42.modele.user.Utilisateur;

import java.util.List;

public class ListCommandesActivity extends AppCompatActivity {

    private List<Commande> commandes;

    private EasyFoodApplication easyFoodApplication;
    private ListView lvCommandes;
    ArrayAdapter<Commande> commandeArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_commandes);
        setTitle("Vos commandes :");
        easyFoodApplication = (EasyFoodApplication) getApplication();

        UtilisateurDAO utilisateurDAO = new UtilisateurDAO(this);
        CommandeDAO commandeDAO = new CommandeDAO(this);

        commandes = commandeDAO.findByUser(easyFoodApplication.getConnectedUtilisateur());

        lvCommandes = findViewById(R.id.lvCommandes);
        commandeArrayAdapter = new CommandeAdapter(this, commandes);
        lvCommandes.setAdapter(commandeArrayAdapter);

        lvCommandes.setOnItemClickListener((adapterView, view, position, id) -> {
            Commande clickedCommade = commandeArrayAdapter.getItem(position);

            Intent intent = new Intent(this, ViewCommadeActivity.class);
            Bundle bundle = new Bundle();
            bundle.putLong("commandeId", clickedCommade.getIdC());
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }
}