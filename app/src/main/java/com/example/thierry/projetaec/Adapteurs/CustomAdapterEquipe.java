package com.example.thierry.projetaec.Adapteurs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.thierry.projetaec.Objets.Equipe;
import com.example.thierry.projetaec.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathan on 2016-04-13.
 */
public class CustomAdapterEquipe extends BaseAdapter {
    private static List<Equipe> equipeArrayList;

    private LayoutInflater mInflater;

    public CustomAdapterEquipe(Context context, List<Equipe> results) {
        equipeArrayList = results;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return equipeArrayList.size();
    }

    public Object getItem(int position) {
        return equipeArrayList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.row_equipe_layout, null);
            holder = new ViewHolder();
            holder.txtId = (TextView) convertView.findViewById(R.id.txtIdEquipe);
            holder.txtNom = (TextView) convertView.findViewById(R.id.txtNomEquipe);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtId.setText(Integer.toString(equipeArrayList.get(position).getIdEquipe()));
        holder.txtNom.setText(equipeArrayList.get(position).getNomEquipe());

        return convertView;
    }

    static class ViewHolder {
        TextView txtId;
        TextView txtNom;
    }
}

