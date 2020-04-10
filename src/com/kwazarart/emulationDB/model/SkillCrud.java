package com.kwazarart.emulationDB.model;

import com.kwazarart.emulationDB.InputOutput.*;
import com.kwazarart.emulationDB.repository.SkillRepository;

import java.util.ArrayList;
import java.util.List;

import static com.kwazarart.emulationDB.InputOutput.Searcher.searchByIndex;

public class SkillCrud implements CommonCrud {
    SkillRepository skillRepository = new SkillRepository();
    String path = skillRepository.getPath();


    @Override
    public Skill add() {
        System.out.print("Enter skill name: ");
        Skill skill;
        skill = new Skill(
                Searcher.searchMaxIndex(path) + 1,
                InputByUser.inputData(),
                Status.ACTIVE
        );
        if (!Checker.containsSkill(skill)) {
            Writer.write(path, skill.toString());
            skillRepository.addSkill(skill);
        }
        return skill;
    }

    @Override
    public Skill get() {
        int id = InputByUser.inputInt();
        if (id <= 0) return null;
        String line = searchByIndex(path, id);
        return stringToSkill(line);
    }

    @Override
    public List<Skill> getAll() {
        List<String> allList = Reader.read(path);
        List<Skill> listSkill = new ArrayList<>();
        for (String line : allList) {
            listSkill.add(stringToSkill(line));
        }
        return listSkill;
    }


    @Override
    public void updater(int variant) {
        int id = InputByUser.inputInt();
        if (id <= 0) return;
        StringBuffer buffer = new StringBuffer("id, skill, status\n");
        List<String> list = Reader.read(path);
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
        Writer.rewrite(path, buffer);
    }

    @Override
    public void delete() {
        int id;
        String skill;
        while (true) {
            id = InputByUser.inputInt();
            if (id <= 0) return;
            skill = searchByIndex(path, id);
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
