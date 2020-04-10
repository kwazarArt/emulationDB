package com.kwazarart.emulationDB.model;

import com.kwazarart.emulationDB.InputOutput.*;
import com.kwazarart.emulationDB.repository.DeveloperRepository;
import com.kwazarart.emulationDB.repository.SkillRepository;
import com.kwazarart.emulationDB.repository.SpecialtyRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.kwazarart.emulationDB.InputOutput.Searcher.searchByIndex;


public class DeveloperCrud implements CommonCrud<Developer> {
    DeveloperRepository developerRepository = new DeveloperRepository();
    SkillRepository skillRepository = new SkillRepository();
    SpecialtyRepository specialtyRepository = new SpecialtyRepository();

    SpecialtyCrud specialtyCrud = new SpecialtyCrud();
    SkillCrud skillCrud = new SkillCrud();
    String path = DeveloperRepository.getPath();


    @Override
    public Developer add() {
        System.out.print("Enter first name: ");
        String firstName = InputByUser.inputData();
        System.out.print("Enter last name: ");
        String lastName = InputByUser.inputData();
        Developer developer;
        Specialty specialty = specialtyCrud.add();
        developer = new Developer(
                Searcher.searchMaxIndex(path) + 1,
                firstName,
                lastName,
                specialty,
                new ArrayList<>(),
                Status.ACTIVE
        );
        addSkillByHandle(developer);
        if (!Checker.containsDeveloper(developer)) {
            Writer.write(path, developer.toString());
            developerRepository.addDeveloper(developer);
        }
        return developer;
    }

    @Override
    public Developer get() {
        int id = InputByUser.inputInt();
        if (id <= 0) return null;
        String line = searchByIndex(path, id);
        return stringToDeveloper(line);
    }

    @Override
    public List<Developer> getAll() {
        List<String> allList = Reader.read(path);
        List<Developer> listDevelopers = new ArrayList<>();
        for (String line : allList) {
            listDevelopers.add(stringToDeveloper(line));
        }
        return listDevelopers;
    }

    @Override
    public void updater(int variant) {
        StringBuffer buffer = new StringBuffer("id,firstName,lastName,Specialty,Skills,status\n");
        List<String> list = Reader.read(path);

        int id = InputByUser.inputInt();
        if (id <= 0) return;
        Developer developer = stringToDeveloper(Searcher.searchByIndex(DeveloperRepository.getPath(), id));
        if (variant == 1) {
            System.out.print("Enter new first name: ");
            developer.setFirstName(InputByUser.inputData());
        } else if (variant == 2) {
            System.out.print("Enter new last name: ");
            developer.setLastName(InputByUser.inputData());
        } else if (variant == 3) {
            developer.setSpecialty(specialtyCrud.add());
        } else if (variant == 4) {
            printListSkill(developer);
            List<Skill> developerListSkill = developer.getSkills();
            System.out.print("Which skill do you want to change? Enter index: ");
            int indexSkill;
            Skill skill;
            Scanner sc = new Scanner(System.in);
            while(true) {
                if (Checker.isDigit(sc.nextLine())) {
                    indexSkill = sc.nextInt();
                    skill = Searcher.searchSkillIntoListSkillByIndex(developerListSkill, indexSkill);
                    break;
                } else {
                    System.out.println("Try again.");
                }
            }
            developerListSkill.remove(skill);
            skill = skillCrud.add();
            developerListSkill.add(skill);
        } else if (variant == 5) {
            if (developer.getStatus().toString().equals(Status.ACTIVE.toString())) {
                developer.setStatus(Status.DELETED);
            } else {
                developer.setStatus(Status.ACTIVE);
            }
        }
        String [] line;
        for (int i = 0; i < list.size(); i++) {
            line = list.get(i).split(",");
            if (Integer.parseInt(line[0]) == id) {
                buffer.append(developer.toString());
            }
            buffer.append(list.get(i));
            buffer.append("\n");
        }
        Writer.rewrite(path, buffer);
    }

    @Override
    public void delete() {
        int id;
        String developer;
        while (true) {
            id = InputByUser.inputInt();
            if (id <= 0) return;
            developer = searchByIndex(path, id);
            if (!developer.equals("Not found")) {
                break;
            }
        }
        String [] elementsDeveloper = developer.split(",");
        if (elementsDeveloper[elementsDeveloper.length - 1].equals(Status.ACTIVE.toString())) {
            System.out.print("Are you sure? Please, again ");
            updater(5);
            System.out.println("Developer deleted.");
        } else {
            System.out.println("Developer already deleted.");
        }
    }



    private void addSkillByHandle(Developer developer) {
        String select;
        System.out.println("Adding skills.");
        while (true) {
            System.out.println("\t1 - add skill");
            System.out.println("\t0 - exit");
            System.out.print("Select please: ");
            Scanner sc = new Scanner(System.in);
            select = sc.nextLine();
            if (select.equals("1")) {
                Skill skill = skillCrud.add();
                skillRepository.addSkill(skill);
                developer.addSkill(skill);
                System.out.println("Else?\n");
            } else if (select.equals("0")) {
                return;
            } else {
                System.out.println("Wrong input. Try again.\n");
            }
        }
    }

    public static Developer stringToDeveloper(String line) {
        String[] elements = line.split(",");
        List<String> listStringSkill = new ArrayList<>();
        for (int i = 4; i < elements.length - 1; i++) {
            listStringSkill.add(elements[i]);
        }
        List<Skill> listSkill = Checker.returnListSkill(listStringSkill);
        Specialty specialty = Checker.returnSpecialty(elements[3]);
        return new Developer(
                Integer.parseInt(elements[0]),
                elements[1],
                elements[2],
                specialty,
                listSkill,
                Status.ACTIVE
                );
    }

    public void printListSkill(Developer developer) {
        System.out.println("Skill list by " + developer.getFirstName() +
                " " + developer.getLastName());
        List<Skill> list = developer.getSkills();
        for (Skill skill : list) {
            System.out.println(skill.getId() + " - " + skill.getName());
        }
    }
}
