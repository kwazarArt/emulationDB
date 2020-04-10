package com.kwazarart.emulationDB.viewer;

import java.util.Scanner;

public class MainMenu {
    public static int viewMainMenu() {
        String select;
        int x;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Main menu:");
            System.out.println("\t1 - Developer");
            System.out.println("\t2 - Skill");
            System.out.println("\t3 - Specialty");
            System.out.println("\t0 - Exit");
            System.out.println("Select variant:");
            select = scanner.nextLine();
            if (select.equals("0")) return 0;
            if (select.equals("1") || select.equals("2") || select.equals("3")) {
                x = Integer.parseInt(select);
                break;
            } else {
                System.out.println("Wrong variant. Try again.\n\n");
            }
        }
        return x;
    }
}
