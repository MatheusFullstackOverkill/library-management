package com.magicallibrary.app.modules.bookcopy;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "book_copy", schema = "public")
public class BookCopy {
    @Id
    @Column(name = "book_copy_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "book_title_id")
    private long bookTitleId;

    private String status;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public BookCopy() {}

    public BookCopy(
        long bookTitleId,
        String status
    ) {
        this.bookTitleId = bookTitleId;
        this.status = status;
    }

    public long getBookCopyId() {
        return this.id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public long getBookTitleId() {
        return this.bookTitleId;
    }
}
