package com.kwazarart.emulationdb.viewer;

import com.kwazarart.emulationdb.controller.DeveloperController;
import com.kwazarart.emulationdb.inputoutput.InputByUser;
import com.kwazarart.emulationdb.model.Developer;

import java.util.Scanner;

public class DeveloperViewer implements Viewer<Developer> {
    DeveloperController developerController = new DeveloperController();
    public static DeveloperViewer developerViewer;

    private DeveloperViewer(){}

    public static DeveloperViewer getSpecialtyViewer () {
        if (developerViewer == null) {
            developerViewer = new DeveloperViewer();
        }
        return developerViewer;
    }

    @Override
    public void veiwAdd() {
        developerController.create();
    }

    @Override
    public void viewGet() {
        int id = InputByUser.inputInt();
        if (id <= 0) return;
        developerController.read(id);
    }

    @Override
    public void viewGetAll() {
        developerController.readAll();
    }

    @Override
    public void viewDelete() {
        developerController.delete();
    }

    public void viewUpdate() {
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
            switch (variant) {
                case "1":
                    developerController.update(1);
                    break;
                case "2":
                    developerController.update(2);
                    break;
                case "3":
                    developerController.update(3);
                    break;
                case "4":
                    developerController.update(4);
                    break;
                case "5":
                    developerController.update(5);
                    break;
                default:
                    System.out.println("\nWrong choice. Try again.\n");
            }
        }
    }
}
