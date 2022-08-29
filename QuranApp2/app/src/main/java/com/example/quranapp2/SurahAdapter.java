package com.example.quranapp2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SurahAdapter extends ArrayAdapter<tsurahModel> {

    public SurahAdapter(@NonNull Context context, @NonNull ArrayList<tsurahModel> surahslist) {
        super(context, 0, surahslist);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.surah_view, parent, false);

        tsurahModel surah = getItem(position);
        TextView name = convertView.findViewById(R.id.surah_name_urdu);
        name.setText("\t" + surah.getSurahID() + "\t\t" + surah.getSurahNameU());

        return convertView;
    }
}
