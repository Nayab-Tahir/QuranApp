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

public class AyahAdapter extends ArrayAdapter<tayahModel> {

    public AyahAdapter(@NonNull Context context, ArrayList<tayahModel> ayahslist) {
        super(context, 0, ayahslist);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.complete_surah_vieew, parent, false);

        tayahModel ayah = getItem(position);
        TextView arabic = convertView.findViewById(R.id.surah_arabic_text);
        TextView translation  = convertView.findViewById(R.id.surah_ayah_translation);

        arabic.setText(ayah.getArabicText());
        translation.setText(ayah.getTranslationU());

        return convertView;
    }

}
