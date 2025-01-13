package com.example.parachef;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Minuman extends AppCompatActivity {
    private ImageView favoriteIcon1, favoriteIcon2, favoriteIcon3;
    private Set<String> favoriteItems; // Using Set<String> instead of ArrayList
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minuman);

        Intent jusJerukIntent = new Intent(Minuman.this, djusjeruk.class);
        LinearLayout jusJeruk = (LinearLayout) findViewById(R.id.djusjeruk);

        Intent jusAlpukatIntent = new Intent(Minuman.this, djusalpukat.class);
        LinearLayout jusAlpukat = (LinearLayout) findViewById(R.id.djusalpukat);

        Intent bobaIntent = new Intent(Minuman.this, dboba.class);
        LinearLayout boba = (LinearLayout) findViewById(R.id.dboba);

        jusJeruk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(jusJerukIntent);
            }
        });

        jusAlpukat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(jusAlpukatIntent);
            }
        });

        boba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(bobaIntent);
            }
        });

        Intent DasboardIntent = new Intent(Minuman.this, Dashboard.class);
        ImageView dasboardbtn = (ImageView) findViewById(R.id.navhome);

        dasboardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(DasboardIntent);
            }
        });

        Intent FavoritesIntent = new Intent(Minuman.this, Favorite.class);
        ImageView favbtn = (ImageView) findViewById(R.id.navfav);

        favbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(FavoritesIntent);
            }
        });

        Intent ProfileIntent = new Intent(Minuman.this, Profile.class);
        ImageView profilebtn = (ImageView) findViewById(R.id.navprof);

        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ProfileIntent);
            }
        });



        // Inisialisasi view dan SharedPreferences
        favoriteIcon1 = findViewById(R.id.mmfav1);
        favoriteIcon2 = findViewById(R.id.mmfav2);
        favoriteIcon3 = findViewById(R.id.mmfav3);

        sharedPreferences = getSharedPreferences("Favorites", MODE_PRIVATE);

        // Memuat data favorit dari SharedPreferences
        favoriteItems = loadFavoriteItems();

        // Set ikon berdasarkan status favorit
        updateFavoriteIcon(favoriteIcon1, "JusJeruk");
        updateFavoriteIcon(favoriteIcon2, "Alpukat");
        updateFavoriteIcon(favoriteIcon3, "Boba");

        // Set listener untuk favoriteIcon1
        favoriteIcon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFavorite("JusJeruk");
            }
        });

        // Set listener untuk favoriteIcon2
        favoriteIcon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFavorite("Alpukat");
            }
        });

        // Set listener untuk favoriteIcon3
        favoriteIcon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFavorite("Boba");
            }
        });
    }

    // Metode untuk menambahkan atau menghapus item dari favorit
    private void toggleFavorite(String itemName) {
        if (favoriteItems.contains(itemName)) {
            favoriteItems.remove(itemName); // Jika sudah ada, hapus dari favorit
            Toast.makeText(Minuman.this, itemName + " dihapus dari Favorit", Toast.LENGTH_SHORT).show();
        } else {
            favoriteItems.add(itemName); // Jika belum ada, tambahkan ke favorit
            Toast.makeText(Minuman.this, itemName + " ditambahkan ke Favorit", Toast.LENGTH_SHORT).show();
        }
        saveFavoriteItems(); // Simpan perubahan
        updateFavoriteIcon(favoriteIcon1, "JusJeruk");
        updateFavoriteIcon(favoriteIcon2, "Alpukat");
        updateFavoriteIcon(favoriteIcon3, "Boba");
    }

    // Metode untuk menyimpan Set<String> ke SharedPreferences
    private void saveFavoriteItems() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet("favoriteItems", favoriteItems); // Save Set instead of List
        editor.apply();
    }

    // Metode untuk memuat Set<String> dari SharedPreferences
    private Set<String> loadFavoriteItems() {
        return sharedPreferences.getStringSet("favoriteItems", new HashSet<>()); // Return empty set if no data
    }

    // Metode untuk memperbarui ikon favorit
    private void updateFavoriteIcon(ImageView imageView, String itemName) {
        if (favoriteItems.contains(itemName)) {
            imageView.setImageResource(R.drawable.favadd);  // Ikon ketika favorit
        } else {
            imageView.setImageResource(R.drawable.favorite);  // Ikon ketika belum favorit
        }
    }
}