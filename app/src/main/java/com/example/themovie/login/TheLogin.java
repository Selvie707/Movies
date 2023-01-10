package com.example.themovie.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.themovie.R;
import com.example.themovie.activities.MainActivity;

public class TheLogin extends AppCompatActivity {

    private Button btnLogin;
    private TextView tvSignUp;
    private EditText etUsername, etPassword;

    private String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_login);

        btnLogin = findViewById(R.id.btn_login_login);
        tvSignUp = findViewById(R.id.tv_login_signup);
        etUsername = findViewById(R.id.et_login_username);
        etPassword = findViewById(R.id.et_login_password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();

                if(username.trim().equalsIgnoreCase("")) {
                    etUsername.setError("Masukkan Username");
                }
                if(password.trim().equalsIgnoreCase("")) {
                    etPassword.setError("Masukkan Password");
                }
                else {
                    Intent intentt = getIntent();

                    String usernamee = intentt.getStringExtra("varUsername");
                    String passwordd = intentt.getStringExtra("varPassword");

                    if (username.equals(usernamee) && password.equals(passwordd)) {
                        Intent intent = new Intent(TheLogin.this, MainActivity.class);

                        intent.putExtra("varUsername", usernamee);

                        startActivity(intent);
                    }
                    if (!username.equals(usernamee)){
                        etUsername.setError("Wrong Username");
                    }
                    if (!password.equals(passwordd)){
                        etPassword.setError("Wrong Password");
                    }
                }
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TheLogin.this, TheSignUp.class);
                startActivity(intent);
            }
        });
    }
}