package com.example.h071211073_finalmobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BookMarkHelper {
    private static final String DATABASE_TABLE = DatabaseContract.TABLE_NAME;
    private static DatabaseHelper databaseHelper;
    private static SQLiteDatabase sqLiteDatabase;
    private static volatile BookMarkHelper INSTANCE;

    public BookMarkHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public static BookMarkHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new BookMarkHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        sqLiteDatabase = databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();
        if (sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
        }
    }

    public Cursor queryAll() {
        return sqLiteDatabase.query(
                DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                DatabaseContract.bookmarkColumns._ID + " ASC"
        );
    }

    public Cursor queryById(String id) {
        return sqLiteDatabase.query(
                DATABASE_TABLE,
                null,
                DatabaseContract.bookmarkColumns._ID + " = ?",
                new String[]{id},
                null,
                null,
                null,
                null
        );
    }

    public long insert(ContentValues values) {
        return sqLiteDatabase.insert(DATABASE_TABLE, null, values);
    }
    public int update(String id, ContentValues values) {
        return sqLiteDatabase.update(DATABASE_TABLE, values, DatabaseContract.bookmarkColumns._ID
                + " = ?", new String[]{id});
    }
    public int deleteById(String id) {
        return sqLiteDatabase.delete(DATABASE_TABLE, DatabaseContract.bookmarkColumns._ID + " = "
                + id, null);
    }
}
