package com.example.parachef;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class AboutUs extends AppCompatActivity {

    private ImageView waButton, igButton, gmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutus);

        // Initialize ImageView buttons
        waButton = findViewById(R.id.wa);
        igButton = findViewById(R.id.ig);
        gmButton = findViewById(R.id.gm);

        // Set onClick listeners

        // Intent for WhatsApp
        waButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "+6282121616871";  // Replace with the actual number
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://wa.me/" + phoneNumber));
                startActivity(intent);
            }
        });

        // Intent for Instagram
        igButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.instagram.com/riccopandora/");  // Replace with actual Instagram username
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.setPackage("com.instagram.android");
                try {
                    startActivity(intent);
                } catch (android.content.ActivityNotFoundException e) {
                    // Open in browser if Instagram app is not installed
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
                }
            }
        });

        // Intent for Gmail
        // Intent for Gmail
        gmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mailto = "mailto:20220810114@uniku.ac.id" +
                        "?subject=" + Uri.encode("Subject here") +
                        "&body=" + Uri.encode("Hello, this is a message.");
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(mailto));
                try {
                    startActivity(intent);
                } catch (android.content.ActivityNotFoundException ex) {
                    // Handle case when no email clients are installed
                }
            }
        });
        Intent DasboardIntent = new Intent(AboutUs.this, Dashboard.class);
        ImageView dasboardbtn = (ImageView) findViewById(R.id.navhome);

        dasboardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(DasboardIntent);
            }
        });

        Intent FavoritesIntent = new Intent(AboutUs.this, Favorite.class);
        ImageView favbtn = (ImageView) findViewById(R.id.navfav);

        favbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(FavoritesIntent);
            }
        });
    }
}
