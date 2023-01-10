package com.example.themovie.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;

import com.example.themovie.R;
import com.example.themovie.adapter.DownloadAdapter;
import com.example.themovie.data.DataDownload;
import com.example.themovie.data.Download;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class DownloadActivity extends AppCompatActivity {

    private BottomNavigationView bnvBottomMenu;

    private RecyclerView rvRecentlyWatched;

    private ArrayList<Download> downloadData = new ArrayList<>();

    private int mView = 0;

    static final String STATE_MODE = "MODE VIEW";

    // Card Recently Watched
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        outState.putInt(STATE_MODE, mView);
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        rvRecentlyWatched = findViewById(R.id.rv_movie_movie);
        rvRecentlyWatched.setHasFixedSize(true);

        downloadData.addAll(DataDownload.DataDownload());

        if (savedInstanceState != null) {
            mView = savedInstanceState.getInt(STATE_MODE);
            ShowDataRWMovie();
        }
        else {
            ShowDataRWMovie();
        }

        bnvBottomMenu = findViewById(R.id.bn_menu_bottom);
        bnvBottomMenu.setSelectedItemId(R.id.menu_download);

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
                        return true;
                    case R.id.menu_favorit:
                        startActivity(new Intent(getApplicationContext(), FavoriteActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.menu_profile:

                        String username = "Via707";
                        String bio = "let's go to the space";

                        Intent intentProfile = new Intent(getApplicationContext(), ProfileActivity.class);

                        intentProfile.putExtra("varProfilName", username);
                        intentProfile.putExtra("varProfilBio", bio);

                        startActivity(intentProfile);
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }

    private void ShowDataRWMovie() {
        mView = 0;
//        int colCount = getResources().getInteger(R.integer.)
        rvRecentlyWatched.setLayoutManager(new GridLayoutManager(this, 3));
        DownloadAdapter rwa = new DownloadAdapter(downloadData);
        rvRecentlyWatched.setAdapter(rwa);
    }

    // Recently Watched
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        ShowDataRWMovie();
        return super.onOptionsItemSelected(item);
    }
}