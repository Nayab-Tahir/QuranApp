package com.example.quranapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

        NavigationView navigationView;
        DrawerLayout drawerLayout;
        ActionBarDrawerToggle toggle;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            Switch switch_language = findViewById(R.id.language);
            navigationView=findViewById(R.id.nav_view);
            drawerLayout=findViewById(R.id.drawer);
            toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();

            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
                {
                    String language;
                    if(switch_language.isChecked())
                        language = "English";
                    else
                        language = "Urdu";

                    switch (menuItem.getItemId())
                    {
                        case R.id.nav_surahs:
                            Intent intent = new Intent(MainActivity.this, AllSurahsView.class);
                            intent.putExtra("language", language);
                            startActivity(intent);
                            break;

                        case R.id.nav_parahs:
                            Toast.makeText(getApplicationContext(),"Retur is Clicked",Toast.LENGTH_LONG).show();
                            //drawerLayout.closeDrawer(GravityCompat.START);
                            break;

                        case R.id.nav_voice :
                            Toast.makeText(getApplicationContext(),"Voice is clicked",Toast.LENGTH_LONG).show();
                            drawerLayout.closeDrawer(GravityCompat.START);
                            break;

                        case R.id.nav_lang_urdu:
                            switch_language.setChecked(false);
                            language = "Urdu";
                            Toast.makeText(getApplicationContext(),"Urdu Language is Selected",Toast.LENGTH_LONG).show();
                            break;
                        case R.id.nav_lang_eng:
                            switch_language.setChecked(true);
                            language = "English";
                            Toast.makeText(getApplicationContext(),"English Language is Selected",Toast.LENGTH_LONG).show();
//                            drawerLayout.closeDrawer(GravityCompat.START);
                            break;
                    }

                    return true;
                }
            });




        }
    }