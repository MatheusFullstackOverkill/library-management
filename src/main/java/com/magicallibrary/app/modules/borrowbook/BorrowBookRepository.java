package com.magicallibrary.app.modules.borrowbook;

import java.util.HashMap;
import java.util.List;

import com.magicallibrary.app.database.QueryStringWrapper;
import com.magicallibrary.app.database.Repository;

public class BorrowBookRepository extends Repository {
    public static BorrowBook create(BorrowBook BorrowBook) {
        return baseCreate(BorrowBook);
    }
    
    public static List<BorrowBookWithTitle> list(HashMap<String, Object> params) {
        HashMap<String, Object> newParams = new HashMap<String, Object>();

        QueryStringWrapper queryStringWrapper = new QueryStringWrapper("""
            SELECT
                b.id as id,
                b.borrowId as borrowId,
                b.bookCopyId as bookCopyId,
                cp.status as status,
                bt.name as name
            FROM BorrowBook b
            LEFT JOIN BookCopy cp ON b.bookCopyId = cp.id
            LEFT JOIN BookTitle bt ON cp.bookTitleId = bt.id
            WHERE
                b.deletedAt is null AND
                bt.deletedAt is null
        """);

        if (params != null) {
            params.forEach((key, value) -> {
                String keyValue = key;
                
                if (key.contains(".")) {
                    System.out.println(key);
                    String[] parts = key.split("\\.");
                    for (int i = 0; i < parts.length; i++) {
                        System.out.println(parts[i]);
                    }
                    System.out.println("sssssssssssssssssssssssss");
                    keyValue = parts[1];
                };

                newParams.put(keyValue, value);

                queryStringWrapper.queryString = queryStringWrapper.queryString.concat(" AND ").concat(key).concat(" = :").concat(keyValue);
            });
        };

        var results = baseList(Object[].class, queryStringWrapper, newParams);
        List<BorrowBookWithTitle> finalResults = results.stream().map(item ->
            new BorrowBookWithTitle(
                (long) item[0],
                (long) item[1],
                (long) item[2],
                (String) item[3],
                (String) item[4]
            )
        ).toList();

        return finalResults;
    }

    public static BorrowBook retrieve(long id) {
        return baseRetrieve(BorrowBook.class, id);
    }

    public static BorrowBook update(BorrowBook BorrowBook) {
        return baseUpdate(BorrowBook.class, BorrowBook);
    }

    public static BorrowBook delete(BorrowBook BorrowBook) {
        return baseDelete(BorrowBook.class, BorrowBook);
    }
}
