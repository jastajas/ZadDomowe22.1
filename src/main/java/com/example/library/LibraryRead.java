package com.example.library;

import java.util.InputMismatchException;

public class LibraryRead extends QueriesDB {

    @Override
    protected void readUserInput() {
        try {
            System.out.println("Wpisz ID książki:");
            book.setId(scanner.nextLong());
        } catch (InputMismatchException e) {
            System.out.println("Book: wrong type input");
        }
    }

    @Override
    protected void queryIntoDatabase() {
        if (book.getId() > 0) {
            book = bookDao.read(book.getId());
        } else {
            throw new UncorrectIDException();
        }
    }

    @Override
    public void run() {
        try {
            readUserInput();
            queryIntoDatabase();
            if (book != null) {
                System.out.println(book.toString());

            } else {
                System.out.println("Wybrana pozycja nie jest zarejestrowana w bazie.");
                book = new Book();
            }
        } catch (UncorrectIDException f) {
            System.out.println("ID wasn't read properly. Book not found.");
        }
    }

}
