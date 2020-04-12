package com.kwazarart.emulationdb.repository;

import com.kwazarart.emulationdb.inputoutput.*;
import com.kwazarart.emulationdb.model.Specialty;
import com.kwazarart.emulationdb.model.Status;

import java.util.ArrayList;
import java.util.List;

import static com.kwazarart.emulationdb.inputoutput.Searcher.searchByIndex;

public class SpecialtyRepository implements Loader<Specialty>, CommonCrud {
    private static final String PATH = "src/com/kwazarart/emulationdb/resources/Specialties.csv";

    List<Specialty> listSpecialty = loadList(Reader.read(getPath()));

    public List<Specialty> getListSpecialty() {
        return listSpecialty;
    }

    public void addSpecialty(Specialty specialty) {
        if (!listSpecialty.contains(specialty))
            this.listSpecialty.add(specialty);
    }

    public static String getPath() {
        return PATH;
    }

    @Override
    public List<Specialty> loadList(List<String> listString) {
        List<Specialty> listSpecialty = new ArrayList<>();
        for (String line : listString) {
            listSpecialty.add(stringToSpecialty(line));
        }
        return listSpecialty;
    }

    @Override
    public Specialty add() {
        System.out.print("Enter specialty name: ");
        Specialty specialty;
        specialty = new Specialty(
                Searcher.searchMaxIndex(PATH) + 1,
                InputByUser.inputData(),
                Status.ACTIVE
        );
        if (!ValidationUtil.containsSpecialty(specialty)) {
            Writer.write(PATH, specialty.toString());
            addSpecialty(specialty);
        }
        return specialty;
    }

    @Override
    public Specialty get() {
        int id = InputByUser.inputInt();
        if (id <= 0) return null;
        String line = searchByIndex(PATH, id);
        return stringToSpecialty(line);
    }

    @Override
    public List<Specialty> getAll() {
        List<String> allList = Reader.read(PATH);
        List<Specialty> listSpecialty = new ArrayList<>();
        for (String line : allList) {
            listSpecialty.add(stringToSpecialty(line));
        }
        return listSpecialty;
    }

    @Override
    public void updater(int variant) {
        int id = InputByUser.inputInt();
        if (id <= 0) return;
        StringBuffer buffer = new StringBuffer("id, specialty, status\n");
        List<String> list = Reader.read(PATH);
        for (int i = 0; i < list.size(); i++) {
            String [] neededLine = list.get(i).split(",");
            if (Integer.parseInt(neededLine[0]) == id) {
                if (variant == 1) {
                    System.out.print("Enter new specialty name: ");
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
        int id;
        String specialty;
        while (true) {
            id = InputByUser.inputInt();
            if (id <= 0) return;
            specialty = searchByIndex(PATH, id);
            if (!specialty.equals("Not found")) {
                break;
            }
        }
        if (specialty.split(",")[2].equals(Status.ACTIVE.toString())) {
            System.out.print("Are you sure? Please, again ");
            updater(2);
            System.out.println("specialty deleted.");
        } else {
            System.out.println("specialty already deleted.");
        }
    }

    public static Specialty stringToSpecialty(String line) {
        String[] elements = line.split(",");
        return new Specialty(Integer.parseInt(elements[0]), elements[1], Status.valueOf(elements[2]));
    }
}
