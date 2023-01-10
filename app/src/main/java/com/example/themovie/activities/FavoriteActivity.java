package com.example.themovie.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.themovie.R;
import com.example.themovie.adapter.FavoritAdapter;
import com.example.themovie.adapter.MovieAdapter;
import com.example.themovie.api.GetApi;
import com.example.themovie.apivia.Datum;
import com.example.themovie.apivia.Retrofit;
import com.example.themovie.apivia.Root;
import com.example.themovie.apivia.TheApi;
import com.example.themovie.data.DataFavorit;
import com.example.themovie.data.Favorit;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteActivity extends AppCompatActivity {

    private BottomNavigationView bnvBottomMenu;
    private ProgressBar pbMovie;

    private RecyclerView rvRecentlyWatched;

    private FavoritAdapter adapterFavorit;
    private ArrayList<Datum> listFavorit;

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
        setContentView(R.layout.activity_favorite);

        rvRecentlyWatched = findViewById(R.id.rv_movie_movie);
        rvRecentlyWatched.setHasFixedSize(true);

        pbMovie = findViewById(R.id.pb_main);
        rvRecentlyWatched.setLayoutManager(new GridLayoutManager(this, 3));

        RetriverMovie();

        bnvBottomMenu = findViewById(R.id.bn_menu_bottom);
        bnvBottomMenu.setSelectedItemId(R.id.menu_favorit);

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

    private void RetriverMovie() {
        pbMovie.setVisibility(View.VISIBLE);

        TheApi ardData = Retrofit.getRetrofit().create(TheApi.class);
        Call<com.example.themovie.apivia.Root> retriverProcess = ardData.getTheMovie();

        retriverProcess.enqueue(new Callback<com.example.themovie.apivia.Root>() {
            @Override
            public void onResponse(Call<com.example.themovie.apivia.Root> call, Response<com.example.themovie.apivia.Root> response) {
                listFavorit = response.body().getData();
                adapterFavorit = new FavoritAdapter(listFavorit);
                rvRecentlyWatched.setAdapter(adapterFavorit);
                pbMovie.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Toast.makeText(FavoriteActivity.this, "Gagal guys", Toast.LENGTH_SHORT).show();
                pbMovie.setVisibility(View.INVISIBLE);
            }
        });
    }

//    private void ShowDataRWMovie() {
//        mView = 0;
////        int colCount = getResources().getInteger(R.integer.)
//        rvRecentlyWatched.setLayoutManager(new GridLayoutManager(this, 3));
//        FavoritAdapter rwa = new FavoritAdapter(favoritData);
//        rvRecentlyWatched.setAdapter(rwa);
//    }

    // Recently Watched
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        ShowDataRWMovie();
        return super.onOptionsItemSelected(item);
    }
}