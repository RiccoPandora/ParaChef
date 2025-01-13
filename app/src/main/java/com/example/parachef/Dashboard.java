package com.example.parachef;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        Intent makananIntent = new Intent(Dashboard.this, Makanan.class);
        ImageView mkn = (ImageView) findViewById(R.id.cmakanan);
        Intent minumanIntent = new Intent(Dashboard.this, Minuman.class);
        ImageView mnm = (ImageView) findViewById(R.id.cminuman);
        Intent boluIntent = new Intent(Dashboard.this, BoluKue.class);
        ImageView blu = (ImageView) findViewById(R.id.cbolukue);

        mkn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(makananIntent);
            }
        });
        mnm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(minumanIntent);
            }
        });
        blu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(boluIntent);
            }
        });



        Intent FavoritesIntent = new Intent(Dashboard.this, Favorite.class);
        ImageView favbtn = (ImageView) findViewById(R.id.navfav);

        favbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(FavoritesIntent);
            }
        });

        Intent ProfileIntent = new Intent(Dashboard.this, Profile.class);
        ImageView profilebtn = (ImageView) findViewById(R.id.navprof);

        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ProfileIntent);
            }
        });
    }


}