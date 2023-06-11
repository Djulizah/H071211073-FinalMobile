package com.example.h071211073_finalmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
    private ImageView imgBack, imgPoster, btnBack, btnMark, imgIcon;
    private TextView tvTitle, tvDate, tvRating, tvSynopsis;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imgBack = findViewById(R.id.img_back);
        imgPoster = findViewById(R.id.img_poster);
        imgIcon = findViewById(R.id.img_icon);
        tvTitle = findViewById(R.id.tv_title);
        tvDate = findViewById(R.id.tv_date);
        tvRating = findViewById(R.id.tv_rating);
        tvSynopsis = findViewById(R.id.tv_synopsis);
        btnMark = findViewById(R.id.btn_mark);

        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(view -> {
            onBackPressed();
        });

        databaseHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        if (intent.getParcelableExtra("movie") != null) {
            Movie movie = intent.getParcelableExtra("movie");

            String posterUrl = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + movie.getPosterPath();
            String backdropUrl = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + movie.getBackdropPath();

            tvTitle.setText(movie.getTitle());
            tvRating.setText(String.valueOf(movie.getVoteAverage()));
            Glide.with(this)
                    .load(posterUrl)
                    .into(imgPoster);
            Glide.with(this)
                    .load(backdropUrl)
                    .into(imgBack);
            tvSynopsis.setText(movie.getOverview());

            btnMark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!databaseHelper.isMovieInFavorites(movie.getTitle())) {
                        addToBookmark(movie.getId(), posterUrl, movie.getTitle(), movie.getReleaseDate(), movie.getVoteAverage(), backdropUrl, movie.getOverview());
                    } else {
                        deleteFromBookmark(movie.getTitle());
                    }
                }
            });

            int imgMovieicon = R.drawable.baseline_movie_24;
            imgIcon.setImageResource(imgMovieicon);

        } else if (intent.getParcelableExtra("tv_show") != null) {
            TvShow tvShow = intent.getParcelableExtra("tv_show");

            String posterUrl = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + tvShow.getPosterPath();
            String backdropUrl = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + tvShow.getBackdropPath();

            tvTitle.setText(tvShow.getName());
            tvRating.setText(String.valueOf(tvShow.getVoteAverage()));
            Glide.with(this)
                    .load(posterUrl)
                    .into(imgPoster);
            Glide.with(this)
                    .load(backdropUrl)
                    .into(imgBack);
            tvSynopsis.setText(tvShow.getOverview());

            btnMark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!databaseHelper.isMovieInFavorites(tvShow.getName())) {
                        addToBookmark(tvShow.getId(), posterUrl, tvShow.getName(), tvShow.getFirstAirDate(), tvShow.getVoteAverage(), backdropUrl, tvShow.getOverview());
                    } else {
                        deleteFromBookmark(tvShow.getName());
                    }
                }
            });

            int imgTvShowicon = R.drawable.baseline_tv_24;
            imgIcon.setImageResource(imgTvShowicon);

        } else {
            Bookmark bookmark = intent.getParcelableExtra("bookmark");

            String posterUrl = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + bookmark.getPosterPath();
            String backdropUrl = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + bookmark.getBackdropPath();

            tvTitle.setText(bookmark.getTitle());
            tvRating.setText(String.valueOf(bookmark.getVoteAverage()));
            Glide.with(this)
                    .load(posterUrl)
                    .into(imgPoster);
            Glide.with(this)
                    .load(backdropUrl)
                    .into(imgBack);
            tvSynopsis.setText(bookmark.getOverview());

            btnMark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!databaseHelper.isMovieInFavorites(bookmark.getTitle())) {
                        addToBookmark(bookmark.getId(), posterUrl, bookmark.getTitle(), bookmark.getDate(), bookmark.getVoteAverage(), backdropUrl, bookmark.getOverview());
                    } else {
                        deleteFromBookmark(bookmark.getTitle());
                    }
                }
            });
        }
    }
    private void addToBookmark(int id, String posterUrl, String title, String releaseDate, Double voteAverage, String backdropUrl, String overview) {
        Movie movie = new Movie(id, posterUrl, title, releaseDate, voteAverage, backdropUrl, overview);
        long result = databaseHelper.insertMovie(movie);
        if (result != -1) {
            Toast.makeText(this, "Added to bookmark", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        }

        int imgBookmarked = R.drawable.baseline_bookmark_24;
        btnMark.setImageResource(imgBookmarked);
    }
    private void deleteFromBookmark(String nama) {
        long result = databaseHelper.deleteMovie(nama);
        if (result != -1) {
            Toast.makeText(this, "Deleted from bookmark", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        }

        int imgBookmark = R.drawable.baseline_bookmark_border_24;
        btnMark.setImageResource(imgBookmark);
    }
}