package com.example.quranapp3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAyahAdapter extends RecyclerView.Adapter<RecyclerAyahAdapter.MyVH> {

    ArrayList<tayahModel> ayahlist;
    String language;

    public RecyclerAyahAdapter(ArrayList<tayahModel> ayahslist, String language) {
        this.language = language;
        this.ayahlist = ayahslist;
    }

    @NonNull
    @Override
    public RecyclerAyahAdapter.MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.complete_surah_vieew, parent, false);
        return new RecyclerAyahAdapter.MyVH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAyahAdapter.MyVH holder, int position) {
        holder.data = ayahlist.get(position);
        holder.ayah_arabic.setText(holder.data.getArabicText());
        if(language.equals("Urdu"))
            holder.ayah_trans.setText(holder.data.getTranslationU());
        else
            holder.ayah_trans.setText(holder.data.getTranslationE());
    }

    @Override
    public int getItemCount() {
        return ayahlist.size();
    }

    public class MyVH extends RecyclerView.ViewHolder{
        TextView ayah_arabic;
        TextView ayah_trans;
        tayahModel data;
        public MyVH(@NonNull View itemView) {
            super(itemView);
            ayah_arabic = itemView.findViewById(R.id.surah_arabic_text);
            ayah_trans = itemView.findViewById(R.id.surah_ayah_translation);
        }
    }

}
