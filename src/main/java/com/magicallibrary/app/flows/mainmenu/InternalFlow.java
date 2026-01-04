package com.magicallibrary.app.flows.mainmenu;

import com.magicallibrary.app.App;

public abstract class InternalFlow {
    protected boolean exitFlowValidator(String input) {
        if (input.toLowerCase().equals("q")) {
            System.out.println("You have exited the flow");

            return true;
        };

        return false;
    };

    protected String getUserInput() {
        try {
            String input = App.input.readLine();

            if (exitFlowValidator(input)) {
                return null;
            };

            return input;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public boolean start() {
        return true;
    };
}
