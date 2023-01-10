package com.example.themovie.detail;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.themovie.R;
import com.example.themovie.activities.ProfileActivity;

public class EditProfile extends AppCompatActivity {

    private ImageView ivEdit, ivProfilPicture;
    private EditText etUsername, etEmail, etBio;
    private Button btnSimpan;

    private String username, email, bio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        ivEdit = findViewById(R.id.iv_editprofile_changeprofilepicture);
        ivProfilPicture = findViewById(R.id.civ_editprofile_profilepicture);
        etUsername = findViewById(R.id.et_editprofile_username);
        etEmail = findViewById(R.id.et_editprofile_email);
        etBio = findViewById(R.id.et_editprofile_bio);
        btnSimpan = findViewById(R.id.btn_editprofile_simpan);

        ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(EditProfile.this);
                dialog.setTitle("Perhatian");
                dialog.setMessage("Saat ini fitur belum tersedia");
                dialog.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                dialog.show();
            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = etUsername.getText().toString();
                email = etEmail.getText().toString();
                bio = etBio.getText().toString();

                if(username.trim().equalsIgnoreCase("")) {
                    etUsername.setError("Masukkan Username");
                }
                if(email.trim().equalsIgnoreCase("")) {
                    etEmail.setError("Masukkan Email");
                }
                if(bio.trim().equalsIgnoreCase("")) {
                    etBio.setError("Masukkan Bio");
                }
                else {
                    Toast.makeText(EditProfile.this, "Profile Berhasil Diubah", Toast.LENGTH_SHORT).show();

                    Intent intentProfile = new Intent(EditProfile.this, ProfileActivity.class);

                    intentProfile.putExtra("varProfilName", username);
                    intentProfile.putExtra("varProfilBio", bio);

                    startActivity(intentProfile);
                }
            }
        });
    }
}