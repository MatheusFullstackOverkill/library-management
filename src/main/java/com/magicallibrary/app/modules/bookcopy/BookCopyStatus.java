package com.magicallibrary.app.modules.bookcopy;

public enum BookCopyStatus {
    AVAILABLE("available"),
    BORROWED("borrowed");

    public final String value;

    private BookCopyStatus(String value) {
        this.value = value;
    };

    public String getValue() {
        return this.value;
    };
}