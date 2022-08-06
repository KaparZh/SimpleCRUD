package com.kaparzh.simplecrud.view;

import java.util.Scanner;

import static com.kaparzh.simplecrud.view.LabelView.LABEL_MENU;

public class ConsoleView {

    private static final String WELCOME_MESSAGE = "Welcome!\n" +
            "Select sections:\n" +
            "1.Writer\n" +
            "2.Post\n" +
            "3.Label\n" +
            "4.Exit\n";

    public void run() {
        while (true) {
            System.out.println(WELCOME_MESSAGE);
            Scanner sc = new Scanner(System.in);
            int select = sc.nextInt();
            switch (select) {
                case 1:
                    break;

                case 2:
                    break;

                case 3:
                    LabelView labelView = new LabelView();
                    System.out.println(LABEL_MENU);
                    int selectValue = sc.nextInt();
                    labelView.labelViewRunner(selectValue);
                    break;

                case 4:
                    return;
            }
        }
    }
}
