package com.kwazarart.emulationDB.controller;

import com.kwazarart.emulationDB.model.Developer;
import com.kwazarart.emulationDB.model.DeveloperCrud;
import com.kwazarart.emulationDB.viewer.DeveloperViewer;
import com.kwazarart.emulationDB.viewer.Viewer;

import java.util.Scanner;

public class DeveloperController implements Controller {
    DeveloperCrud developerCrud = new DeveloperCrud();
    Viewer<Developer> viewer = new DeveloperViewer();

    @Override
    public void create() {
        developerCrud.add();
    }

    @Override
    public void read() {
        Developer developer = developerCrud.get();
        if (developer == null) {
            viewer.notFound();
        } else {
            viewer.printByIndex(developer);
        }
    }

    @Override
    public void readAll() {
        viewer.printAll(developerCrud.getAll());
        System.out.println("\n\n");;
    }

    @Override
    public void update() {
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
            if (variant.equals("0")) return;
            if (variant.equals("1")) {
                developerCrud.updater(1);
            } else if (variant.equals("2")) {
                developerCrud.updater(2);
            } else if (variant.equals("3")) {
                developerCrud.updater(3);
            } else if (variant.equals("4")) {
                developerCrud.updater(4);
            } else if (variant.equals("5")) {
                developerCrud.updater(5);
            } else {
                System.out.println("\nWrong choice. Try again.\n");
            }
        }
    }

    @Override
    public void delete() {
        developerCrud.delete();
    }
}
