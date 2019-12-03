package com.example.serverclientassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private DBHandler dBHandler;
    private SQLiteDatabase db;

    public BookDAO(Context context) {
        dBHandler = new DBHandler(context);
    }

    public void open() throws SQLException {
        db = dBHandler.getWritableDatabase();
    }

    public void close() {
        dBHandler.close();
    }

    public List<Book> getAllBooks() {
        List<Book> bookList = new ArrayList<Book>();
        String selectQuery = "SELECT  * FROM " +
                DBHandler.TABLE_BOOKS;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Book book = new Book();
                book.setId(Integer.parseInt(cursor.getString(0)));
                book.setbookTitle(cursor.getString(1));
                book.setbookAuthor(cursor.getString(2));
                book.setBookPublisher(cursor.getString(3));
                book.setbookYear(cursor.getString(4));
                bookList.add(book);
            } while (cursor.moveToNext());
        }
        return bookList;
    }

    void addBook(Book book) {
        ContentValues values = new ContentValues();
        values.put(DBHandler.KEY_TITLE,
                book.getbookTitle());
        values.put(DBHandler.KEY_AUTHOR,
                book.getbookAuthor());
        values.put(DBHandler.KEY_PUBLISHER,
                book.getBookPublisher());
        values.put(DBHandler.KEY_YEAR,
                book.getbookYear());
        db.insert(DBHandler.TABLE_BOOKS, null, values);
    }

    void deleteAllBooks(){
        db.execSQL("delete from " +
                DBHandler.TABLE_BOOKS);
    }

    public List<Book> getBooks (String selection, String argument){
        List<Book> bookList = new ArrayList<Book>();
        String[] columnsToRetrieve ={DBHandler.KEY_ID, DBHandler.KEY_TITLE, DBHandler.KEY_AUTHOR, DBHandler.KEY_PUBLISHER, DBHandler.KEY_YEAR};
        String[] argumentOfSelection = {argument};
        Cursor cursor = db.query(DBHandler.TABLE_BOOKS, columnsToRetrieve,selection+ "=?", argumentOfSelection,null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Book book = new Book();
                book.setId(Integer.parseInt(cursor.getString(0)));
                book.setbookTitle(cursor.getString(1));
                book.setbookAuthor(cursor.getString(2));
                book.setBookPublisher(cursor.getString(3));
                book.setbookYear(cursor.getString(4));
                bookList.add(book);
            } while (cursor.moveToNext());
        }
        return bookList;
    }

    public void deleteBooks(String selection, String argument) {
        String[] argumentOfSelection = {argument};
        db.delete(DBHandler.TABLE_BOOKS, selection +
                "= ?", argumentOfSelection);
    }

    public int updateBook(Book book) {
        ContentValues values = new ContentValues();
        values.put(DBHandler.KEY_TITLE, book.getbookTitle());
        values.put(DBHandler.KEY_AUTHOR, book.getbookAuthor());
        values.put(DBHandler.KEY_PUBLISHER, book.getBookPublisher());
        values.put(DBHandler.KEY_YEAR, book.getbookYear());
        return db.update(DBHandler.TABLE_BOOKS, values,
                DBHandler.KEY_ID + " = ?",
                new String[] { String.valueOf(book.getId()) });
    }

}
