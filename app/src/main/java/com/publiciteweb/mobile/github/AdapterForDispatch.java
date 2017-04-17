package com.publiciteweb.mobile.github;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import static android.graphics.Color.rgb;

/**
 * Created by sun on 17-04-14.
 */

public class AdapterForDispatch extends BaseAdapter implements View.OnClickListener{
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<HashMap<String, String>> listeDriverAdapter;

    public AdapterForDispatch(Context context, ArrayList<HashMap<String, String>> listeDriverAdapter) {
        this.context = context;
        this.listeDriverAdapter = listeDriverAdapter;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listeDriverAdapter.size();
    }

    @Override
    public Object getItem(int position) {
        return listeDriverAdapter.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //creation AdapterView avec le nom et le prenom, les images cliquables Modifier et Maps
        convertView = inflater.inflate(R.layout.simple_liste_item, null);

        ImageView imgMap = (ImageView) convertView.findViewById(R.id.imgMap);
        //imgMap.setTag(listeDriverAdapter.get(position).get(""));
        //clique sur l'image du Map, creation de la fenetre MAP
        imgMap.setOnClickListener(this);

        //clique sur l'image Edit, creation de la fenetre de modifiquation
        ImageView imgEdit = (ImageView) convertView.findViewById(R.id.imgEdit);
        HashMap<String, String > cursorTag = new HashMap<>();
        cursorTag.put("_id", listeDriverAdapter.get(position).get("_id"));
        cursorTag.put("nom", listeDriverAdapter.get(position).get("nom"));
        cursorTag.put("prenom", listeDriverAdapter.get(position).get("prenom"));
        cursorTag.put("courriel", listeDriverAdapter.get(position).get("courriel"));
        cursorTag.put("password", listeDriverAdapter.get(position).get("password"));
        imgEdit.setTag(cursorTag);
        imgEdit.setOnClickListener(this);

        TextView txtNomPrenom = (TextView) convertView.findViewById(R.id.txtNomPrenom);
        TextView txtCourriel = (TextView) convertView.findViewById(R.id.txtCourriel);

        //affichage le nom et le prenom de la personne
        txtNomPrenom.setText(listeDriverAdapter.get(position).get("nom") + " "
                + listeDriverAdapter.get(position).get("prenom"));
        txtCourriel.setText(listeDriverAdapter.get(position).get("courriel"));
        notifyDataSetChanged();
        if(position%2 != 0) {
            convertView.setBackgroundColor(rgb(219, 218, 215));
        } else {
            convertView.setBackgroundColor(rgb(231, 230, 228));
        }
        return convertView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgMap:
                Intent intentMaps = new Intent(context, Activity_Maps.class);
                intentMaps.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intentMaps);
                break;
            case R.id.imgEdit:
                Intent intentEdit = new Intent(context, Activity_Edit.class);
                intentEdit.putExtra(TransferData.ACTION,"edit");
                intentEdit.putExtra(TransferData.REPONSE, (Serializable) v.getTag());
                intentEdit.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intentEdit);
                break;
            default:
                break;
        }
    }
}
