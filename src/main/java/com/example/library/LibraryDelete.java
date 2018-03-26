package com.example.library;

import java.util.InputMismatchException;

public class LibraryDelete extends QueriesDB {

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
            bookDao.delete(book.getId());
        } else {
            throw new UncorrectIDException();
        }
    }

    @Override
    public void run() {
        try {
            readUserInput();
            queryIntoDatabase();
            System.out.println("Poprawnie usunięto książkę: " + book.toString() + " z bazie danych.");
        } catch (UncorrectIDException f) {
            System.out.println("ID wasn't read properly. Book not found.");
        }
    }
}
