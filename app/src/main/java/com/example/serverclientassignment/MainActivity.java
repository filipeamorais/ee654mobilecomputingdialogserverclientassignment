package com.example.serverclientassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    BookDAO bookDAO;
    private static MainActivity instance;
    TextView textViewResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = (TextView) findViewById(R.id.textViewResult);
        bookDAO = new BookDAO(this);
        instance = this;
    }

    public String showAllRecords(){
        bookDAO.open();
        textViewResult.setText("here1");
        List<Book> books = bookDAO.getAllBooks();
        String str = "1";
//                    for (Book b : books) {
//                        String row = b.getId() + ", Title: " +
//                                b.getbookTitle() + ", Author: " +
//                                b.getbookAuthor() + ", Publisher: " +
//                                b.getBookPublisher() + ", Year: " + b.getbookYear();
//                        str += row + "\n";
//                    }
        textViewResult.setText("here2");
        return str;
    }

    public static MainActivity getActivity() {
        return instance;
    }

    @Override protected void onResume() {
        bookDAO.open();
        super.onResume();
    }

    @Override protected void onPause() {
        bookDAO.close();
        super.onPause();
    }
}
