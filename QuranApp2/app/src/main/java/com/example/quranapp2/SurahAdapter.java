package com.example.quranapp2;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;

public class SurahAdapter extends ArrayAdapter<tsurahModel> {

    String language;
    Context context;

    public SurahAdapter(@NonNull Context context, @NonNull ArrayList<tsurahModel> surahslist, String language) {
        super(context, 0, surahslist);
        this.language = language;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.surah_view, parent, false);

        tsurahModel surah = getItem(position);
        TextView name = convertView.findViewById(R.id.surah_name_urdu);
        Typeface typeface = ResourcesCompat.getFont( this.context, R.font.jameel_noori_nastaleeq);

        if(this.language.equals("English")){
            name.setTypeface(typeface);
            name.setText("\t" + surah.getSurahID() + "\t\t" + surah.getSurahNameE());
        }
        else if(this.language.equals("Urdu"))
            name.setText("\t" + surah.getSurahID() + "\t\t" + surah.getSurahNameU());

        return convertView;
    }
}
