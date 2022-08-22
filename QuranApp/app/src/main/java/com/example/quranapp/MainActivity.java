package com.example.quranapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView surahList = findViewById(R.id.surah_list);

        ArrayList<String> suraharrlist = new ArrayList<String>();
        QDH qdh = new QDH();
        String[] surahnames = qdh.urduSurahNames;
        for(int i=0; i<surahnames.length; i++){
            suraharrlist.add(surahnames[i]);
        }

        SurahAdapter surahAdapter = new SurahAdapter(this, suraharrlist);
        surahList.setAdapter(surahAdapter);


        surahList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, AyaatActivity.class);
                intent.putExtra("Position", position);
                startActivity(intent);
            }
        });

    }
}