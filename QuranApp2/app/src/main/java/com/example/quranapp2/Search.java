package com.example.quranapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        TextView surahNo = findViewById(R.id.surah_no);
        TextView ayahNo = findViewById(R.id.ayah_no);
        Button searchBtn = findViewById(R.id.search_btn);
        TextView result_arabic = findViewById(R.id.result_arabic);
        TextView result_trans = findViewById(R.id.result_trans);

        Intent intent = getIntent();
        String language = intent.getStringExtra("language");

        DBHelper db = new DBHelper(this);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int surah_no = Integer.parseInt(surahNo.getText().toString());
                int ayah_no = Integer.parseInt(ayahNo.getText().toString());
                tayahModel ayah = db.searchAyah(surah_no, ayah_no);
                if(ayah != null){
                    result_arabic.setText(ayah.getArabicText());
                    if(language.equals("English"))
                        result_trans.setText(ayah.getTranslationE());
                    else
                        result_trans.setText(ayah.getTranslationU());
                }
                else{
                    result_arabic.setText("Invalid Inputs");
                    result_trans.setText("");
                }
            }
        });

    }
}