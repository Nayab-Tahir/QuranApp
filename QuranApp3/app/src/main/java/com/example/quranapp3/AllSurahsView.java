package com.example.quranapp3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class AllSurahsView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_surahs_view);

        RecyclerView recyclerView = findViewById(R.id.all_surahs_recyclerview);
        String language = getIntent().getStringExtra("language");
        DBHelper db = new DBHelper(this);
        try{
            db.CheckDatabase();
        }catch (Exception e){}

        try {
            db.OpenDatabase();
        }catch (Exception e){}

        ArrayList<tsurahModel> allSurahs = db.getAllSurahs();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerSurahAdapter recyclerSurahAdapter = new RecyclerSurahAdapter(this, allSurahs, language, new RecyclerSurahAdapter.OnItemClickListener(){

            @Override
            public void onItemClick(tsurahModel surahModel) {
                Intent intent = new Intent(AllSurahsView.this, SingleSurah.class);
                intent.putExtra("surahID", surahModel.getSurahID());
                intent.putExtra("language", language);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(recyclerSurahAdapter);
    }
}