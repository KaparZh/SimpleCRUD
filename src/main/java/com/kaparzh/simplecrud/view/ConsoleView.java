package com.kaparzh.simplecrud.view;

import java.util.Scanner;

import static com.kaparzh.simplecrud.view.LabelView.LABEL_MENU;
import static com.kaparzh.simplecrud.view.PostView.POST_MENU;
import static com.kaparzh.simplecrud.view.WriterView.WRITER_MENU;

public class ConsoleView {

    private final WriterView writerView = new WriterView();
    private final LabelView labelView = new LabelView();
    private final PostView postView = new PostView();

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
                    System.out.println(WRITER_MENU);
                    int writerMenuSelectValue = sc.nextInt();
                    writerView.writerViewRunner(writerMenuSelectValue);
                    break;

                case 2:
                    System.out.println(POST_MENU);
                    int postMenuSelectValue = sc.nextInt();
                    postView.postViewRunner(postMenuSelectValue);
                    break;

                case 3:
                    System.out.println(LABEL_MENU);
                    int labelMenuSelectValue = sc.nextInt();
                    labelView.labelViewRunner(labelMenuSelectValue);
                    break;

                case 4:
                    return;
            }
        }
    }
}
