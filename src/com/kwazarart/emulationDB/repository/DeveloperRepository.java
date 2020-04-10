package com.kwazarart.emulationDB.repository;

import com.kwazarart.emulationDB.InputOutput.Reader;
import com.kwazarart.emulationDB.model.Developer;
import com.kwazarart.emulationDB.model.DeveloperCrud;

import java.util.ArrayList;
import java.util.List;

public class DeveloperRepository implements Loader<Developer> {
    private static final String path = "Developers.csv";

    List<Developer> listDeveloper = loadList(Reader.read(getPath()));

    public static String getPath() {
        return path;
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
            listDeveloper.add(DeveloperCrud.stringToDeveloper(line));
        }
        return listDeveloper;
    }
}
