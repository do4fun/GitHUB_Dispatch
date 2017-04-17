package com.publiciteweb.mobile.github;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sun on 17-04-16.
 */

public class TransferData {
    protected static final String REPONSE = "com.publiciteweb.mobile.github.REPONSE";
    protected static final String ACTION = "com.publiciteweb.mobile.github.ACTION";
    protected static final String url =
            "http://10.0.2.2:80/Labo3ZinenkoOlena/PHP/controleurContactsJSON.php";


    /*exécution du requete Volley avec parametre:
    * final String action - nom d'action d'execution du requete:
    *       listerParCondition, listerTous, ajouter, modifier
    * final Context context - pour afficher Toast dans l'activity courant
    * ListView pListDrivers - ListView pour afficher la liste des drivers selectionnés
    *       (pour action: listerParCondition, listerTous)
    * final String conditionSelectionnee - la chaine pour trouver nom et prenom dans BD:
    *       SELECT... WHERE nom+prenom LIKE conditionSelectionnee
    *       (pour action: listerParCondition)
    * final HashMap<String, String> unDriver - les données d'un driver
    *       pour mettre comme les parametres POST
    *       (pour action: ajouter, modifier)*/
    public static void executionRequeteVolley(final String action,
                                              final Context context,
                                              ListView pListDrivers,
                                              final String conditionSelectionnee,
                                              final HashMap<String, String> unDriver){
        final ArrayList<HashMap<String, String>> listeDriversAdapter = new ArrayList<>();
        final ListView listDrivers = pListDrivers;

        StringRequest requete = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("RESULTAT ", action + ": " + response);
                            JSONArray jsonResponse = new JSONArray(response);
                            String message = jsonResponse.getJSONObject(0).getString("msgCause");
                            if(message.equals("success")) {
                                switch (action) {
                                    case "listerTous":
                                    case "listerParCondition":
                                        JSONObject unConducteur;
                                        for (int i = 1; i < jsonResponse.length(); i++) {
                                            unConducteur = jsonResponse.getJSONObject(i);
                                            HashMap<String, String> resultat = new HashMap<>();
                                            resultat.put("_id", unConducteur.getString("_id"));
                                            resultat.put("nom", unConducteur.getString("nom"));
                                            resultat.put("prenom", unConducteur.getString("prenom"));
                                            resultat.put("courriel", unDriver.get("courriel"));
                                            resultat.put("password", unDriver.get("password"));
                                            listeDriversAdapter.add(resultat);
                                        }
                                        AdapterForDispatch adapter = new AdapterForDispatch(context, listeDriversAdapter);
                                        listDrivers.setAdapter(adapter);
                                        break;
                                    case "ajouter":
                                        Toast.makeText(context, "Les données bien enregistrées", Toast.LENGTH_SHORT).show();
                                        break;
                                    case "modifier":
                                        Toast.makeText(context, "Les données sont modifiées", Toast.LENGTH_SHORT).show();
                                        break;
                                    default:
                                        break;
                                }
                            } else {
                                Toast.makeText(context, "Problème de " + action, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException ex){
                            ex.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "ERREUR", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parameters = new HashMap<>();
                // Les parametres pour POST
                switch (action) {
                    case "listerTous":
                        parameters.put("action", "listerTous");
                        break;
                    case "listerParCondition":
                        parameters.put("action", "listerParCondition");
                        parameters.put("conditionSelectionnee", conditionSelectionnee);
                        break;
                    case "ajouter":
                        parameters.put("action", "enregistrer");
                        parameters.put("nom", unDriver.get("nom"));
                        parameters.put("prenom", unDriver.get("prenom"));
                        parameters.put("courriel", unDriver.get("courriel"));
                        parameters.put("password", unDriver.get("password"));
                        break;
                    case "modifier":
                        parameters.put("action", "modifier");
                        parameters.put("_id", unDriver.get("_id"));
                        parameters.put("nom", unDriver.get("nom"));
                        parameters.put("prenom", unDriver.get("prenom"));
                        parameters.put("courriel", unDriver.get("courriel"));
                        parameters.put("password", unDriver.get("password"));
                        break;
                   default:
                        break;
                }
                return parameters;
            }
        };
        Volley.newRequestQueue(context).add(requete);
    }
}
