package com.example.serverclientassignment;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.List;

import static com.example.serverclientassignment.MainActivity.getActivity;

public class ComputeService extends Service {


    public ComputeService()  {    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private final IMyAidlInterface.Stub mBinder =
            new IMyAidlInterface.Stub() {



                //instantiate the book data access object
//                BookDAO bookDAO = new BookDAO(getApplicationContext());
//                public String showAllRecords(){
//                    bookDAO.open();
////                    List<Book> books = bookDAO.getAllBooks();
//                    String str = "1";
////                    for (Book b : books) {
////                        String row = b.getId() + ", Title: " +
////                                b.getbookTitle() + ", Author: " +
////                                b.getbookAuthor() + ", Publisher: " +
////                                b.getBookPublisher() + ", Year: " + b.getbookYear();
////                        str += row + "\n";
////                    }
//                    bookDAO.close();
//                    return str;
//                }

//                @Override
//                public String clickedShow(int whichAttribute, String argument) throws RemoteException {
//                    String result = MainActivity.getActivity().showAllRecords();
//
//                    //return (argument + Integer.toString(whichAttribute));
//                    return result;
//                }

                @Override
                public String clickedShow(int whichAttribute, String argument) throws RemoteException {
                    String str = "";
                    Book[] blist = new Book[100];
                    Book b1 = new Book(1,"EE654","Author-1", "Publisher-1", "2019");
                    blist[0] = b1;
                    for (Book b : blist) {
                        String row = b.getId() + ", Title: " +
                                b.getbookTitle() + ", Author: " +
                                b.getbookAuthor() + ", Publisher: " +
                                b.getBookPublisher() + ", Year: " + b.getbookYear();
                        str += row + "\n";
                    }
                    return str;
                }

            };
}
