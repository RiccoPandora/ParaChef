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

import java.util.HashSet;
import java.util.Set;

public class BoluKue extends AppCompatActivity {
    private ImageView favoriteIcon1, favoriteIcon2, favoriteIcon3;
    private Set<String> favoriteItems; // Using Set<String> instead of ArrayList
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bolukue);

        Intent kleponIntent = new Intent(BoluKue.this, dklepon.class);
        LinearLayout klepon = (LinearLayout) findViewById(R.id.dklepon);

        Intent lupisIntent = new Intent(BoluKue.this, dlupis.class);
        LinearLayout lupis = (LinearLayout) findViewById(R.id.dlupis);

        Intent browniesIntent = new Intent(BoluKue.this, dbrownies.class);
        LinearLayout brownies = (LinearLayout) findViewById(R.id.dbrownies);

        klepon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(kleponIntent);
            }
        });

        lupis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(lupisIntent);
            }
        });

        brownies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(browniesIntent);
            }
        });

        Intent DasboardIntent = new Intent(BoluKue.this, Dashboard.class);
        ImageView dasboardbtn = (ImageView) findViewById(R.id.navhome);

        dasboardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(DasboardIntent);
            }
        });

        Intent FavoritesIntent = new Intent(BoluKue.this, Favorite.class);
        ImageView favbtn = (ImageView) findViewById(R.id.navfav);

        favbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(FavoritesIntent);
            }
        });

        Intent ProfileIntent = new Intent(BoluKue.this, Profile.class);
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
        updateFavoriteIcon(favoriteIcon1, "Kelepon");
        updateFavoriteIcon(favoriteIcon2, "Lupis");
        updateFavoriteIcon(favoriteIcon3, "Brownies");

        // Set listener untuk favoriteIcon1
        favoriteIcon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFavorite("Kelepon");
            }
        });

        // Set listener untuk favoriteIcon2
        favoriteIcon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFavorite("Lupis");
            }
        });

        // Set listener untuk favoriteIcon3
        favoriteIcon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFavorite("Brownies");
            }
        });
    }

    // Metode untuk menambahkan atau menghapus item dari favorit
    private void toggleFavorite(String itemName) {
        if (favoriteItems.contains(itemName)) {
            favoriteItems.remove(itemName); // Jika sudah ada, hapus dari favorit
            Toast.makeText(BoluKue.this, itemName + " dihapus dari Favorit", Toast.LENGTH_SHORT).show();
        } else {
            favoriteItems.add(itemName); // Jika belum ada, tambahkan ke favorit
            Toast.makeText(BoluKue.this, itemName + " ditambahkan ke Favorit", Toast.LENGTH_SHORT).show();
        }
        saveFavoriteItems(); // Simpan perubahan
        updateFavoriteIcon(favoriteIcon1, "Kelepon");
        updateFavoriteIcon(favoriteIcon2, "Lupis");
        updateFavoriteIcon(favoriteIcon3, "Brownies");
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