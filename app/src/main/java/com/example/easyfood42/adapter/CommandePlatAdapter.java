package com.example.easyfood42.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.easyfood42.R;
import com.example.easyfood42.modele.Commande;
import com.example.easyfood42.modele.Plat;
import com.example.easyfood42.util.DateUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommandePlatAdapter extends ArrayAdapter<Plat> {

    private final Map<Plat, Integer> contenu;

    public CommandePlatAdapter(@NonNull Context context, @NonNull Map<Plat, Integer> contenu) {
        super(context, 0, new ArrayList<>(contenu.keySet()));
        this.contenu = contenu;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Plat plat = getItem(position);
        int quantity = contenu.get(plat);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_commande_plat, parent, false);
        }

        TextView tvQuantity = convertView.findViewById(R.id.tvQuantity);
        tvQuantity.setText(""+quantity);

        TextView tvNomPlat = convertView.findViewById(R.id.tvNomPlat);
        tvNomPlat.setText(plat.getNomP());

        TextView tvPrixPlat = convertView.findViewById(R.id.tvPrixPlat);
        tvPrixPlat.setText(plat.getPrixClientP()+"€/u");

        return convertView;
    }
}
