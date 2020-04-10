package com.kwazarart.emulationDB.controller;

import com.kwazarart.emulationDB.model.Skill;
import com.kwazarart.emulationDB.model.SkillCrud;
import com.kwazarart.emulationDB.viewer.SkillViewer;
import com.kwazarart.emulationDB.viewer.Viewer;

import java.util.Scanner;

public class SkillController implements Controller{
    SkillCrud skillCrud = new SkillCrud();
    Viewer<Skill> viewer = new SkillViewer();

    @Override
    public void create() {
        skillCrud.add();
    }

    @Override
    public void read() {
        Skill skill = skillCrud.get();
        if (skill == null) {
            viewer.notFound();
        } else {
            viewer.printByIndex(skill);
        }
    }

    @Override
    public void readAll() {
        viewer.printAll(skillCrud.getAll());
        System.out.println("\n\n");;
    }

    @Override
    public void update() {
        System.out.println("What do you need to change?");
        String variant;
        while (true) {
            System.out.println("\n\t1 - skill name");
            System.out.println("\t2 - status");
            System.out.println("\t0 - Exit");
            System.out.print("Enter variant: ");
            Scanner sc = new Scanner(System.in);
            variant = sc.nextLine();
            if (variant.equals("0")) return;
            if (variant.equals("1")) {
                skillCrud.updater(1);
            } else if (variant.equals("2")) {
                skillCrud.updater(2);
            } else {
                System.out.println("\nWrong choice. Try again.\n");
            }
        }
    }

    @Override
    public void delete() {
        skillCrud.delete();
    }
}
