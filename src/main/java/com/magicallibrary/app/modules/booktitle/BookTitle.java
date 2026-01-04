package com.magicallibrary.app.modules.booktitle;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "book_title", schema = "public")
public class BookTitle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_title_id")
    private long id;

    private String name;
    private String author;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public BookTitle() {}

    public BookTitle(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public long getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return String.format(
            """
                Book's title: %s,
                Book's author: %s
            """, 
            this.name,
            this.author
        );
    }
}
