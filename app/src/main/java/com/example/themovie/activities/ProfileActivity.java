package com.example.themovie.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.themovie.R;
import com.example.themovie.detail.AboutUs;
import com.example.themovie.detail.EditProfile;
import com.example.themovie.detail.HelpCenter;
import com.example.themovie.detail.RateThisApp;
import com.example.themovie.login.MainMovie;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvProfileName, tvProfileBio;

    private static String username, bio;

    private BottomNavigationView bnvBottomMenu;
    private RecyclerView rvRecentlyWatched;

    private EditText etEditProfile, etRate, etHelpCenter, etAboutUs, etLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvProfileName = findViewById(R.id.tv_profil_username);
        tvProfileBio = findViewById(R.id.tv_profile_description);

        Intent intentProfile = getIntent();

        username = intentProfile.getStringExtra("varProfilName");
        bio = intentProfile.getStringExtra("varProfilBio");

        tvProfileName.setText(username);
        tvProfileName.setTextColor(getResources().getColor(R.color.via));

        tvProfileBio.setText(bio);

        //

        bnvBottomMenu = findViewById(R.id.bn_menu_bottom);
        bnvBottomMenu.setSelectedItemId(R.id.menu_profile);

        ProfileDetailListener();

        BottomMenu();
    }

    // Bottom Menu
    private void BottomMenu () {
        bnvBottomMenu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.menu_movie:
                        startActivity(new Intent(getApplicationContext(), MovieActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.menu_download:
                        startActivity(new Intent(getApplicationContext(), DownloadActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.menu_favorit:
                        startActivity(new Intent(getApplicationContext(), FavoriteActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.menu_profile:
                        return true;
                }
                return false;
            }
        });
    }

    // Profile Detail Intent
    private void ProfileDetailListener () {
        etEditProfile = findViewById(R.id.et_edit_profile);
        etRate = findViewById(R.id.et_rate);
        etHelpCenter = findViewById(R.id.et_help_center);
        etAboutUs = findViewById(R.id.et_about_us);
        etLogOut = findViewById(R.id.et_log_out);

        etEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, EditProfile.class);
                startActivity(intent);
            }
        });

        etRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, RateThisApp.class);
                startActivity(intent);
            }
        });

        etHelpCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, HelpCenter.class);
                startActivity(intent);
            }
        });

        etAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, AboutUs.class);
                startActivity(intent);
            }
        });

        etLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(ProfileActivity.this);
                dialog.setTitle("Perhatian");
                dialog.setMessage("Apakah kamu yakin ingin keluar?");
                dialog.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                dialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(ProfileActivity.this, MainMovie.class);
                        startActivity(intent);
                    }
                });
                dialog.show();
            }
        });
    }
}