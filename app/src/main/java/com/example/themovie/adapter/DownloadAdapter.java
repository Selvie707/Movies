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
import com.example.themovie.data.Download;
import com.example.themovie.detail.DetailRecentlyWatched;

import java.util.ArrayList;

public class DownloadAdapter extends RecyclerView.Adapter<DownloadAdapter.ViewHolder> {
    private ArrayList<Download> daftarDownload;

    public DownloadAdapter(ArrayList<Download> daftarDownload) {
        this.daftarDownload = daftarDownload;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recently_watched_viewpager, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Download rwm = daftarDownload.get(position);
        holder.tvTitle.setText(rwm.getTitle());
        holder.tvYear.setText(rwm.getYear());
        Glide.with(holder.itemView.getContext())
                .load(rwm.getPhoto())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.ivPhoto);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = rwm.getTitle();
                String year = rwm.getYear();
                String photo = rwm.getPhoto();

                Intent intent = new Intent(holder.itemView.getContext(), DetailRecentlyWatched.class);
                intent.putExtra("varTitle", title);
                intent.putExtra("varYear", year);
                intent.putExtra("varPhoto", photo);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return daftarDownload.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPhoto;
        TextView tvTitle, tvYear;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivPhoto = itemView.findViewById(R.id.iv_recently_watched);
            tvTitle = itemView.findViewById(R.id.tv_recent_title);
            tvYear = itemView.findViewById(R.id.tv_recent_year);
        }
    }
}
