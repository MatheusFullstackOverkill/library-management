package com.magicallibrary.app.modules.user;

import java.util.HashMap;
import java.util.List;

import com.magicallibrary.app.database.QueryStringWrapper;
import com.magicallibrary.app.database.Repository;


public class UserRepository extends Repository {
    public static List<User> list(HashMap<String, Object> params) {
        QueryStringWrapper queryStringWrapper = new QueryStringWrapper("FROM User WHERE deletedAt is null");

        if (params != null) {
            params.forEach((key, value) -> {
                if (key.equals("search")) {
                    queryStringWrapper.queryString = queryStringWrapper.queryString.concat(
                        """
                            AND ( 
                                firstName ILIKE :search OR
                                lastName ILIKE :search OR
                                email ILIKE :search
                            )
                        """
                    );
                } else {
                    queryStringWrapper.queryString = queryStringWrapper.queryString.concat(" AND ").concat(key).concat(" = :").concat(key);
                };
            });
        };

        if (params.containsKey("search")) {
            params.put("search", "%" + params.get("search") + "%" );
        };

        return baseList(User.class, queryStringWrapper, params);
    }

    public static User create(User newUser) {
        return baseCreate(newUser);
    }

    public static User retrieve(long id) {
        return baseRetrieve(User.class, id);
    }

    public static User update(User user) {
        return baseUpdate(User.class, user);
    }

    public static User delete(User user) {
        return baseDelete(User.class, user);
    }
}
