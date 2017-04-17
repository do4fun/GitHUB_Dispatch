package com.publiciteweb.mobile.github;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

/**
 * Created by 201696620 on 2017-03-17.
 */
public class Activity_Edit extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbarGestion;
    private Button btnModifier;
    private Button btnAddition;
    private Button btnAnnulerModifier;
    private EditText txtEditNom;
    private EditText txtEditPrenom;
    private EditText txtEditCourriel;
    private EditText txtEditPassword;

    private String action;
    private HashMap<String,String> unDriver;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        readContenuApp();
    }

    protected void readContenuApp() {
        toolbarGestion = (Toolbar)findViewById(R.id.toolbarGestion);
        toolbarGestion.setNavigationIcon(R.drawable.logo_old);
        setSupportActionBar(toolbarGestion);
        getSupportActionBar().setTitle("GitHub Dispatch");

        btnModifier = (Button) findViewById(R.id.btnModifier);
        btnAddition = (Button) findViewById(R.id.btnAddition);
        btnAnnulerModifier = (Button) findViewById(R.id.btnAnnulerModifier);
        btnAnnulerModifier.setOnClickListener(this);

        txtEditNom = (EditText)findViewById(R.id.txtEditNom);
        txtEditPrenom = (EditText)findViewById(R.id.txtEditPrenom);
        txtEditCourriel = (EditText)findViewById(R.id.txtEditCourriel);
        txtEditPassword = (EditText)findViewById(R.id.txtEditPassword);

        unDriver = new HashMap<>();
        //difinition d'action: edit ou modifier des données driver
        action = getIntent().getStringExtra(TransferData.ACTION);
        if(action.equals("edit")) {
            //changement le bouton Ajouter par Modifier
            btnAddition.setVisibility(View.GONE);
            btnModifier.setVisibility(View.VISIBLE);
            btnModifier.setOnClickListener(this);

            unDriver = (HashMap<String, String>) getIntent().getSerializableExtra(TransferData.REPONSE);
            txtEditNom.setText(unDriver.get("nom"));
            txtEditPrenom.setText(unDriver.get("prenom"));
            txtEditCourriel.setText(unDriver.get("courriel"));
            txtEditPassword.setText(unDriver.get("password"));
        }else {
            //changement le bouton Modifier par Ajouter
            btnModifier.setVisibility(View.GONE);
            btnAddition.setVisibility(View.VISIBLE);
            btnAddition.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAddition:
                unDriver.put("nom", txtEditNom.getText().toString().trim());
                unDriver.put("prenom", txtEditPrenom.getText().toString().trim());
                unDriver.put("courriel", txtEditCourriel.getText().toString().trim());
                unDriver.put("password", txtEditPassword.getText().toString().trim());
                //TransferData.executionRequeteVolley("ajouter", getApplicationContext(), null, null, unDriver);
                Activity_Edit.this.finish();
                break;
            case R.id.btnModifier:
                unDriver.put("nom", txtEditNom.getText().toString().trim());
                unDriver.put("prenom", txtEditPrenom.getText().toString().trim());
                unDriver.put("courriel", txtEditCourriel.getText().toString().trim());
                unDriver.put("password", txtEditPassword.getText().toString().trim());
                //TransferData.executionRequeteVolley("modifier", getApplicationContext(), null, null, unDriver);
                Activity_Edit.this.finish();
                break;
            case R.id.btnAnnulerModifier:
                Activity_Edit.this.finish();
                break;
            default:
                break;
        }
    }
}
