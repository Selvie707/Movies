package com.example.themovie.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.themovie.R;
import com.example.themovie.adapter.DetailGenreAdapter;
import com.example.themovie.api.GetApi;
import com.example.themovie.api.Retrofit;
import com.example.themovie.apivia.TheApi;
import com.example.themovie.detailmodel.Genre;
import com.example.themovie.detailmodel.Root;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailRecentlyWatched extends AppCompatActivity {

    private ArrayList<Genre> alGenre;
    private DetailGenreAdapter adapterGenre;
    private LinearLayoutManager llmMovie;

    private RecyclerView rvGenre;
    private ImageView ivPhoto;
    private TextView tvTitle, tvOverview, tvRate;

    private String title, releasedate, genre, posterpath, sinopsis;
    private double ratting;

    private CheckBox cbFavorit;

    private int mView = 0; // 0 card, 1 grid
    //static variabel
    static final String STATE_MODE = "MODE VIEW";

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(STATE_MODE, mView);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_recently_watched);

        Intent intent = getIntent();
        String id = intent.getStringExtra("varId");

        rvGenre = findViewById(R.id.rv_detailrecentlywatched_genre);

        llmMovie = new GridLayoutManager(this, 2);
        rvGenre.setLayoutManager(llmMovie);

        ivPhoto = findViewById(R.id.iv_detail_movie);
        tvTitle = findViewById(R.id.tv_detail_title);
        tvRate = findViewById(R.id.tv_detailrecentlywatched_ratting);
        tvOverview = findViewById(R.id.tv_detail_sinopsis);

        RetriverMovie();

        GetApi api = Retrofit.getRetrofit().create(GetApi.class);
        Call<Root> detailApi = api.getDetail(Integer.parseInt(id),
                ("0a9d3ed8c00f265589f595b6f3b92228"));

        detailApi.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                title = response.body().getTitle();
                releasedate = response.body().getRelease_date();
                genre = String.valueOf(response.body().getGenres());
                posterpath = response.body().getPoster_path();
                sinopsis = response.body().getOverview();
                ratting = response.body().getVote_average();

                String rate = String.valueOf(response.body().getVote_average());

                tvTitle.setText(response.body().getTitle());
                tvRate.setText(rate);
                tvOverview.setText(response.body().getOverview());
                Glide.with(DetailRecentlyWatched.this).load(response.body().getPoster_path()).into(ivPhoto);

                cbFavorit = findViewById(R.id.cb_detail_favorite);

                cbFavorit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TheApi ardData = Retrofit.getRetrofit().create(TheApi.class);
                        Call<com.example.themovie.apivia.Root> retPro = ardData.createMovie(title, posterpath, genre, ratting, releasedate, sinopsis);

                        retPro.enqueue(new Callback<com.example.themovie.apivia.Root>() {
                            @Override
                            public void onResponse(Call<com.example.themovie.apivia.Root> call, Response<com.example.themovie.apivia.Root> response) {

                                if (cbFavorit.isChecked()) {
                                    MyToast("Ditambahkan ke favorit");
                                }
                                else {
                                    MyToast("Batal ditambahkan ke favorit");
                                }

                                finish();
                            }

                            @Override
                            public void onFailure(Call<com.example.themovie.apivia.Root> call, Throwable t) {
                                Toast.makeText(DetailRecentlyWatched.this, "Gagal terhubung ke server : "
                                        + t.getMessage(), Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                    }
                });
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {

            }
        });
    }

    private void MyToast (String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    private void RetriverMovie() {

        Intent intent = getIntent();
        String id = intent.getStringExtra("varId");

        GetApi apii = Retrofit.getRetrofit().create(GetApi.class);
        Call<Root> retriverGenre = apii.getDetail(Integer.parseInt(id), ("0a9d3ed8c00f265589f595b6f3b92228"));

        retriverGenre.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                alGenre = response.body().getGenres();
                adapterGenre = new DetailGenreAdapter(alGenre);
                rvGenre.setAdapter(adapterGenre);
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Toast.makeText(DetailRecentlyWatched.this, "Gagal guys", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        RetriverMovie();
    }
}