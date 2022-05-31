package com.example.easyfood42.controleur;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.easyfood42.R;
import com.example.easyfood42.modele.user.Client;
import com.example.easyfood42.modele.user.Utilisateur;
import com.example.easyfood42.modele.dao.UtilisateurDAO;

public class MainActivity extends AppCompatActivity {

    private EasyFoodApplication easyFoodApplication;
    private Utilisateur utilConnecte;
    private Button b_connexion;
    private EditText et_mailU;
    private EditText et_passwd;
    private UtilisateurDAO unUtilDAO;
    private TextView et_error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Connexion");

        easyFoodApplication = (EasyFoodApplication) getApplication();
        b_connexion = findViewById(R.id.b_connexion);
        et_mailU = findViewById(R.id.et_mailU);
        et_passwd = findViewById(R.id.et_passwd);
        et_error = findViewById(R.id.et_error);

        unUtilDAO = new UtilisateurDAO(this);

        b_connexion.setOnClickListener(view -> {
//              utilConnecte = unUtilDAO.findByMail("client1@sio.fr");
            utilConnecte = unUtilDAO.findByMail(et_mailU.getText().toString());

            if(utilConnecte == null || !utilConnecte.verifPasswd(et_passwd.getText().toString()) ){
                if(utilConnecte != null) {
                    Log.d("testLog", "MDP : " + (utilConnecte.verifPasswd(et_passwd.getText().toString()) ? "Ok" : "Mauvais") );
                }
                et_error.setText("L'identifiant ou le mot de passe sont incorrectes.");
                utilConnecte = null;
                return;
            }

            et_error.setText("");
            Log.d("testLog","Connecté en tant que : "+utilConnecte.getPrenomU() + utilConnecte.getMailU());
            easyFoodApplication.setConnectedUtilisateur(utilConnecte);

            if(utilConnecte instanceof Client) {
                Intent intent = new Intent(this, ListCommandesActivity.class);
                startActivity(intent);
            }

        });
    }

}