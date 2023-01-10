package com.example.themovie.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.themovie.R;
import com.example.themovie.activities.MovieActivity;
import com.example.themovie.models.Genre;
import com.example.themovie.models.Result;

import java.util.ArrayList;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder> {
    private ArrayList<Genre> daftarMovie;

    public GenreAdapter(ArrayList<Genre> daftarMovie) {
        this.daftarMovie = daftarMovie;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Genre rwm = daftarMovie.get(position);
        holder.tvGenre.setText(rwm.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), MovieActivity.class);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return daftarMovie.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout clGenre;
        TextView tvGenre;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            clGenre = itemView.findViewById(R.id.cl_category);
            tvGenre = itemView.findViewById(R.id.tv_category);
        }
    }
}
