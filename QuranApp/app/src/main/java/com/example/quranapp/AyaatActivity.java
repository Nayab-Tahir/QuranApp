package com.example.quranapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class AyaatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayaat);

        ListView listView = findViewById(R.id.ayaat_list);
        Intent intent = getIntent();
        int index = intent.getIntExtra("Position", 0);
        QDH qdh = new QDH();
        int start = qdh.getSurahStart(index);
        int end = start + qdh.getSurahVerses(index);


        ArrayList<String> ayat_arraylist = new ArrayList<String>();
        QuranArabicText text = new QuranArabicText();
        String[] arr =  text.QuranArabicText;
        for(int i =start-1; i<end-1; i++){
            ayat_arraylist.add(arr[i]);
        }

        AyaatAdapter adapter = new AyaatAdapter(this, ayat_arraylist);
        listView.setAdapter(adapter);
    }
}