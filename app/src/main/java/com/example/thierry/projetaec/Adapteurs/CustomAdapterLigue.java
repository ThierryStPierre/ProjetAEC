package com.example.thierry.projetaec.Adapteurs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.thierry.projetaec.Objets.Ligue;
import com.example.thierry.projetaec.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathan on 2016-04-13.
 */
public class CustomAdapterLigue extends BaseAdapter {
    private static List<Ligue> ligueArrayList;

    private LayoutInflater mInflater;

    public CustomAdapterLigue(Context context, List<Ligue> results) {
        ligueArrayList = results;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return ligueArrayList.size();
    }

    public Object getItem(int position) {
        return ligueArrayList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.row_ligue_layout, null);
            holder = new ViewHolder();
            holder.txtId = (TextView) convertView.findViewById(R.id.txtIdLigue);
            holder.txtNom = (TextView) convertView.findViewById(R.id.txtNomLigue);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtId.setText(Integer.toString(ligueArrayList.get(position).getIdLigue()));
        holder.txtNom.setText(ligueArrayList.get(position).getNomLigue());

        return convertView;
    }

    static class ViewHolder {
        TextView txtId;
        TextView txtNom;
    }
}

