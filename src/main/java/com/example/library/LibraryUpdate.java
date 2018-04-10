package com.example.library;

import java.util.InputMismatchException;

public class LibraryUpdate extends QueriesDB {

    @Override
    protected void readUserInput() {
        try {
            System.out.println("Wprowadź ID pozycji:");
            book.setId(scanner.nextLong());
            scanner.nextLine();
            System.out.println("=====================================================");
            System.out.println("Wprowadź nowy tytuł:");
            book.setTitle(scanner.nextLine());
            System.out.println("=====================================================");
            System.out.println("Wprowadź nowego autora:");
            book.setAuthor(scanner.nextLine());
            System.out.println("=====================================================");
            System.out.println("Wprowadź nowy rok:");
            book.setYear(scanner.nextInt());
            System.out.println("=====================================================");
            System.out.println("Wprowadź nowy isbn:");
            book.setIsbn(scanner.nextLong());
        } catch (InputMismatchException e) {
            System.out.println("Book: wrong type input");
        }
    }

    @Override
    protected void queryIntoDatabase() {
        if (book.getId() <= 0) {
            throw new UncorrectIDException();
        } else if (book.getTitle().equals("") || book.getTitle() == null ||
                book.getAuthor().equals("") || book.getAuthor() == null ||
                String.valueOf(book.getIsbn()).length() < 10 ||
                String.valueOf(book.getYear()).length() != 4) {
            throw new UncorrectBookException();
        } else {
            bookDao.update(book);
        }
    }

    @Override
    public void run() {
        try {
            readUserInput();
            queryIntoDatabase();
            System.out.println("Poprawnie zaktualizowano książkę: " + book.toString() + " w bazie danych.");
        } catch (UncorrectBookException f) {
            System.out.println("Book wasn't written.");
        } catch (UncorrectIDException g) {
            System.out.println("ID wasn't read properly. Book not found.");
        }
    }
}
