package com.example.library;

import java.util.InputMismatchException;

public class LibrarySave extends QueriesDB {

    @Override
    protected void readUserInput() {
        try {
            System.out.println("Wprowadź tytuł");
            book.setTitle(scanner.nextLine());
            System.out.println("=====================================================");
            System.out.println("Wprowadź autora:");
            book.setAuthor(scanner.nextLine());
            System.out.println("=====================================================");
            System.out.println("Wprowadź rok:");
            book.setYear(scanner.nextInt());
            System.out.println("=====================================================");
            System.out.println("Wprowadź isbn:");
            book.setIsbn(scanner.nextLong());
        } catch (InputMismatchException e) {
            System.out.println("Book: wrong type input");
        }

    }

    @Override
    protected void queryIntoDatabase() {
        if (!book.getTitle().equals("") && book.getTitle() != null &&
                !book.getAuthor().equals("") && book.getAuthor() != null &&
                String.valueOf(book.getIsbn()).length() >= 10 && String.valueOf(book.getYear()).length() == 4) {
            bookDao.save(book);
            bookDao.closeConnection();
        } else {
            throw new UncorrectBookException();
        }
    }

    @Override
    public void run() {
        try {
            readUserInput();
            queryIntoDatabase();
            System.out.println("Poprawnie zapisano książkę: " + book.toString() + " w bazie danych.");
        } catch (UncorrectBookException f) {
            System.out.println("Book wasn't written.");
        }
    }
}
