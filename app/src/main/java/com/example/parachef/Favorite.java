package com.example.parachef;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import android.widget.ListView;
import java.util.Set;

public class Favorite extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    private ListView favoritesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite);

        sharedPreferences = getSharedPreferences("Favorites", MODE_PRIVATE);


        Intent DasboardIntent = new Intent(Favorite.this, Dashboard.class);
        ImageView dasboardbtn = (ImageView) findViewById(R.id.navhome);

        dasboardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(DasboardIntent);
            }
        });

        Intent ProfileIntent = new Intent(Favorite.this, Profile.class);
        ImageView profilebtn = (ImageView) findViewById(R.id.navprof);

        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ProfileIntent);
            }
        });

        favoritesListView = findViewById(R.id.favoritesListView);

        Set<String> favoriteItems  = loadFavoriteItems();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>(favoriteItems ));
        if (!adapter.isEmpty()){
            favoritesListView.setVisibility(View.VISIBLE);
            favoritesListView.setAdapter(adapter);
        }else {
            favoritesListView.setVisibility(View.GONE);
        }
    }

    // Metode untuk memuat Set<String> dari SharedPreferences
    private Set<String> loadFavoriteItems() {
        return sharedPreferences.getStringSet("favoriteItems", new HashSet<>()); // Return empty set if no data
    }
}