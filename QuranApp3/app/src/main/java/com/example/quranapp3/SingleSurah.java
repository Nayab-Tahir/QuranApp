package com.example.quranapp3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SingleSurah extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_surah);

        Intent intent = getIntent();
        int surahID = intent.getIntExtra("surahID",-1);
        String language = intent.getStringExtra("language");
        RecyclerView recyclerView = findViewById(R.id.single_surah_recyclerview);
        TextView text = findViewById(R.id.surahName);

        Log.e("no", surahID + "");

        ArrayList<tayahModel> list = new ArrayList<tayahModel>();
        DBHelper db = new DBHelper(this);
        list = db.getAllAyahs(surahID);
        String[] surahName =  db.getSurahName(surahID);

        if(language.equals("English"))
            text.setText(surahName[0]);
        else if(language.equals("Urdu"))
            text.setText(surahName[1]);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAyahAdapter ayahAdapter = new RecyclerAyahAdapter(list, language);
        recyclerView.setAdapter(ayahAdapter);
    }
}