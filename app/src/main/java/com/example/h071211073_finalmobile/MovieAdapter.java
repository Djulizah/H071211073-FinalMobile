package com.example.h071211073_finalmobile;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private List<Movie> movieList;
    public MovieAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }
    private ClickListener clickListener;
    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_poster_gradient, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.setData(movie);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvYear;
        ImageView imgPoster;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvYear = itemView.findViewById(R.id.tv_year);
            imgPoster = itemView.findViewById(R.id.img_poster);
        }

        public void setData(Movie movie) {
            tvTitle.setText(String.valueOf(movie.getTitle()));
            tvYear.setText(String.valueOf(movie.getReleaseDate()));

            String posterPath = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + movie.getPosterPath();;
            Glide.with(itemView.getContext())
                    .load(posterPath)
                    .into(imgPoster);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                    intent.putExtra("movie", movie);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
    interface ClickListener {
        void onItemClicked(MovieResponse movieResponse);
    }
}
