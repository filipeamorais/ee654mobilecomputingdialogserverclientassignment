package com.example.serverclientassignment;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.TextView;

import java.util.List;

import static com.example.serverclientassignment.MainActivity.getActivity;

public class ComputeService extends Service {

    TextView textViewResult;
    BookDAO bookDAO;
    public ComputeService()  {    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    //** Called when the service is being created. */
    @Override
    public void onCreate() {
//        textViewResult = (TextView) textViewResult.findViewById(R.id.textViewResult);
//        textViewResult.setText("here1");
        bookDAO = new BookDAO(this);

    }

    private final IMyAidlInterface.Stub mBinder =
            new IMyAidlInterface.Stub() {

                //instantiate the book data access object
//                BookDAO bookDAO = new BookDAO(getApplicationContext());
                public String showAllRecords(){
                    bookDAO.open();
                    Book b1 = new Book(1,"EE654","Author-1", "Publisher-1", "2019");
                    bookDAO.addBook(b1);
                    List<Book> books = bookDAO.getAllBooks();
                    String str = "1";
                    for (Book b : books) {
                        String row = b.getId() + ", Title: " +
                                b.getbookTitle() + ", Author: " +
                                b.getbookAuthor() + ", Publisher: " +
                                b.getBookPublisher() + ", Year: " + b.getbookYear();
                        str += row + "\n";
                    }
                    bookDAO.close();
                    return str;
                }

                @Override
                public String clickedShow(int whichAttribute, String argument) throws RemoteException {
                    //String result = MainActivity.getActivity().showAllRecords();
                    String result = showAllRecords();

                    //return (argument + Integer.toString(whichAttribute));
                    return result;
                }

//                @Override
//                public String clickedShow(int whichAttribute, String argument) throws RemoteException {
//                    String str = "";
//                    Book[] blist = new Book[100];
//                    Book b1 = new Book(1,"EE654","Author-1", "Publisher-1", "2019");
//                    blist[0] = b1;
//                    for (Book b : blist) {
//                        String row = b.getId() + ", Title: " +
//                                b.getbookTitle() + ", Author: " +
//                                b.getbookAuthor() + ", Publisher: " +
//                                b.getBookPublisher() + ", Year: " + b.getbookYear();
//                        str += row + "\n";
//                    }
//                    return str;
//                }
                Book[] blist = new Book[10];

                @Override
                public Book[] getBookList(String argument) {
                    Book b1 = new Book(1,"EE654","Author-1", "Publisher-1", "2019");
                    Book b2 = new Book(2,"Title-2","Author-2", "Publisher-2", "2018");
                    //Book[] blist = new Book[2];
                    int intArg = Integer.parseInt(argument);
                    blist[intArg + 1] = b1;
                    blist[intArg + 2] = b2;
//                    blist[1] = b1;
//                    blist[2] = b2;
                    return blist;
                }
            };

}
