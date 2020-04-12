package com.kwazarart.emulationdb.viewer;

import java.util.Scanner;

public class InnerMenu {
    public static int viewInnerMenu() {
        String select;
        int x;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Main menu:");
            System.out.println("\t1 - Add");
            System.out.println("\t2 - Get");
            System.out.println("\t3 - Get all");
            System.out.println("\t4 - Update");
            System.out.println("\t5 - Delete");
            System.out.println("\t0 - Exit");
            System.out.println("Select variant:");
            select = scanner.nextLine();
            if (select.equals("0")) return 0;
            if (select.equals("1") || select.equals("2") || select.equals("3") || select.equals("4") || select.equals("5")) {
                x = Integer.parseInt(select);
                break;
            } else {
                System.out.println("Wrong variant. Try again.\n\n");
            }
        }
        return x;
    }
}
