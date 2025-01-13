package com.example.parachef;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;

public class Makanan extends AppCompatActivity {
    private ImageView favoriteIcon1, favoriteIcon2, favoriteIcon3, favoriteIcon4, favoriteIcon5;
    private Set<String> favoriteItems; // Using Set<String> instead of ArrayList
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.makanan);

        Intent risolIntent = new Intent(Makanan.this, drisol.class);
        LinearLayout risol = (LinearLayout) findViewById(R.id.drisol);

        Intent chickenIntent = new Intent(Makanan.this, dchicken.class);
        LinearLayout chicken = (LinearLayout) findViewById(R.id.dchicken);

        Intent sayurasemIntent = new Intent(Makanan.this, dsayurasem.class);
        LinearLayout sayurasem = (LinearLayout) findViewById(R.id.dsayurasem);

        Intent telurbaladoIntent = new Intent(Makanan.this, dtelurbalado.class);
        LinearLayout telurbalado = (LinearLayout) findViewById(R.id.dtelurbalado);

        Intent rendangIntent = new Intent(Makanan.this, drendang.class);
        LinearLayout rendang = (LinearLayout) findViewById(R.id.drendang);

        risol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(risolIntent);
            }
        });

        chicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(chickenIntent);
            }
        });

        sayurasem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(sayurasemIntent);
            }
        });

        telurbalado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(telurbaladoIntent);
            }
        });

        rendang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(rendangIntent);
            }
        });

        Intent DasboardIntent = new Intent(Makanan.this, Dashboard.class);
        ImageView dasboardbtn = (ImageView) findViewById(R.id.navhome);

        dasboardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(DasboardIntent);
            }
        });

        Intent FavoritesIntent = new Intent(Makanan.this, Favorite.class);
        ImageView favbtn = (ImageView) findViewById(R.id.navfav);

        favbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(FavoritesIntent);
            }
        });

        Intent ProfileIntent = new Intent(Makanan.this, Profile.class);
        ImageView profilebtn = (ImageView) findViewById(R.id.navprof);

        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ProfileIntent);
            }
        });



        // Inisialisasi view dan SharedPreferences
        favoriteIcon1 = findViewById(R.id.mfav1);
        favoriteIcon2 = findViewById(R.id.mfav2);
        favoriteIcon3 = findViewById(R.id.mfav3);
        favoriteIcon4 = findViewById(R.id.mfav4);
        favoriteIcon5 = findViewById(R.id.mfav5);

        sharedPreferences = getSharedPreferences("Favorites", MODE_PRIVATE);

        // Memuat data favorit dari SharedPreferences
        favoriteItems = loadFavoriteItems();

        // Set ikon berdasarkan status favorit
        updateFavoriteIcon(favoriteIcon1, "Risoles");
        updateFavoriteIcon(favoriteIcon2, "FriedChiken");
        updateFavoriteIcon(favoriteIcon3, "SayurAsem");
        updateFavoriteIcon(favoriteIcon4, "TelurBalada");
        updateFavoriteIcon(favoriteIcon5, "Rendang");

        // Set listener untuk favoriteIcon1
        favoriteIcon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFavorite("Risoles");
            }
        });

        // Set listener untuk favoriteIcon2
        favoriteIcon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFavorite("FriedChiken");
            }
        });

        favoriteIcon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFavorite("SayurAsem");
            }
        });

        favoriteIcon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFavorite("TelurBalada");
            }
        });

        favoriteIcon5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFavorite("Rendang");
            }
        });
    }

    // Metode untuk menambahkan atau menghapus item dari favorit
    private void toggleFavorite(String itemName) {
        if (favoriteItems.contains(itemName)) {
            favoriteItems.remove(itemName); // Jika sudah ada, hapus dari favorit
            Toast.makeText(Makanan.this, itemName + " dihapus dari Favorit", Toast.LENGTH_SHORT).show();
        } else {
            favoriteItems.add(itemName); // Jika belum ada, tambahkan ke favorit
            Toast.makeText(Makanan.this, itemName + " ditambahkan ke Favorit", Toast.LENGTH_SHORT).show();
        }
        saveFavoriteItems(); // Simpan perubahan
        updateFavoriteIcon(favoriteIcon1, "Risoles");
        updateFavoriteIcon(favoriteIcon2, "FriedChiken");
        updateFavoriteIcon(favoriteIcon3, "SayurAsem");
        updateFavoriteIcon(favoriteIcon4, "TelurBalada");
        updateFavoriteIcon(favoriteIcon5, "Rendang");
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