package com.example.serverclientassignment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "booksDb";
    public static final String TABLE_BOOKS = "books";
    public static final String KEY_ID = "id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_PUBLISHER = "publisher";
    public static final String KEY_AUTHOR = "author";
    public static final String KEY_YEAR = "year";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null,
                DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableString = "CREATE TABLE " +
                TABLE_BOOKS + "(" + KEY_ID +
                " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT, "+
                KEY_AUTHOR + " TEXT,"+  KEY_PUBLISHER + " TEXT,"+ KEY_YEAR + " TEXT" + ")";
        db.execSQL(createTableString);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +
                TABLE_BOOKS);
        onCreate(db);
    }
}
