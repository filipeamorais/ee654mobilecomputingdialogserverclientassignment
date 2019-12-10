package com.example.serverclientassignment;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import static com.example.serverclientassignment.MainActivity.getActivity;

public class ComputeService extends Service {

    BookDAO bookDAO;
    public ComputeService()  {    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    //** Called when the service is being created. */
    @Override
    public void onCreate() {
        bookDAO = new BookDAO(this);

    }

    private final IMyAidlInterface.Stub mBinder =
            new IMyAidlInterface.Stub() {

                //instantiate the book data access object
                public String showAllRecords(){
                    bookDAO.open();
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

                public String showBy(String selection, String argument){
                    bookDAO.open();
                    List<Book> books = bookDAO.getBooks(selection, argument);
                    String str = "";
                    for (Book b : books) {
                        String row = b.getId() + ": Title: " +
                                b.getbookTitle()  + ", Author: " +
                                b.getbookAuthor() + ", Publisher: " +
                                b.getBookPublisher() + ", Year: " + b.getbookYear();
                        str += row + "\n";
                    }
                    bookDAO.close();
                    return str;
                }

                public String deleteAllRecords(){
                    bookDAO.open();
                    bookDAO.deleteAllBooks();
                    bookDAO.close();
                    String str = "All books deleted!";
                    return str;
                }

                public String deleteBy(String selection, String argument){
                    bookDAO.open();
                    bookDAO.deleteBooks(selection, argument);
                    bookDAO.close();
                    String str = "Book(s) deleted!";
                    return str;
                }

                public String insertCommand(Book filledBook) {
                    bookDAO.open();
                    bookDAO.addBook(filledBook);
                    bookDAO.close();
                    String str = "Book added!";
                    return str;
                }

                public String updateCommand(Book filledBook) {
                    bookDAO.open();
                    bookDAO.updateBook(filledBook);
                    bookDAO.close();
                    String str = "Book updated!";
                    return str;
                }

                @Override
                public String clickedShow(int whichAttribute, String argument) throws RemoteException {
                    String result = "Empty";
                    switch (whichAttribute){
                        case 0:
                            result = showAllRecords();
                            return result;
                        case 1:
                            result = showBy(DBHandler.KEY_TITLE, argument);
                            return result;
                        case 2:
                            result = showBy(DBHandler.KEY_AUTHOR, argument);
                            return result;
                        case 3:
                            result = showBy(DBHandler.KEY_PUBLISHER, argument);
                            return result;
                        case 4:
                            result = showBy(DBHandler.KEY_YEAR, argument);
                            return result;
                    }
                    return ("Nothing was chosen");
                }

                @Override
                public String clickedDelete(int whichAttribute, String argument) throws RemoteException {
                    String result = "Empty";
                    switch (whichAttribute){
                        case 0:
                            result = deleteAllRecords();
                            return result;
                        case 1:
                            result = deleteBy(DBHandler.KEY_TITLE, argument);
                            return result;
                        case 2:
                            result = deleteBy(DBHandler.KEY_AUTHOR, argument);
                            return result;
                        case 3:
                            result = deleteBy(DBHandler.KEY_PUBLISHER, argument);
                            return result;
                        case 4:
                            result = deleteBy(DBHandler.KEY_YEAR, argument);
                            return result;
                    }
                    return ("Nothing was chosen");
                }

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
