package com.example.themovie.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.themovie.R;

public class MainMovie extends AppCompatActivity {

    private Button btnSignUp, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_movie);

        btnSignUp = findViewById(R.id.btn_main_movie_sign_up);
        btnLogin = findViewById(R.id.btn_main_movie_login);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMovie.this, TheSignUp.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMovie.this, TheLogin.class);
                startActivity(intent);
            }
        });
    }
}