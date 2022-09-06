package com.example.quranapp3;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerSurahAdapter extends RecyclerView.Adapter<RecyclerSurahAdapter.MyVH> {

    ArrayList<tsurahModel> surahslist;
    String language;
    private OnItemClickListener listener;

    public RecyclerSurahAdapter(ArrayList<tsurahModel> surahslist, String language, OnItemClickListener listener) {
        this.surahslist = surahslist;
        this.language = language;
        this.listener = listener;
    }
    //    String language;
//    Context context;
//
//    public SurahAdapter(@NonNull Context context, @NonNull ArrayList<tsurahModel> surahslist, String language) {
//        super(context, 0, surahslist);
//        this.language = language;
//        this.context = context;
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        LayoutInflater inflater = LayoutInflater.from(getContext());
//        convertView = inflater.inflate(R.layout.surah_view, parent, false);
//
//        tsurahModel surah = getItem(position);
//        TextView name = convertView.findViewById(R.id.surah_name_urdu);
//        Typeface typeface = ResourcesCompat.getFont( this.context, R.font.jameel_noori_nastaleeq);
//
//        if(this.language.equals("English")){
//            name.setTypeface(typeface);
//            name.setText("\t" + surah.getSurahID() + "\t\t" + surah.getSurahNameE());
//        }
//        else if(this.language.equals("Urdu"))
//            name.setText("\t" + surah.getSurahID() + "\t\t" + surah.getSurahNameU());
//
//        return convertView;
//    }

    @NonNull
    @Override
    public RecyclerSurahAdapter.MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.surah_view, parent, false);
        return new MyVH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerSurahAdapter.MyVH holder, int position) {
        holder.data = surahslist.get(position);
        if(language.equals("Urdu"))
            holder.surah_name.setText("\t" + holder.data.getSurahID() + "\t\t" + holder.data.getSurahNameU());
        else
            holder.surah_name.setText("\t" + holder.data.getSurahID() + "\t\t" + holder.data.getSurahNameE());
        holder.surah_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(holder.data);
            }
        });
    }

    @Override
    public int getItemCount() {
        return surahslist.size();
    }

    public class MyVH extends RecyclerView.ViewHolder{
        TextView surah_name;
        tsurahModel data;
        public MyVH(@NonNull View itemView) {
            super(itemView);
            surah_name = itemView.findViewById(R.id.surah_name_urdu);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(tsurahModel surahModel);

    }
}
