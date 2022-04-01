package com.example.easyfood42;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Utilisateur utilConnecte;
    private Button b_connexion;
    private EditText et_mailU;
    private EditText et_passwd;
    private UtilisateurDAO unUtilDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b_connexion = findViewById(R.id.b_connexion);
        et_mailU = findViewById(R.id.et_mailU);
        et_passwd = findViewById(R.id.et_passwd);

        unUtilDAO = new UtilisateurDAO(this);

        b_connexion.setOnClickListener(view -> {
            utilConnecte = unUtilDAO.getUtilisateurByMail(et_mailU.getText().toString());

            if (utilConnecte != null && utilConnecte.verifPasswd(et_passwd.getText().toString())){
                Log.d("testLog","utilisateur reconnu. L'utilisateur connecté est stocké dans utilConnecte");
                TypeUtilisateurDAO unTUDAO = new TypeUtilisateurDAO(MainActivity.this);
                TypeUtilisateur leTypeUtil = unTUDAO.getTypeUtilisateurById(utilConnecte.getIdTU());
                Log.d("testLog",leTypeUtil.getLibelleTU());
            }
            else{
                Log.d("testLog","utilisateur non reconnu");
                utilConnecte=null;
            }
        });
    }

}