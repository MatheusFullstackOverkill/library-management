package com.magicallibrary.app.flows;

import java.util.HashMap;

import com.magicallibrary.app.App;
import com.magicallibrary.app.modules.user.User;
import com.magicallibrary.app.modules.user.UserRepository;
import com.magicallibrary.app.modules.user.exceptions.TooManyLoginAttemptsException;

public class Auth {
    private int errorsCount = 0;

    private String validateEmail() throws TooManyLoginAttemptsException {
        boolean emailValidated = false;
        String email = null;

        System.out.println("Email:");
        email = App.scanner.nextLine();
        System.out.println();

        if (email.length() > 0) {
            emailValidated = true;
        };

        if (emailValidated) {
            return email;
        } else {
            if (this.errorsCount >= 2) {
                throw new TooManyLoginAttemptsException("Invalid Email");
            };

            System.out.println("Invalid Email");
            this.errorsCount += 1;
            return validateEmail();
        }
    }

    private String validatePassword() throws TooManyLoginAttemptsException {
        boolean passwordValidated = false;
        String password = null;

        System.out.println("Password:");
        password = App.scanner.nextLine();
        System.out.println();

        if (password.length() > 0) {
            passwordValidated = true;
        };

        if (passwordValidated) {
            return password;
        } else {
            if (this.errorsCount >= 2) {
                throw new TooManyLoginAttemptsException("Invalid Passoword");
            };

            System.out.println("Invalid Passoword");
            this.errorsCount += 1;
            return validatePassword();
        }
    }

    private User validateUser(String email, String password) throws TooManyLoginAttemptsException {
        var params = new HashMap<String, Object>() {{
            put("email", email);
            put("password", password);
        }};
        var users = UserRepository.list(params);

        if (users.size() > 0) {
            return users.getFirst();
        } else {
            if (this.errorsCount >= 2) {
                throw new TooManyLoginAttemptsException("User with the email and password not found.");
            };

            System.out.println("Invalid user");
            this.errorsCount += 1;
            return start();
        }
    }

    public User start() throws TooManyLoginAttemptsException {
        String email = validateEmail();
        String password = validatePassword();
        User user = validateUser(email, password);

        return user;
    }
}
