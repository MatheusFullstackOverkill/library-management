package com.magicallibrary.app.flows.mainmenu;

import java.util.HashMap;
import java.util.List;

import com.magicallibrary.app.App;
import com.magicallibrary.app.modules.bookcopy.BookCopy;
import com.magicallibrary.app.modules.bookcopy.BookCopyRepository;
import com.magicallibrary.app.modules.borrow.Borrow;
import com.magicallibrary.app.modules.borrow.BorrowRepository;
import com.magicallibrary.app.modules.borrowbook.BorrowBookRepository;
import com.magicallibrary.app.modules.borrowbook.BorrowBookWithTitle;
import com.magicallibrary.app.modules.user.User;

public class ReturnBooks extends InternalFlow {
    private Borrow searchBorrowsByCustomer() {
        System.out.println("Search borrow by customer's name or email:");
        String name = App.scanner.next();

        if (exitFlowValidator(name)) {
            return null;
        };

        HashMap<String, Object> searchParams = new HashMap<String, Object>() {{
            put("customerSearch", "ma");
            put("status", "all_books_borrowed");
        }};

        List<Object[]> results = BorrowRepository.teste(searchParams);

        if (results.size() == 0) {
            System.out.println("And open borrow found for this customer");

            return searchBorrowsByCustomer();
        } else {
            System.out.println("Founded open borrows, select one option:");
            for (int index = 0; index < results.size(); index++) {
                Borrow borrow = (Borrow) results.get(index)[0];
                User borrowUser = (User) results.get(index)[1];

                HashMap<String, Object> bookCopiesParams = new HashMap<String, Object>() {{
                    put("borrowId", borrow.getId());
                }};

                List<BorrowBookWithTitle> bookCopies = BorrowBookRepository.list(bookCopiesParams);
                String formattedBookCopies = String.join(", ", bookCopies.stream().map(x -> x.name).toList());

                System.out.println(
                    String.format(
                        """
                            %d. name: %s, email: %s, books: %s
                        """,
                        (index + 1), borrowUser.getFullname(), borrowUser.getEmail(), formattedBookCopies
                    )
                );  
            };

            try {
                int response = Integer.parseInt(getUserInput());

                if (response > results.size() || response <= 0) {
                    System.out.println("Invalid choice");

                    return searchBorrowsByCustomer();
                } {
                    return (Borrow) results.get(response - 1)[0];
                }
            } catch (Exception e) {
                System.out.println("Invalid choice");

                return searchBorrowsByCustomer();
            }
        }
    }

    private boolean returnBooks(Borrow borrow) {
        HashMap<String, Object> bookCopiesParams = new HashMap<String, Object>() {{
            put("borrowId", borrow.getId());
            put("status", "borrowed");
        }};

        List<BorrowBookWithTitle> bookCopies = BorrowBookRepository.list(bookCopiesParams); 
        System.out.println("Founded borrowed books, select one option:");

        for (int index = 0; index < bookCopies.size(); index++) {
            System.out.println(
                String.format(
                    """
                        %d. book: %s
                    """,
                    (index + 1), bookCopies.get(index).name
                )
            );  
        };

        try {
            int response = Integer.parseInt(getUserInput());

            if (response > bookCopies.size() || response <= 0) {
                System.out.println("Invalid choice");

                return returnBooks(borrow);
            } {
                BookCopy bookCopy = BookCopyRepository.retrieve(bookCopies.get(response - 1).bookCopyId);
                bookCopy.setStatus("available");
                BookCopyRepository.update(bookCopy);

                System.out.println("Book returned!");

                if (bookCopies.size() - 1 <= 0) {
                    borrow.setStatus("all_books_returned");
                    BorrowRepository.update(borrow);

                    System.out.println("All books have been returned!");

                    return true;
                };

                borrow.setStatus("some_books_returned");
                BorrowRepository.update(borrow);

                return returnBooks(borrow);
            }
        } catch(Exception e) {
            System.out.println("Invalid choice");

            return returnBooks(borrow);
        }
    }

    public boolean start() {
        // search borrow by customer name or email;
        Borrow borrow = searchBorrowsByCustomer();

        if (borrow == null) {
            return true;
        };

        // get the bookcopies by status "borrowed" and borrowId;
        // selected the books that you want to return;
        // loop through then and set the status to "returned" and set "returnedAt" to now();
        boolean ended = returnBooks(borrow);

        return ended;
    };
}
