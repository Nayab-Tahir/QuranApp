package com.example.quranapp3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
        RecyclerView recyclerView = findViewById(R.id.single_parah_recyclerview);

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
                RecyclerAyahAdapter ayahAdapter = new RecyclerAyahAdapter(parah, language);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(ayahAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}