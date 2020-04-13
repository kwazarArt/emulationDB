package com.kwazarart.app.repository;


import com.kwazarart.app.inputoutput.*;
import com.kwazarart.app.model.Skill;
import com.kwazarart.app.model.Status;

import java.util.ArrayList;
import java.util.List;


public class SkillRepository implements Loader<Skill>, Repository {

    private static final String PATH = "C:\\Users\\user\\Desktop\\emulationdb\\src\\main\\java\\com\\kwazarart\\app\\resources\\Skills.csv";
    private List<Skill> listSkill  = loadList(Reader.read(getPath()));


    public List<Skill> getList() {
        logging("getList");
        return listSkill;
    }

    public void addSkill(Skill skill) {
        logging("addSkill");
        listSkill.add(skill);
    }

    public String getPath() {
        logging("getPath");
        return PATH;
    }

    @Override
    public List<Skill> loadList(List<String> listString) {
        logging("loadList");
        List<Skill> listSkill = new ArrayList<>();
        for (String line : listString) {
            listSkill.add(stringToSkill(line));
        }
        return listSkill;
    }

    @Override
    public Skill add() {
        logging("add");
        Skill skill;
        skill = new Skill(
                Searcher.searchMaxIndex(PATH) + 1,
                InputByUser.inputData(),
                Status.ACTIVE
        );
        if (!ValidationUtil.containsSkill(skill)) {
            Writer.write(PATH, skill.toString());
            addSkill(skill);
        }
        return skill;
    }

    @Override
    public Skill get(int id) {
        logging("get");
        String line = Searcher.searchByIndex(PATH, id);
        return stringToSkill(line);
    }

    @Override
    public List<Skill> getAll() {
        logging("getAll");
        List<String> allList = Reader.read(PATH);
        List<Skill> listSkill = new ArrayList<>();
        for (String line : allList) {
            listSkill.add(stringToSkill(line));
        }
        return listSkill;
    }


    @Override
    public void updater(int variant) {
        logging("updater");
        int id = InputByUser.inputInt();
        if (id <= 0) return;
        StringBuffer buffer = new StringBuffer("id, skill, status\n");
        List<String> list = Reader.read(PATH);
        for (int i = 0; i < list.size(); i++) {
            String [] neededLine = list.get(i).split(",");
            if (Integer.parseInt(neededLine[0]) == id) {
                if (variant == 1) {
                    System.out.print("Enter new skill name: ");
                    neededLine[1] = InputByUser.inputData();
                } else if (variant == 2) {
                    if (neededLine[2].equals("ACTIVE")) {
                        neededLine[2] = "DELETED";
                    } else {
                        neededLine[2] = "ACTIVE";
                    }
                }
            }
            for (int j = 0; j < neededLine.length; j++) {
                buffer.append(neededLine[j]);
                if (j != neededLine.length - 1) {
                    buffer.append(",");
                }
            }
            buffer.append("\n");
        }
        Writer.rewrite(PATH, buffer);
    }

    @Override
    public void delete() {
        logging("delete");
        int id;
        String skill;
        while (true) {
            id = InputByUser.inputInt();
            if (id <= 0) return;
            skill = Searcher.searchByIndex(PATH, id);
            if (!skill.equals("Not found")) {
                break;
            }
        }
        if (skill.split(",")[2].equals(Status.ACTIVE.toString())) {
            System.out.print("Are you sure? Please, again ");
            updater(2);
            System.out.println("Skill deleted.");
        } else {
            System.out.println("Skill already deleted.");
        }
    }

    public static Skill stringToSkill(String line) {
        String[] elements = line.split(",");
        return new Skill(Integer.parseInt(elements[0]), elements[1], Status.valueOf(elements[2]));
    }
}
