package com.kwazarart.app.viewer;

import com.kwazarart.app.controller.SpecialtyController;
import com.kwazarart.app.inputoutput.InputByUser;
import com.kwazarart.app.model.Specialty;


import java.util.Scanner;

public class SpecialtyViewer implements Viewer<Specialty> {

    public static SpecialtyViewer specialtyViewer;
    SpecialtyController specialtyController = new SpecialtyController();

    private SpecialtyViewer(){}

    public static SpecialtyViewer getSpecialtyViewer () {
        if (specialtyViewer == null) {
            specialtyViewer = new SpecialtyViewer();
        }
        return specialtyViewer;
    }

    @Override
    public void veiwAdd() {
        System.out.print("Enter specialty name: ");
        specialtyController.create();
    }

    @Override
    public void viewGet() {
        int id = InputByUser.inputInt();
        if (id <= 0) return;
        specialtyController.read(id);
    }

    @Override
    public void viewGetAll() {
        specialtyController.readAll();
    }

    @Override
    public void viewDelete() {
        specialtyController.delete();
    }

    @Override
    public void viewUpdate() {
        System.out.println("What do you need to change?");
        String variant;
        while (true) {
            System.out.println("\n\t1 - skill name");
            System.out.println("\t2 - status");
            System.out.println("\t0 - Exit");
            System.out.print("Enter variant: ");
            Scanner sc = new Scanner(System.in);
            variant = sc.nextLine();
            if (variant.equals("0")) { return; }
            if (variant.equals("1")) { specialtyController.update(1);}
            else if (variant.equals("2")) { specialtyController.update(2);}
            else { System.out.println("\nWrong choice. Try again.\n"); }
        }
    }
}
