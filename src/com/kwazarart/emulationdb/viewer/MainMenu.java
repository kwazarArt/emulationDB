package com.kwazarart.emulationdb.viewer;

import com.kwazarart.emulationdb.model.Developer;
import com.kwazarart.emulationdb.model.Skill;
import com.kwazarart.emulationdb.model.Specialty;

import java.util.Scanner;

public class MainMenu {
    public static void viewMainMenu() {
        Viewer<Skill> viewerSkill = SkillViewer.getSkillViewer();
        Viewer<Specialty> viewerSpecialty = SpecialtyViewer.getSpecialtyViewer();
        Viewer<Developer> viewerDeveloper = DeveloperViewer.getSpecialtyViewer();

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
            if (select.equals("0")) return;
            switch (select) {
                case "1":
                    viewerDeveloper.viewInnerMenu();
                    break;
                case "2":
                    viewerSkill.viewInnerMenu();
                    break;
                case "3":
                    viewerSpecialty.viewInnerMenu();
                    break;
                default:
                    System.out.println("Wrong variant. Try again.\n\n");
                    break;
            }
        }
    }
}
