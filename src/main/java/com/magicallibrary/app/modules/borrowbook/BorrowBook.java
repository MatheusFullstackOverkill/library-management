package com.magicallibrary.app.modules.borrowbook;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "borrow_book", schema = "public")
public class BorrowBook {
    @Id
    @Column(name = "borrow_book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "borrow_id")
    private long borrowId;

    @Column(name = "book_copy_id")
    private long bookCopyId;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public BorrowBook() {}

    public BorrowBook(
        long borrowId,
        long bookCopyId
    ) {
        this.borrowId = borrowId;
        this.bookCopyId = bookCopyId;
    }

    public long getId() {
        return this.id;
    }

    public long getBorrowId() {
        return this.borrowId;
    }

    public long getBookCopyId() {
        return this.bookCopyId;
    }
}
