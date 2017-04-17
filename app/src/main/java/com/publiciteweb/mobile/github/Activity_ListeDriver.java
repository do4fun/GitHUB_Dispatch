package com.publiciteweb.mobile.github;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.HashMap;

import static android.graphics.Color.rgb;

/**
 * Created by sun on 17-04-14.
 */

public class Activity_ListeDriver extends AppCompatActivity implements SearchView.OnQueryTextListener, View.OnClickListener {
    private Toolbar toolbarGestion;
    private SearchView searchDriver;
    private ListView listeViewDriver;
    private ImageButton btnAddition;

    private ArrayList<HashMap<String, String>> listeDrivers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_drivers);
        readContenApp();
        //affichage la liste de tous drivers
        TransferData.executionRequeteVolley("listerTous", getApplicationContext(),listeViewDriver, null, null);
    }

    //affichage la liste de tous drivers
    @Override
    protected void onResume(){
        super.onResume();
        TransferData.executionRequeteVolley("listerTous", getApplicationContext(),listeViewDriver, null, null);
    }

    protected void readContenApp(){
        toolbarGestion = (Toolbar)findViewById(R.id.toolbarGestion);
        toolbarGestion.setNavigationIcon(R.drawable.logo_old);
        setSupportActionBar(toolbarGestion);
        getSupportActionBar().setTitle("GitHub Dispatch");

        searchDriver = (SearchView)findViewById(R.id.searchDriver);
        searchDriver.setIconified(false);
        searchDriver.setSubmitButtonEnabled(true);
        searchDriver.setOnQueryTextListener(this);

        btnAddition = (ImageButton)findViewById(R.id.btnAddition);
        btnAddition.setOnClickListener(this);

        listeDrivers = new ArrayList<>();

        //pour l'examples
        HashMap<String, String> exemple = new HashMap<>();
        exemple.put("nom", "Shevchenko");
        exemple.put("prenom", "Andrey");
        exemple.put("courriel", "xxx@kkkk.com");
        exemple.put("password", "123");
        listeDrivers.add(exemple);

        exemple = new HashMap<>();
        exemple.put("nom", "Lisova");
        exemple.put("prenom", "Mavka");
        exemple.put("courriel", "xxx@kkkk.com");
        exemple.put("password", "123");
        listeDrivers.add(exemple);

        exemple = new HashMap<>();
        exemple.put("nom", "Zinenko");
        exemple.put("prenom", "Olena");
        exemple.put("courriel", "xxx@kkkk.com");
        exemple.put("password", "123");
        listeDrivers.add(exemple);

        exemple = new HashMap<>();
        exemple.put("nom", "Bolotina");
        exemple.put("prenom", "Olena");
        exemple.put("courriel", "xxx@kkkk.com");
        exemple.put("password", "123");
        listeDrivers.add(exemple);

        exemple = new HashMap<>();
        exemple.put("nom", "Tavares");
        exemple.put("prenom", "Antonio");
        exemple.put("courriel", "xxx@kkkk.com");
        exemple.put("password", "123");
        listeDrivers.add(exemple);

        exemple = new HashMap<>();
        exemple.put("nom", "Benichou");
        exemple.put("prenom", "Gilles");
        exemple.put("courriel", "xxx@kkkk.com");
        exemple.put("password", "123");
        listeDrivers.add(exemple);

        exemple = new HashMap<>();
        exemple.put("nom", "Fabrice");
        exemple.put("prenom", "");
        exemple.put("courriel", "xxx@kkkk.com");
        exemple.put("password", "123");
        listeDrivers.add(exemple);

        listeViewDriver = (ListView)findViewById(R.id.listeViewDriver);
        AdapterForDispatch adapter = new AdapterForDispatch(getApplicationContext(), listeDrivers);
        listeViewDriver.setAdapter(adapter);
    }

    //recherche le driver par nom et prenom
    @Override
    public boolean onQueryTextSubmit(String query) {
        TransferData.executionRequeteVolley("listerParCondition", getApplicationContext(),listeViewDriver, query, null);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    //en cliquent au boutton "Plus" - ajouter le nouveau driver
    @Override
    public void onClick(View view) {
        Intent intentAdd = new Intent(Activity_ListeDriver.this,Activity_Edit.class);
        //le paramètre ACTION pour Activity_Edit
        intentAdd.putExtra(TransferData.ACTION,"addition");
        startActivity(intentAdd);
    }
}
