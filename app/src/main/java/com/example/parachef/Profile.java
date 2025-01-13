package com.example.parachef;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);


        Intent DasboardIntent = new Intent(Profile.this, Dashboard.class);
        ImageView dasboardbtn = (ImageView) findViewById(R.id.navhome);

        dasboardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(DasboardIntent);
            }
        });

        Intent FavoritesIntent = new Intent(Profile.this, Favorite.class);
        ImageView favbtn = (ImageView) findViewById(R.id.navfav);

        favbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(FavoritesIntent);
            }
        });
        Intent AboutUsIntent = new Intent(Profile.this, AboutUs.class);
        Button Aboutbtn = (Button) findViewById(R.id.btnAbout);

        Aboutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(AboutUsIntent);
            }
        });
        Intent LogoutIntent = new Intent(Profile.this, Login.class);
        Button Logoutbtn = (Button) findViewById(R.id.btnLogout);

        Logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(LogoutIntent);
            }
        });
    }
}