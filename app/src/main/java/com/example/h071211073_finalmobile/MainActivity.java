package com.example.h071211073_finalmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    ImageView btnMovie, btnTvShows, btnBookmarked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigateFragment(new MovieFragment());

        btnMovie = findViewById(R.id.btn_movie);
        btnMovie.setOnClickListener(view -> navigateFragment(new MovieFragment()));

        btnTvShows = findViewById(R.id.btn_tv);
        btnTvShows.setOnClickListener(view -> navigateFragment(new TvShowFragment()));

        btnBookmarked = findViewById(R.id.btn_bookmark);
        btnBookmarked.setOnClickListener(view -> navigateFragment(new BookmarkFragment()));
    }

    private void navigateFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view_tag, fragment)
                .addToBackStack(null)
                .commit();
    }
}