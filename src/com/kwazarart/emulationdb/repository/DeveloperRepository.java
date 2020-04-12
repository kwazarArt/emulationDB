package com.kwazarart.emulationdb.repository;

import com.kwazarart.emulationdb.inputoutput.*;
import com.kwazarart.emulationdb.model.Developer;
import com.kwazarart.emulationdb.model.Skill;
import com.kwazarart.emulationdb.model.Specialty;
import com.kwazarart.emulationdb.model.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.kwazarart.emulationdb.inputoutput.Searcher.searchByIndex;

public class DeveloperRepository implements Loader<Developer>, CommonCrud {
    private static final String PATH = "src/com/kwazarart/emulationdb/resources/Developers.csv";
    SkillRepository skillRepository = new SkillRepository();
    SpecialtyRepository specialtyRepository = new SpecialtyRepository();

    List<Developer> listDeveloper = loadList(Reader.read(getPath()));

    public static String getPath() {
        return PATH;
    }

    public List<Developer> getList() {
        return listDeveloper;
    }

    public void addDeveloper(Developer skill) {
        listDeveloper.add(skill);
    }

    @Override
    public List<Developer> loadList(List<String> listString) {
        List<Developer> listDeveloper = new ArrayList<>();
        for (String line : listString) {
            listDeveloper.add(stringToDeveloper(line));
        }
        return listDeveloper;
    }

    @Override
    public Developer add() {
        System.out.print("Enter first name: ");
        String firstName = InputByUser.inputData();
        System.out.print("Enter last name: ");
        String lastName = InputByUser.inputData();
        Developer developer;
        Specialty specialty = specialtyRepository.add();
        developer = new Developer(
                Searcher.searchMaxIndex(PATH) + 1,
                firstName,
                lastName,
                specialty,
                new ArrayList<>(),
                Status.ACTIVE
        );
        addSkillByHandle(developer);
        if (!ValidationUtil.containsDeveloper(developer)) {
            Writer.write(PATH, developer.toString());
            addDeveloper(developer);
        }
        return developer;
    }

    @Override
    public Developer get() {
        int id = InputByUser.inputInt();
        if (id <= 0) return null;
        String line = searchByIndex(PATH, id);
        return stringToDeveloper(line);
    }

    @Override
    public List<Developer> getAll() {
        List<String> allList = Reader.read(PATH);
        List<Developer> listDevelopers = new ArrayList<>();
        for (String line : allList) {
            listDevelopers.add(stringToDeveloper(line));
        }
        return listDevelopers;
    }

    @Override
    public void updater(int variant) {
        StringBuffer buffer = new StringBuffer("id,firstName,lastName,Specialty,Skills,status\n");
        List<String> list = Reader.read(PATH);

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
            developer.setSpecialty(specialtyRepository.add());
        } else if (variant == 4) {
            printListSkill(developer);
            List<Skill> developerListSkill = developer.getSkills();
            System.out.print("Which skill do you want to change? Enter index: ");
            int indexSkill;
            Skill skill;
            Scanner sc = new Scanner(System.in);
            while(true) {
                if (ValidationUtil.isDigit(sc.nextLine())) {
                    indexSkill = sc.nextInt();
                    skill = Searcher.searchSkillIntoListSkillByIndex(developerListSkill, indexSkill);
                    break;
                } else {
                    System.out.println("Try again.");
                }
            }
            developerListSkill.remove(skill);
            skill = skillRepository.add();
            developerListSkill.add(skill);
        } else if (variant == 5) {
            if (developer.getStatus().toString().equals(Status.ACTIVE.toString())) {
                developer.setStatus(Status.DELETED);
            } else {
                developer.setStatus(Status.ACTIVE);
            }
        } else return;
        String [] line;
        for (int i = 0; i < list.size(); i++) {
            line = list.get(i).split(",");
            if (Integer.parseInt(line[0]) == id) {
                buffer.append(developer.toString());
            }
            buffer.append(list.get(i));
            buffer.append("\n");
        }
        Writer.rewrite(PATH, buffer);
    }

    @Override
    public void delete() {
        int id;
        String developer;
        while (true) {
            id = InputByUser.inputInt();
            if (id <= 0) return;
            developer = searchByIndex(PATH, id);
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
                Skill skill = skillRepository.add();
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
        List<Skill> listSkill = ValidationUtil.returnListSkill(listStringSkill);
        Specialty specialty = ValidationUtil.returnSpecialty(elements[3]);
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
