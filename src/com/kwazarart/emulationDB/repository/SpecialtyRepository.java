package com.kwazarart.emulationDB.repository;

import com.kwazarart.emulationDB.InputOutput.Reader;
import com.kwazarart.emulationDB.model.Specialty;
import com.kwazarart.emulationDB.model.SpecialtyCrud;

import java.util.ArrayList;
import java.util.List;

public class SpecialtyRepository implements Loader<Specialty> {
    private static final String path = "Specialties.csv";

    List<Specialty> listSpecialty = loadList(Reader.read(getPath()));

    public List<Specialty> getListSpecialty() {
        return listSpecialty;
    }

    public void addSpecialty(Specialty specialty) {
        if (!listSpecialty.contains(specialty))
            this.listSpecialty.add(specialty);
    }

    public static String getPath() {
        return path;
    }

    @Override
    public List<Specialty> loadList(List<String> listString) {
        List<Specialty> listSpecialty = new ArrayList<>();
        for (String line : listString) {
            listSpecialty.add(SpecialtyCrud.stringToSpecialty(line));
        }
        return listSpecialty;
    }
}
