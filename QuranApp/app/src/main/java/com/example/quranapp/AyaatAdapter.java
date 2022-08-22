package com.example.quranapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AyaatAdapter extends ArrayAdapter<String> {
    public AyaatAdapter(@NonNull Context context, ArrayList<String> ayaat) {
        super(context, 0, ayaat);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.ayat_view, parent, false);

        String ayat = getItem(position);
        TextView name = convertView.findViewById(R.id.ayat);
        name.setText(ayat);

        return convertView;
    }
}