package com.example.quranapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class SingleParah extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_parah);

        Intent intent = getIntent();
        String language = intent.getStringExtra("language");

        Spinner spinner = findViewById(R.id.spinner);
        ListView listView = findViewById(R.id.single_para_listview);

        List<String> paraNo = new ArrayList<>();
        for(int i=1; i<=30; i++){
            paraNo.add(""+ i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, paraNo);
        spinner.setAdapter(adapter);

        DBHelper db = new DBHelper(this);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<tayahModel> parah = db.getParah(position+1);
                AyahAdapter ayahAdapter = new AyahAdapter(getApplicationContext(), parah, language);
                listView.setAdapter(ayahAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}