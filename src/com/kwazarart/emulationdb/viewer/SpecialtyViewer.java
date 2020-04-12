package com.kwazarart.emulationdb.viewer;

import com.kwazarart.emulationdb.model.Specialty;

import java.util.List;
import java.util.Scanner;

public class SpecialtyViewer implements Viewer<Specialty> {
    @Override
    public void printByIndex(Specialty specialty) {
        System.out.println(specialty);
    }

    @Override
    public void printAll(List<Specialty> list) {
        for (Specialty specialty : list) {
            System.out.println(specialty);
        }
        System.out.println("\n");
    }

    @Override
    public void notFound() {
        System.out.println("Specialty doesn't found");
    }

    @Override
    public int viewUpdate() {
        System.out.println("What do you need to change?");
        String variant;
        while (true) {
            System.out.println("\n\t1 - specialty name");
            System.out.println("\t2 - status");
            System.out.println("\t0 - Exit");
            System.out.print("Enter variant: ");
            Scanner sc = new Scanner(System.in);
            variant = sc.nextLine();
            if (variant.equals("0")) return 0;
            if (variant.equals("1")) {
                return 1;
            } else if (variant.equals("2")) {
                return 2;
            } else {
                System.out.println("\nWrong choice. Try again.\n");
            }
        }
    }
}
