package com.kwazarart.emulationDB.viewer;

import com.kwazarart.emulationDB.model.Developer;

import java.util.List;

public class DeveloperViewer implements Viewer<Developer> {

    @Override
    public void printByIndex(Developer developer) {
        System.out.println(developer);
    }

    @Override
    public void printAll(List<Developer> list) {
        for(Developer developer : list) {
            System.out.println(developer);
        }
    }

    @Override
    public void notFound() {
        System.out.println("Skill doesn't found");
    }
}
