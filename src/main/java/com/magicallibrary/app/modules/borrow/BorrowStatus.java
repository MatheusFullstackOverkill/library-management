package com.magicallibrary.app.modules.borrow;

public enum BorrowStatus {
    ALL_BOOKS_BORROWED("all_books_borrowed"),
    SOME_BOOKS_BORROWED("some_books_borrowed"),
    ALL_BOOKS_RETURNED("all_books_returned");

    private final String value;

    BorrowStatus(String value) {
        this.value = value;
    };

    public String getValue() {
        return this.value;
    };
}