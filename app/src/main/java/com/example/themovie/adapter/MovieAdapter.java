package com.example.themovie.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import com.example.themovie.R;
import com.example.themovie.data.Movie;
import com.example.themovie.detail.DetailRecentlyWatched;
import com.example.themovie.models.Result;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private ArrayList<Result> daftarMovie;

    public MovieAdapter(ArrayList<Result> daftarMovie) {
        this.daftarMovie = daftarMovie;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_viewpager, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Result movie = daftarMovie.get(position);
        holder.tvTitle.setText(movie.getTitle());
        holder.tvYear.setText(movie.getRelease_date());
        Glide.with(holder.itemView.getContext())
                .load(movie.getPoster_path())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.ivPhoto);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = String.valueOf(movie.getId());

                Intent intent = new Intent(holder.itemView.getContext(), DetailRecentlyWatched.class);
                intent.putExtra("varId", id);

                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return daftarMovie.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPhoto;
        TextView tvTitle, tvYear;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivPhoto = itemView.findViewById(R.id.iv_movie);
            tvTitle = itemView.findViewById(R.id.tv_movie_title);
            tvYear = itemView.findViewById(R.id.tv_movie_year);
        }
    }
}
