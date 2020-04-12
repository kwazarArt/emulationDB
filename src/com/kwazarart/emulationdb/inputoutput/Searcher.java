package com.kwazarart.emulationdb.inputoutput;

import com.kwazarart.emulationdb.model.Skill;

import java.util.List;

public class Searcher {
    public static int searchMaxIndex(String path) {
        List<String> list = Reader.read(path);
        int maxIndex = 0;
        for (int i = 0; i < list.size(); i++) {
            int currentIndex = Integer.parseInt(list.get(i).split(",")[0]);
            if (maxIndex < currentIndex) {
                maxIndex = currentIndex;
            }
        }
        return maxIndex;
    }
    
    public static String searchByIndex(String path, int index) {
        List<String> list = Reader.read(path);
        for (int i = 0; i < list.size(); i++) {
            if (Integer.parseInt(list.get(i).split(",")[0]) == index) {
                return list.get(i);
            }
        }
        return "Not found";
    }

    public static Skill searchSkillIntoListSkillByIndex(List<Skill> list, int id) {
        for (Skill skill : list) {
            if (skill.getId() == id) {
                return skill;
            }
        }
        return null;
    }
}
