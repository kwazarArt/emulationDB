package com.kwazarart.emulationdb.viewer;

import com.kwazarart.emulationdb.model.Developer;

import java.util.List;
import java.util.Scanner;

public class DeveloperViewer implements Viewer<Developer> {

    @Override
    public void printByIndex(Developer developer) {
        System.out.println(developer);
    }

    @Override
    public void printAll(List<Developer> list) {
        for(Developer developer : list) {
            System.out.println(developer);
        }
        System.out.println("\n");
    }

    @Override
    public void notFound() {
        System.out.println("Skill doesn't found");
    }

    public int viewUpdate() {
        System.out.println("What do you need to change?");
        String variant;
        while (true) {
            System.out.println("\n\t1 - First name");
            System.out.println("\t2 - Last name");
            System.out.println("\t3 - Specialty");
            System.out.println("\t4 - Skill list");
            System.out.println("\t5 - Status");
            System.out.println("\t0 - Exit");
            System.out.print("Enter variant: ");
            Scanner sc = new Scanner(System.in);
            variant = sc.nextLine();
            if (variant.equals("0")) return 0;
            if (variant.equals("1")) {
                return 1;
            } else if (variant.equals("2")) {
                return 2;
            } else if (variant.equals("3")) {
                return 3;
            } else if (variant.equals("4")) {
                return 4;
            } else if (variant.equals("5")) {
                return 5;
            } else {
                System.out.println("\nWrong choice. Try again.\n");
            }
        }
    }
}
