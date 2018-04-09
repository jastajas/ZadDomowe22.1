package com.example.library;


public enum UserOption {


    SAVE_BOOK(new LibrarySave(), "Zapisz książkę w bazie danych."),
    READ_BOOK(new LibraryRead(), "Pokaż wybraną książkę."),
    UPDATE_BOOK(new LibraryUpdate(), "Zaktualizuj wybraną książkę."),
    DELETE_BOOK(new LibraryDelete(), "Skasuj wybraną książkę."),
    FINISH(null, "Zakończ program.");

    private QueriesDB queriesDB;
    private String optionDescription;

    UserOption (QueriesDB queriesDB, String optionDescription){
        this.queriesDB = queriesDB;
        this.optionDescription = optionDescription;
    }

    public QueriesDB getQueriesDB() {
        return queriesDB;
    }

    @Override
    public String toString() {
        return optionDescription;
    }
}
