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

public class SurahAdapter extends ArrayAdapter<String> {
    public SurahAdapter(@NonNull Context context, ArrayList<String> surahs) {
        super(context, 0, surahs);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.surah_view, parent, false);

        String surah = getItem(position);
        TextView name = convertView.findViewById(R.id.surah_name);
        name.setText(surah);

        return convertView;
    }
}