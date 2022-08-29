package com.example.quranapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.media.MediaParser;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

        NavigationView navigationView;
        DrawerLayout drawerLayout;
        ActionBarDrawerToggle toggle;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            navigationView=findViewById(R.id.nav_view);
            drawerLayout=findViewById(R.id.drawer);
            toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();

            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
                {
                    switch (menuItem.getItemId())
                    {
                        case R.id.nav_book :
                            Intent intent = new Intent(MainActivity.this, AllSurahsView.class);
                            startActivity(intent);
                            break;

                        case R.id.nav_return :
                            Toast.makeText(getApplicationContext(),"Retur is Clicked",Toast.LENGTH_LONG).show();
                            //drawerLayout.closeDrawer(GravityCompat.START);
                            break;

                        case R.id.nav_laptop :
                            Toast.makeText(getApplicationContext(),"Laptop is clicked",Toast.LENGTH_LONG).show();
                            drawerLayout.closeDrawer(GravityCompat.START);
                            break;

                        case R.id.nav_voice :
                            Toast.makeText(getApplicationContext(),"Voice is clicked",Toast.LENGTH_LONG).show();
                            drawerLayout.closeDrawer(GravityCompat.START);
                            break;

                        case R.id.nav_chrome_reader :
                            Toast.makeText(getApplicationContext(),"Chrome Reader is clicked",Toast.LENGTH_LONG).show();
                            drawerLayout.closeDrawer(GravityCompat.START);
                            break;
                    }

                    return true;
                }
            });




        }
    }