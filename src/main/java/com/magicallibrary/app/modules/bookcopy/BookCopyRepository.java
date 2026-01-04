package com.magicallibrary.app.modules.bookcopy;

import java.util.HashMap;
import java.util.List;

import com.magicallibrary.app.database.QueryStringWrapper;
import com.magicallibrary.app.database.Repository;

public class BookCopyRepository extends Repository {
    public static BookCopy create(BookCopy BookCopy) {
        return baseCreate(BookCopy);
    }

    public static List<BookCopy> list(HashMap<String, Object> params) {
        QueryStringWrapper queryStringWrapper = new QueryStringWrapper("FROM BookCopy WHERE deletedAt is null");

        if (params != null) {
            params.forEach((key, value) -> {
                queryStringWrapper.queryString = queryStringWrapper.queryString.concat(" AND ").concat(key).concat(" = :").concat(key);
            });
        };
    
        return baseList(BookCopy.class, queryStringWrapper, params);
    }

    public static BookCopy retrieve(long id) {
        return baseRetrieve(BookCopy.class, id);
    }

    public static BookCopy update(BookCopy BookCopy) {
        return baseUpdate(BookCopy.class, BookCopy);
    }

    public static BookCopy delete(BookCopy BookCopy) {
        return baseDelete(BookCopy.class, BookCopy);
    }
}
