package com.kaparzh.simplecrud.view;

import com.kaparzh.simplecrud.controller.LabelController;

import java.util.Scanner;

public class LabelView {

    public static final String LABEL_MENU = "Select action:\n" +
            "1.Create label\n" +
            "2.Update label\n" +
            "3.Delete label\n" +
            "4.Get label by Id\n" +
            "5.Get list of all labels\n" +
            "0.Back to main menu\n";

    private final LabelController labelController = new LabelController();

    public void labelViewRunner(int value) {
        switch (value) {
            case 0:
                break;
            case 1:
                createLabel();
                break;
            case 2:
                updateLabel();
                break;
            case 3:
                deleteLabel();
                break;
            case 4:
                getLabelById();
                break;
            case 5:
                getAll();
                break;
        }
    }

    private void getLabelById() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter label id:");
        int id = Integer.parseInt(sc.nextLine());
        labelController.getById(id);
    }

    private void deleteLabel() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter label id for delete:");
        int id = Integer.parseInt(sc.nextLine());
        labelController.deleteById(id);
    }

    private void createLabel() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter label id");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println("Enter label name");
        String labelName = sc.nextLine();
        labelController.createLabel(id, labelName);
    }

    private void updateLabel() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter label id");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println("Enter label name");
        String labelName = sc.nextLine();
        labelController.updateLabel(id, labelName);
    }

    private void getAll() {
        labelController.getAll();
    }
}
