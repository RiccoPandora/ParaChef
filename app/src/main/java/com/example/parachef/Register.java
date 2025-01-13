package com.example.parachef;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        Intent myIntent = new Intent(Register.this, Login.class);

        TextView textMasuk = (TextView) findViewById(R.id.masuk);
        textMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(myIntent);
            }
        });


        SharedPreferences preferences = getSharedPreferences("login_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        EditText EditNama = findViewById(R.id.regis_nama);
        EditText EditUsername = findViewById(R.id.regis_username);
        EditText EditPasswd = findViewById(R.id.regis_password);

        Button btnDaftar = (Button) findViewById(R.id.btndaftar);
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = String.valueOf(EditNama.getText());
                String user = String.valueOf(EditUsername.getText());
                String pass = String.valueOf(EditPasswd.getText());
                if (!nama.isEmpty() && !user.isEmpty() && !pass.isEmpty()){
                    editor.putString("name", nama);
                    editor.putString("username", user);
                    editor.putString("password", pass);
                    editor.apply();
                    startActivity(myIntent);
                }
            }
        });
    }
}