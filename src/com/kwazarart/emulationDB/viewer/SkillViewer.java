package com.kwazarart.emulationDB.viewer;

import com.kwazarart.emulationDB.model.Skill;

import java.util.List;

public class SkillViewer implements Viewer<Skill> {

    @Override
    public void printByIndex(Skill skill) {
        System.out.println(skill);
    }

    @Override
    public void printAll(List<Skill> list) {
        for (Skill skill : list) {
            System.out.println(skill);
        }
    }

    @Override
    public void notFound() {
        System.out.println("Skill doesn't found");
    }



}
