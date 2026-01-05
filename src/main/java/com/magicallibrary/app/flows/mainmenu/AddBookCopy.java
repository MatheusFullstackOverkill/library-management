package com.magicallibrary.app.flows.mainmenu;

import java.util.HashMap;
import java.util.List;

import com.magicallibrary.app.modules.bookcopy.BookCopy;
import com.magicallibrary.app.modules.bookcopy.BookCopyRepository;
import com.magicallibrary.app.modules.booktitle.BookTitle;
import com.magicallibrary.app.modules.booktitle.BookTitleRepository;

public class AddBookCopy extends InternalFlow {

    private BookTitle validateBookTitle() {
        System.out.println("Book's Title:");
        String name = getUserInput();
        if (name == null) {
            return null;
        };

        HashMap<String, Object> params = new HashMap<String, Object>() {{
            put("name", name);
        }};

        List<BookTitle> results = BookTitleRepository.list(params);

        if (results.size() == 0) {
            System.out.println("Book Title doens't exists, do you want to create it?");
            System.out.println("""
                1. Yes;
                2. No;
            """);
            Integer response = Integer.parseInt(getUserInput());

            if (response == 1) {
                return createBookTitle(name);
            };

            return validateBookTitle();
        } else {
            return results.getFirst();
        }
    }

    private BookTitle createBookTitle(String name) {
        if (name == null) {
            System.out.println("Book's Title:");
            name = getUserInput();
            if (name == null) {
                return null;
            };
        };

        System.out.println("Book's author");
        String author = getUserInput();
        if (author == null) {
            return null;
        };

        BookTitle bookTitle = BookTitleRepository.create(new BookTitle(name, author));
        System.out.println("Book Title successfully created!");

        return bookTitle;
    }

    @Override
    public boolean start() {
        BookTitle bookTitle = validateBookTitle();
        if (bookTitle == null) {
            return true;
        };

        BookCopyRepository.create(new BookCopy(bookTitle.getId(), "available"));
        System.out.println("Book Copy successfully created!");

        return true;
    }
}
