package com.example.h071211073_finalmobile;

import android.provider.BaseColumns;

public class DatabaseContract {
    public static String TABLE_NAME = "bookmark";
    public static final class bookmarkColumns implements BaseColumns {
        public static final String COLUMN_POSTER_PATH = "poster_path";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_VOTE_AVERAGE = "vote_average";
        public static final String COLUMN_BACKDROP_PATH = "backdrop_path";
        public static final String COLUMN_OVERVIEW = "overview";

    }
}
