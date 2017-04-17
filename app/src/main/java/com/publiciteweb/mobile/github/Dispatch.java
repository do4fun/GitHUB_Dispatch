package com.publiciteweb.mobile.github;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

/**
 * Created by sun on 17-04-16.
 */

public class Dispatch extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbarGestion;
    private Button btnConnexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion);
        readContenApp();
    }

    protected void readContenApp(){
        toolbarGestion = (Toolbar)findViewById(R.id.toolbarGestion);
        toolbarGestion.setNavigationIcon(R.drawable.logo_old);
        setSupportActionBar(toolbarGestion);
        getSupportActionBar().setTitle("GitHub Driver");

        btnConnexion = (Button)findViewById(R.id.btn_login);
        btnConnexion.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //connexion. Ouvrir l'activiti avec la liste des drivers Activity_ListeDriver
            case R.id.btn_login:
                Intent intentDriver = new Intent(Dispatch.this, Activity_ListeDriver.class);
                startActivity(intentDriver);
                break;
            default:
                break;
        }
    }
}
