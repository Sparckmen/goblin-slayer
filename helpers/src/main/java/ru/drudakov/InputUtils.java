package ru.drudakov;

import java.util.Scanner;

public class InputUtils {
    static Scanner scanner = new Scanner(System.in);

    public static int inputInt(int max) {
        int choice = scanner.nextInt();
        while (choice < 0 && choice > max) {
            choice = scanner.nextInt();
        }
        return choice;
    }
}
