package com.kwazarart.emulationDB.controller;

import com.kwazarart.emulationDB.model.Specialty;
import com.kwazarart.emulationDB.model.SpecialtyCrud;
import com.kwazarart.emulationDB.viewer.SpecialtyViewer;

import java.util.Scanner;

public class SpecialtyController implements Controller {
    SpecialtyCrud specialtyCrud = new SpecialtyCrud();
    SpecialtyViewer viewer = new SpecialtyViewer();

    @Override
    public void create() {
        specialtyCrud.add();
    }

    @Override
    public void read() {
        Specialty specialty = specialtyCrud.get();
        if (specialty == null) {
            viewer.notFound();
        } else {
            viewer.printByIndex(specialty);
        }
    }

    @Override
    public void readAll() {
        viewer.printAll(specialtyCrud.getAll());
        System.out.println("\n\n");;
    }

    @Override
    public void update() {
        System.out.println("What do you need to change?");
        String variant;
        while (true) {
            System.out.println("\n\t1 - specialty name");
            System.out.println("\t2 - status");
            System.out.println("\t0 - Exit");
            System.out.print("Enter variant: ");
            Scanner sc = new Scanner(System.in);
            variant = sc.nextLine();
            if (variant.equals("0")) return;
            if (variant.equals("1")) {
                specialtyCrud.updater(1);
            } else if (variant.equals("2")) {
                specialtyCrud.updater(2);
            } else {
                System.out.println("\nWrong choice. Try again.\n");
            }
        }
    }

    @Override
    public void delete() {
        specialtyCrud.delete();
    }
}
