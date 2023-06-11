package com.example.h071211073_finalmobile;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class BookmarkFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmark, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<Bookmark> bookmarkList = getDatabase();

        RecyclerView rvBookmarks = view.findViewById(R.id.rv_bookmark);
        rvBookmarks.setLayoutManager(new LinearLayoutManager(getActivity()));

        BookmarkAdapter bookmarkAdapter = new BookmarkAdapter(bookmarkList);
        rvBookmarks.setAdapter(bookmarkAdapter);
    }

    private List<Bookmark> getDatabase() {
        List<Bookmark> favoriteList = new ArrayList<>();
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        Cursor cursor = databaseHelper.getAllMovies();

        if (cursor != null && cursor.moveToFirst()) {

            int idColumnIndex = cursor.getColumnIndex(DatabaseContract.bookmarkColumns._ID);
            int titleColumnIndex = cursor.getColumnIndex(DatabaseContract.bookmarkColumns.COLUMN_TITLE);
            int dateColumnIndex = cursor.getColumnIndex(DatabaseContract.bookmarkColumns.COLUMN_DATE);
            int overviewColumnIndex = cursor.getColumnIndex(DatabaseContract.bookmarkColumns.COLUMN_OVERVIEW);
            int posterPathColumnIndex = cursor.getColumnIndex(DatabaseContract.bookmarkColumns.COLUMN_POSTER_PATH);
            int backdropPathColumnIndex = cursor.getColumnIndex(DatabaseContract.bookmarkColumns.COLUMN_BACKDROP_PATH);
            int voteAverageColumnIndex = cursor.getColumnIndex(DatabaseContract.bookmarkColumns.COLUMN_VOTE_AVERAGE);

            do {
                int id = (idColumnIndex != -1) ? cursor.getInt(idColumnIndex) : -1;
                String title = (titleColumnIndex != -1) ? cursor.getString(titleColumnIndex) : null;
                String date = (dateColumnIndex != -1) ? cursor.getString(dateColumnIndex) : null;
                String overview = (overviewColumnIndex != -1) ? cursor.getString(overviewColumnIndex) : null;
                String posterPath = (posterPathColumnIndex != -1) ? cursor.getString(posterPathColumnIndex) : null;
                String backdropPath = (backdropPathColumnIndex != -1) ? cursor.getString(backdropPathColumnIndex) : null;
                double voteAvg = (voteAverageColumnIndex != -1) ? cursor.getDouble(voteAverageColumnIndex) : 0.0;

                Bookmark bookmark = new Bookmark(id, posterPath, title, date, voteAvg, backdropPath, overview);
                favoriteList.add(bookmark);
            } while (cursor.moveToNext());

        }

        if (cursor != null) {
            cursor.close();
        }

        return favoriteList;
    }
}