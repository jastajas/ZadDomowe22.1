package com.example.library;

import java.util.Scanner;

public abstract class QueriesDB {

    Book book;
    Scanner scanner;
    BookDao bookDao;

    public QueriesDB() {
        book = new Book();
        scanner = new Scanner(System.in);
        bookDao = new BookDao();
    }


    protected abstract void readUserInput();


    protected abstract void queryIntoDatabase();


    public abstract void run();
}
