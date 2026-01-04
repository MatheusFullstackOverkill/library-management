package com.magicallibrary.app.modules.borrowbook;

public class BorrowBookWithTitle {
    public long id;
    public long borrowId;
    public long bookCopyId;
    public String status;
    public String name;

    public BorrowBookWithTitle(
        long id,
        long borrowId,
        long bookCopyId,
        String status,
        String name
    ) {
        this.id = id;
        this.borrowId = borrowId;
        this.bookCopyId = bookCopyId;
        this.status = status;
        this.name = name;
    }
}
