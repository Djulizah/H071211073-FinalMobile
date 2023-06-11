package com.example.h071211073_finalmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView btnMovie, btnTvShows, btnBookmarked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_view_tag, new MovieFragment(),
                        MovieFragment.class.getSimpleName())
                .addToBackStack(null)
                .commit();

        btnMovie = findViewById(R.id.btn_movie);
        btnMovie.setOnClickListener(view -> {
            MovieFragment movieFragment = new MovieFragment();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container_view_tag, movieFragment,
                            MovieFragment.class.getSimpleName())
                    .addToBackStack(null)
                    .commit();
        });

        btnTvShows = findViewById(R.id.btn_tv);
        btnTvShows.setOnClickListener(view -> {
            TvShowFragment tvShow = new TvShowFragment();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container_view_tag, tvShow,
                            TvShowFragment.class.getSimpleName())
                    .addToBackStack(null)
                    .commit();
        });


        btnBookmarked = findViewById(R.id.btn_bookmark);
        btnBookmarked.setOnClickListener(view -> {
            BookmarkFragment bookmarkFragment = new BookmarkFragment();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container_view_tag, bookmarkFragment,
                            BookmarkFragment.class.getSimpleName())
                    .addToBackStack(null)
                    .commit();
        });
    }
}