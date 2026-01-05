package com.magicallibrary.app.modules.borrow;

import java.util.HashMap;
import java.util.List;

import com.magicallibrary.app.database.QueryStringWrapper;
import com.magicallibrary.app.database.Repository;

public class BorrowRepository extends Repository {
    public static Borrow create(Borrow Borrow) {
        return baseCreate(Borrow);
    }

    public static List<Object[]> list(HashMap<String, Object> params) {
        QueryStringWrapper queryStringWrapper = new QueryStringWrapper("""
            SELECT
                b,
                u
            FROM 
                Borrow b
            LEFT JOIN User u ON b.customerId = u.id
            WHERE
                b.deletedAt IS NULL AND
                u.deletedAt IS NULL
        """);

        if (params != null) {
            params.forEach((key, value) -> {
                switch (key) {
                    case "customerSearch":
                        queryStringWrapper.queryString = queryStringWrapper.queryString.concat(
                            """
                                AND (
                                    u.firstName ILIKE :customerSearch OR
                                    u.lastName ILIKE :customerSearch OR
                                    u.email ILIKE :customerSearch
                                )
                            """
                        );
                        break;
                    case "notStatus":
                        queryStringWrapper.queryString = queryStringWrapper.queryString.concat(" AND status != :notStatus");
                        break;
                    default:
                        queryStringWrapper.queryString = queryStringWrapper.queryString.concat(" AND ").concat(key).concat(" = :").concat(key);
                        break;
                };
            });
        };

        if (params.containsKey("customerSearch")) {
            params.put("customerSearch", "%" + params.get("customerSearch") + "%" );
        };
    
        return baseList(Object[].class, queryStringWrapper, params);
    }

    public static Borrow retrieve(long id) {
        return baseRetrieve(Borrow.class, id);
    }

    public static Borrow update(Borrow Borrow) {
        return baseUpdate(Borrow.class, Borrow);
    }

    public static Borrow delete(Borrow Borrow) {
        return baseDelete(Borrow.class, Borrow);
    }
}
