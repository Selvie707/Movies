package com.example.themovie.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.themovie.R;

public class TheSignUp extends AppCompatActivity {

    private CheckBox cbTerm;

    private Button btn_signup;
    private EditText etFirstName, etLastName, etUsername, etEmail, etPassword, etConfirmPassword;
    private TextView tvLogin, tvAgree;

    private String firstName, lastName, username, email, password, confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_sign_up);

        btn_signup = findViewById(R.id.btn_signup_signup);
        etFirstName = findViewById(R.id.et_signup_firstname);
        etLastName = findViewById(R.id.et_signup_lastname);
        etUsername = findViewById(R.id.et_signup_username);
        etEmail = findViewById(R.id.et_signup_email);
        etPassword = findViewById(R.id.et_signup_password);
        etConfirmPassword = findViewById(R.id.et_signup_confirmpassword);
        tvLogin = findViewById(R.id.tv_signup_login);
        cbTerm = findViewById(R.id.cb_signup_agree);
        tvAgree = findViewById(R.id.tv_signup_rememberme);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstName = etFirstName.getText().toString();
                lastName = etLastName.getText().toString();
                username = etUsername.getText().toString();
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();
                confirmPassword = etConfirmPassword.getText().toString();

                if(firstName.trim().equalsIgnoreCase("")) {
                    etFirstName.setError("Masukkan First Name");
                }
                if(lastName.trim().equalsIgnoreCase("")) {
                    etLastName.setError("Masukkan Last Name");
                }
                if(username.trim().equalsIgnoreCase("")) {
                    etUsername.setError("Masukkan Username");
                }
                if(email.trim().equalsIgnoreCase("")) {
                    etEmail.setError("Masukkan Email");
                }
                if(password.trim().equalsIgnoreCase("")) {
                    etPassword.setError("Masukkan Password");
                }
                if(confirmPassword.trim().equalsIgnoreCase("")) {
                    etConfirmPassword.setError("Masukkan Confirm Password");
                }
                if(!cbTerm.isChecked()){
                    Toast.makeText(TheSignUp.this, "You need to accept our privacy and policy", Toast.LENGTH_SHORT).show();
                }
                else if(password.trim().equalsIgnoreCase(confirmPassword)) {
                    Intent intent = new Intent(TheSignUp.this, TheLogin.class);

                    intent.putExtra("varUsername", username);
                    intent.putExtra("varPassword", password);

                    startActivity(intent);
                }
                else {
                    etPassword.setError("Password harus sama");
                    etConfirmPassword.setError("Password harus sama");
                }
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TheSignUp.this, TheLogin.class);
                startActivity(intent);
            }
        });
    }
}