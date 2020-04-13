package com.kwazarart.emulationdb.viewer;

import java.util.Scanner;

public interface Viewer<E> {
    default void viewInnerMenu() {
        String select;
        int x;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Inner menu:");
            System.out.println("\t1 - Add");
            System.out.println("\t2 - Get");
            System.out.println("\t3 - Get all");
            System.out.println("\t4 - Update");
            System.out.println("\t5 - Delete");
            System.out.println("\t0 - Exit");
            System.out.println("Select variant:");
            select = scanner.nextLine();
            if (select.equals("0")) return;
            switch (select) {
                case "1":
                    veiwAdd();
                    break;
                case "2":
                    viewGet();
                    break;
                case "3":
                    viewGetAll();
                    break;
                case "4":
                    viewUpdate();
                    break;
                case "5":
                    viewDelete();
                    break;
                default:
                    System.out.println("Wrong variant. Try again.\n\n");
                    break;
            }
        }
    }
    void veiwAdd();

    void viewGet();

    void viewGetAll();

    void viewUpdate();

    void viewDelete();
}
