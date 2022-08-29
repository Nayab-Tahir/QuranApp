package com.example.quranapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class SingleSurah extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_surah);

        Intent intent = getIntent();
        int surahID = intent.getIntExtra("surahID",-1);
        ListView listView = findViewById(R.id.single_surah_listview);

        ArrayList<tayahModel> list = new ArrayList<tayahModel>();
        DBHelper db = new DBHelper(this);
        list = db.getAllAyahs(surahID);

        AyahAdapter ayahAdapter = new AyahAdapter(this, list);
        listView.setAdapter(ayahAdapter);
    }
}