package com.magicallibrary.app.flows.mainmenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.magicallibrary.app.App;
import com.magicallibrary.app.modules.bookcopy.BookCopy;
import com.magicallibrary.app.modules.bookcopy.BookCopyRepository;
import com.magicallibrary.app.modules.booktitle.BookTitle;
import com.magicallibrary.app.modules.booktitle.BookTitleRepository;
import com.magicallibrary.app.modules.borrow.Borrow;
import com.magicallibrary.app.modules.borrow.BorrowRepository;
import com.magicallibrary.app.modules.borrowbook.BorrowBook;
import com.magicallibrary.app.modules.borrowbook.BorrowBookRepository;
import com.magicallibrary.app.modules.user.User;
import com.magicallibrary.app.modules.user.UserRepository;

public class BorrowBooks extends InternalFlow {
    private ArrayList<BookCopy> selectedBookTitlesCopies = new ArrayList<BookCopy>();

    private boolean searchBookTitles() {
        System.out.println("Book's Title:");
        String name = getUserInput();
        if (name == null) {
            return true;
        };

        if (name.toLowerCase().equals("end")) {
            return false;
        };

        HashMap<String, Object> params = new HashMap<String, Object>() {{
            put("name", name);
        }};

        List<BookTitle> results = BookTitleRepository.list(params);

        if (results.size() == 0) {
            System.out.println("Book Title doesn't exists");

            return searchBookTitles();
        } else {
            HashMap<String, Object> bookCopiesParams = new HashMap<String, Object>() {{
                put("bookTitleId", results.getFirst().getId());
                put("status", "available");
            }};

            List<BookCopy> bookCopiesResults = BookCopyRepository.list(bookCopiesParams);
            if (bookCopiesParams.size() == 0) {
                System.out.println("There's no available boook copy for this title");

                return searchBookTitles();
            } else {
                selectedBookTitlesCopies.add(bookCopiesResults.getFirst());

                return searchBookTitles();
            }
        }
    }

    private User searchCustomer() {
        System.out.println("Search customer by name, last name or email");
        String search = getUserInput();

        HashMap<String, Object> searchParams = new HashMap<String, Object>() {{
            put("search", search);
            put("usertype", "customer");
        }};

        List<User> results = UserRepository.list(searchParams);

        if (results.size() == 0) {
            return createCustomer();
        } else {
            System.out.println("Founded customers, select one option: \n");
            for (int index = 0; index < results.size(); index++) {
                System.out.println(String.format("%d. name: %s, email: %s", (index + 1), results.get(index).getFullname(), results.get(index).getEmail()));    
            };
            System.out.println(String.format("%d. Create user", results.size() + 1));

            int response = Integer.parseInt(getUserInput());

            if (response > results.size() + 1 || response <= 0) {
                System.out.println("Invalid choice");

                return searchCustomer();
            } else if (response == results.size() + 1) {
                return createCustomer();
            } {
                return results.get(response - 1);
            }
        }
    }

    private boolean validateExistingCustomer(String email) {
        HashMap<String, Object> searchParams = new HashMap<String, Object>() {{
            put("email", email);
            put("usertype", "customer");
        }};

        List<User> results = UserRepository.list(searchParams);

        return results.size() > 0;
    }

    private User createCustomer() {
        System.out.println("Email");
        String email = getUserInput();

        if (validateExistingCustomer(email)) {
            System.out.println("User already exists");

            return createCustomer();
        };

        System.out.println("First name");
        String firstName = getUserInput();

        System.out.println("Last name");
        String lastName = getUserInput();

        User newCustomer = new User(firstName, lastName, email, "customer", "");
        User newCreatedCustomer = UserRepository.create(newCustomer);

        return newCreatedCustomer;
    }

    public boolean start() {
        // search booktitle;
        // get borrowed bookcopies (any status other then "borrowed") and limit by 1;
        // add. then to an list until user exit the recursive method;
        boolean exited = searchBookTitles();
        if (exited) {
            return true;
        };

        // search customer by name or email;
        // create one if don't find it;
        // create borrow and loop through BookCopies and create BorrowBook with status as "borrowed";
        User customer = searchCustomer();

        Borrow newBorrow = new Borrow(customer.getId(), App.currentUser.getId(), "all_books_borrowed");
        Borrow newCreatedBorrow = BorrowRepository.create(newBorrow);

        for (int i = 0; i < selectedBookTitlesCopies.size(); i++) {
            BorrowBookRepository.create(new BorrowBook(newCreatedBorrow.getId(), selectedBookTitlesCopies.get(i).getBookCopyId()));

            BookCopy bookCopy = BookCopyRepository.retrieve(selectedBookTitlesCopies.get(i).getBookCopyId());
            bookCopy.setStatus("borrowed");
            BookCopyRepository.update(bookCopy);
        };

        System.out.println("Borrow created successfully!");

        return true;
    };
}
