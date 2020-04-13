package main.java.com.kwazarart.app.viewer;

import main.java.com.kwazarart.app.controller.SkillController;
import main.java.com.kwazarart.app.inputoutput.InputByUser;
import main.java.com.kwazarart.app.model.Skill;

import java.util.Scanner;


public class SkillViewer implements Viewer<Skill> {

    public static SkillViewer skillViewer;
    SkillController skillController = new SkillController();

    private SkillViewer(){}

    public static SkillViewer getSkillViewer () {
        if (skillViewer == null) {
            skillViewer = new SkillViewer();
        }
        return skillViewer;
    }

    @Override
    public void veiwAdd() {
        System.out.print("Enter skill name: ");
        skillController.create();
    }

    @Override
    public void viewGet() {
        int id = InputByUser.inputInt();
        if (id <= 0) return;
        skillController.read(id);
    }

    @Override
    public void viewGetAll() {
        skillController.readAll();
    }

    @Override
    public void viewDelete() {
        skillController.delete();
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
            if (variant.equals("1")) { skillController.update(1);}
            else if (variant.equals("2")) { skillController.update(2);}
            else { System.out.println("\nWrong choice. Try again.\n"); }
        }
    }
}
