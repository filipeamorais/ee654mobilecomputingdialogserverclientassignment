package com.example.serverclientassignment;

import java.io.Serializable;

public class Book implements Serializable {
    int id;
    String bookTitle;
    String bookAuthor;
    String bookPublisher;
    String bookYear;

    public Book(){

    }

    public Book(int id, String bookTitle, String bookAuthor, String bookPublisher, String bookYear){
        this.id = id;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookPublisher = bookPublisher;
        this.bookYear = bookYear;
    }

    public int getId() { return id; }

    public void setId(int id){this.id = id;}

    public String getbookTitle() {
        return bookTitle;
    }

    public void setbookTitle(String title) { bookTitle = title; }

    public String getBookPublisher() {
        return bookPublisher;
    }

    public void setBookPublisher(String publisher) { bookPublisher = publisher; }

    public String getbookAuthor() {
        return bookAuthor;
    }

    public void setbookAuthor(String author) { bookAuthor = author; }

    public String getbookYear() {
        return bookYear;
    }

    public void setbookYear(String year) { bookYear = year; }
}
