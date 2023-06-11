package com.example.h071211073_finalmobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "bookmark.db";
    private static final int DATABASE_VERSION = 1;

    public static final String SQL_CREATE_TABLE =
            String.format(
                    "CREATE TABLE %s"
                    + "(%s INTEGER PRIMARY KEY AUTOINCREMENT,"
                            + "%s TEXT NOT NULL," + "%s TEXT NOT NULL,"
                            + "%s TEXT NOT NULL," + "%s TEXT NOT NULL,"
                            + "%s TEXT NOT NULL," + "%s TEXT NOT NULL)",
                    DatabaseContract.TABLE_NAME,
                    DatabaseContract.bookmarkColumns._ID,
                    DatabaseContract.bookmarkColumns.COLUMN_TITLE,
                    DatabaseContract.bookmarkColumns.COLUMN_DATE,
                    DatabaseContract.bookmarkColumns.COLUMN_VOTE_AVERAGE,
                    DatabaseContract.bookmarkColumns.COLUMN_OVERVIEW,
                    DatabaseContract.bookmarkColumns.COLUMN_POSTER_PATH,
                    DatabaseContract.bookmarkColumns.COLUMN_BACKDROP_PATH
            );

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long insertMovie(Movie movie) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.bookmarkColumns.COLUMN_TITLE, movie.getTitle());
        values.put(DatabaseContract.bookmarkColumns.COLUMN_DATE, movie.getReleaseDate());
        values.put(DatabaseContract.bookmarkColumns.COLUMN_OVERVIEW, movie.getOverview());
        values.put(DatabaseContract.bookmarkColumns.COLUMN_POSTER_PATH, movie.getPosterPath());
        values.put(DatabaseContract.bookmarkColumns.COLUMN_BACKDROP_PATH, movie.getBackdropPath());
        values.put(DatabaseContract.bookmarkColumns.COLUMN_VOTE_AVERAGE, movie.getVoteAverage());

        return sqLiteDatabase.insert(DatabaseContract.TABLE_NAME, null, values);
    }

    public int updateMovie(Movie movie) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.bookmarkColumns.COLUMN_TITLE, movie.getTitle());
        values.put(DatabaseContract.bookmarkColumns.COLUMN_DATE, movie.getReleaseDate());
        values.put(DatabaseContract.bookmarkColumns.COLUMN_OVERVIEW, movie.getOverview());
        values.put(DatabaseContract.bookmarkColumns.COLUMN_POSTER_PATH, movie.getPosterPath());
        values.put(DatabaseContract.bookmarkColumns.COLUMN_BACKDROP_PATH, movie.getBackdropPath());
        values.put(DatabaseContract.bookmarkColumns.COLUMN_VOTE_AVERAGE, movie.getVoteAverage());

        String selection = DatabaseContract.bookmarkColumns._ID + "=?";
        String[] selectionArgs = {String.valueOf(movie.getId())};

        return sqLiteDatabase.update(DatabaseContract.TABLE_NAME, values, selection, selectionArgs);
    }
    public int deleteMovie(String nama) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String selection = DatabaseContract.bookmarkColumns.COLUMN_TITLE + "=?";
        String[] selectionArgs = {String.valueOf(nama)};

        return sqLiteDatabase.delete(DatabaseContract.TABLE_NAME, selection, selectionArgs);
    }

    public Cursor getAllMovies() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String[] projection = {
                DatabaseContract.bookmarkColumns._ID,
                DatabaseContract.bookmarkColumns.COLUMN_TITLE,
                DatabaseContract.bookmarkColumns.COLUMN_DATE,
                DatabaseContract.bookmarkColumns.COLUMN_OVERVIEW,
                DatabaseContract.bookmarkColumns.COLUMN_POSTER_PATH,
                DatabaseContract.bookmarkColumns.COLUMN_BACKDROP_PATH,
                DatabaseContract.bookmarkColumns.COLUMN_VOTE_AVERAGE,
        };
        return sqLiteDatabase.query(
                DatabaseContract.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
    }

    public boolean isMovieInFavorites(String movieTitle) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            String query = "SELECT * FROM " + DatabaseContract.TABLE_NAME +
                    " WHERE " + DatabaseContract.bookmarkColumns.COLUMN_TITLE + " = ?";
            String[] selectionArgs = {String.valueOf(movieTitle)};
            cursor = sqLiteDatabase.rawQuery(query, selectionArgs);
            if (cursor != null && cursor.getCount() > 0) {
                return true;
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return false;
    }
}
