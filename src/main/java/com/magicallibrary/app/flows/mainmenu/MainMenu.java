package com.magicallibrary.app.flows.mainmenu;

import com.magicallibrary.app.App;

public class MainMenu {
    public void start() {
        System.out.println("Choose your destiny!");
        System.out.println("""
            1. Add. book title;
            2. Add. book copy;
            3. Borrow books;
            4. Return books;
            5. Loggout;
        """);

        int selectedOption = App.scanner.nextInt();
        InternalFlow selectedInternalFlow = null;

        switch(selectedOption) {
            case 1:
                selectedInternalFlow = new AddBookTitle();
        
                break;
            case 2:
                selectedInternalFlow = new AddBookCopy();

                break;
            case 3:
                selectedInternalFlow = new BorrowBooks();
            
                break;
            case 4:
                selectedInternalFlow = new ReturnBooks();

                break;
            case 5:
                System.out.println("Bye, have a good time!");

                return;
            default:
                return;
        };

        selectedInternalFlow.start();

        start();
    }
}
