package com.magicallibrary.app.flows.mainmenu;

import java.util.HashMap;
import java.util.List;

import com.magicallibrary.app.modules.booktitle.BookTitle;
import com.magicallibrary.app.modules.booktitle.BookTitleRepository;

public class AddBookTitle extends InternalFlow {

    private boolean validateExistingBookTitle(String name) {
        HashMap<String, Object> params = new HashMap<String, Object>() {{
            put("name", name);
        }};

        List<BookTitle> results = BookTitleRepository.list(params);

        return results.size() > 0;
    }

    private BookTitle createBookTitle() {
        System.out.println("Book's Title:");
        String name = getUserInput();
        if (name == null) {
            return null;
        };

        if (validateExistingBookTitle(name)) {
            System.out.println("Book's title already exists");

            return createBookTitle();
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
        createBookTitle();

        return true;
    }
}
