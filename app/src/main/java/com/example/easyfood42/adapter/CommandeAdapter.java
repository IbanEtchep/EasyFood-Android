package com.example.easyfood42.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.easyfood42.R;
import com.example.easyfood42.modele.Commande;
import com.example.easyfood42.util.DateUtils;

import java.text.SimpleDateFormat;
import java.util.List;

public class CommandeAdapter extends ArrayAdapter<Commande> {

    public CommandeAdapter(@NonNull Context context, @NonNull List<Commande> commandes) {
        super(context, 0, commandes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Commande commande = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_commande, parent, false);
        }

        TextView tvNomResto = convertView.findViewById(R.id.tvNomResto);
        tvNomResto.setText(commande.getResto().getNomR());

        TextView tvNbPlats = convertView.findViewById(R.id.tvNbPlats);
        tvNbPlats.setText(commande.getNbPlats() + " plats");

        TextView tvPrix = convertView.findViewById(R.id.tvPrix);
        tvPrix.setText(commande.getPrixTotal()+"€");

        TextView tvDateStatus = convertView.findViewById(R.id.tvDateStatus);
        tvDateStatus.setText(DateUtils.format(commande.getDateC()) + " • " + commande.getStatut());

        return convertView;
    }
}
