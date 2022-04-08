package com.example.easyfood42.controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.easyfood42.R;
import com.example.easyfood42.modele.BdSQLiteOpenHelper;
import com.example.easyfood42.modele.TypeUtilisateurDAO;
import com.example.easyfood42.modele.UtilisateurDAO;

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

        b_connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                utilConnecte = unUtilDAO.getUtilisateurByMail(et_mailU.getText().toString());
                Log.d("testLog","mdp : "+utilConnecte.getPrenomU() + utilConnecte.getMailU());
                if(utilConnecte != null){
                    Log.d("testLog","utilisateur pas nul");
                } else{
                    Log.d("testLog","utilisateur nul");
                }
                if(utilConnecte.verifPasswd(et_passwd.getText().toString())){
                    Log.d("testLog","passwork ok");
                } else{
                    Log.d("testLog","password non ok"+BdSQLiteOpenHelper.md5(et_passwd.getText().toString()));
                }
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
            }
        });
    }

}