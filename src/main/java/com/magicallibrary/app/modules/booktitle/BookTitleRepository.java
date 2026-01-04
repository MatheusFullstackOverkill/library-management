package com.magicallibrary.app.modules.booktitle;

import java.util.HashMap;
import java.util.List;

import com.magicallibrary.app.database.QueryStringWrapper;
import com.magicallibrary.app.database.Repository;

public class BookTitleRepository extends Repository {
    public static BookTitle create(BookTitle bookTitle) {
        return baseCreate(bookTitle);
    }
    
    public static List<BookTitle> list(HashMap<String, Object> params) {
        QueryStringWrapper queryStringWrapper = new QueryStringWrapper("FROM BookTitle WHERE deletedAt is null");

        if (params != null) {
            params.forEach((key, value) -> {
                queryStringWrapper.queryString = queryStringWrapper.queryString.concat(" AND ").concat(key).concat(" = :").concat(key);
            });
        };
    
        return baseList(BookTitle.class, queryStringWrapper, params);
    }

    public static BookTitle retrieve(long id) {
        return baseRetrieve(BookTitle.class, id);
    }

    public static BookTitle update(BookTitle bookTitle) {
        return baseUpdate(BookTitle.class, bookTitle);
    }

    public static BookTitle delete(BookTitle bookTitle) {
        return baseDelete(BookTitle.class, bookTitle);
    }
}
