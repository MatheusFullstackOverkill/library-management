package com.magicallibrary.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.hibernate.Session;

import com.magicallibrary.app.database.DatabaseConnection;
import com.magicallibrary.app.flows.Auth;
import com.magicallibrary.app.flows.mainmenu.MainMenu;
import com.magicallibrary.app.modules.user.User;
import com.magicallibrary.app.modules.user.exceptions.TooManyLoginAttemptsException;

/**
 * Hello world!
 */
public class App {
    public static final Scanner scanner = new Scanner(System.in);
    // BufferedReader is another option to get user input, I choose to use this instead of Scanner
    // because Scanner a problem of not waiting for user's input sometimes, and by using BufferedReader I solved that.
    // References:
    // https://medium.com/@AlexanderObregon/reading-multiple-lines-of-input-with-java-scanner-4901bb99cbbd
    // https://stackoverflow.com/questions/7877529/java-string-scanner-input-does-not-wait-for-info-moves-directly-to-next-stateme
    public static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    public static final Session session = DatabaseConnection.hibernateSetup();
    public static User currentUser = null;

    public static void main(String[] args) {
        try {
            System.out.println("Welcome the my Magical Library!");
            System.out.println("Please, silence in the library.");

            Auth auth = new Auth();
            User user = auth.start();
            currentUser = user;

            System.out.println("Welcome " + user.getEmail() + "!");

            MainMenu mainMenu = new MainMenu();
            mainMenu.start();

        } catch(TooManyLoginAttemptsException e) {
            System.out.println("Too many login attempts, closing the library");

            return;
        };
        
        // App.session.close();
    }
}
