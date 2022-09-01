package com.example.quranapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AllSurahsView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_surahs_view);

        ListView listView = findViewById(R.id.all_surahs_listview);
        String language = getIntent().getStringExtra("language");
        DBHelper db = new DBHelper(this);
        try{
            db.CheckDatabase();
        }catch (Exception e){}

        try {
            db.OpenDatabase();
        }catch (Exception e){}

        ArrayList<tsurahModel> allSurahs = db.getAllSurahs();
        SurahAdapter surahAdapter = new SurahAdapter(AllSurahsView.this, allSurahs, language);
        listView.setAdapter(surahAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AllSurahsView.this, SingleSurah.class);
                intent.putExtra("surahID", position+1);
                intent.putExtra("language", language);
                startActivity(intent);
            }
        });
    }
}