package com.kwazarart.emulationDB.repository;

import com.kwazarart.emulationDB.InputOutput.Reader;
import com.kwazarart.emulationDB.model.Skill;
import com.kwazarart.emulationDB.model.SkillCrud;

import java.util.ArrayList;
import java.util.List;

public class SkillRepository implements Loader<Skill> {
    private static final String path = "Skills.csv";
    private List<Skill> listSkill;

    public SkillRepository() {
        listSkill = loadList(Reader.read(getPath()));
    }

    public List<Skill> getList() {
        return listSkill;
    }

    public void addSkill(Skill skill) {
        listSkill.add(skill);
    }

    public String getPath() {
        return path;
    }

    @Override
    public List<Skill> loadList(List<String> listString) {
        List<Skill> listSkill = new ArrayList<>();
        for (String line : listString) {
            listSkill.add(SkillCrud.stringToSkill(line));
        }
        return listSkill;
    }
}
