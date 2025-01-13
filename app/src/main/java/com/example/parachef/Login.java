package com.example.parachef;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Set;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        SharedPreferences preferences = getSharedPreferences("login_prefs", MODE_PRIVATE);
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putString("name", "Ricco");
//        editor.putString("username", "user1");
//        editor.putString("password", "user123");
//        editor.apply();

        Intent myIntent = new Intent(Login.this, Dashboard.class);
        Button btn = (Button)findViewById(R.id.btnLogin);

        TextInputEditText username = findViewById(R.id.username);
        TextInputEditText pasword = findViewById(R.id.password);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = String.valueOf(username.getText());
                String pass = String.valueOf(pasword.getText());

                if (user.equals(preferences.getString("username",null)) && pass.equals(preferences.getString("password",null))){
                    startActivity(myIntent);
                } else {
                    Toast.makeText(getApplicationContext(), "Username atau Password Salah",
                            Toast.LENGTH_LONG).show();

                }
            }
        });
        Intent IntentRegister = new Intent(Login.this, Register.class);
        TextView btnregister = (TextView) findViewById(R.id.regist_id);

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(IntentRegister);
            }
        });
    }

}