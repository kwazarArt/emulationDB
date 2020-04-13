package com.kwazarart.app.repository;

import com.kwazarart.app.inputoutput.*;

import com.kwazarart.app.model.Specialty;
import com.kwazarart.app.model.Status;

import java.util.ArrayList;

import java.util.List;
import java.util.logging.Logger;

public class SpecialtyRepository implements Loader<Specialty>, Repository {
    Logger log = Logger.getLogger(DeveloperRepository.class.getName());
    private static final String PATH = "C:\\Users\\user\\Desktop\\emulationdb\\src\\main\\java\\com\\kwazarart\\app\\resources\\Specialties.csv";

    List<Specialty> listSpecialty = loadList(Reader.read(getPath()));

    public List<Specialty> getListSpecialty() {
        logging("getListSpecialty");
        return listSpecialty;
    }

    public void addSpecialty(Specialty specialty) {
        logging("addSpecialty");
        if (!listSpecialty.contains(specialty))
            this.listSpecialty.add(specialty);
    }

    public String getPath() {
        logging("getPath");
        return PATH;
    }

    @Override
    public List<Specialty> loadList(List<String> listString) {
        logging("loadList");
        List<Specialty> listSpecialty = new ArrayList<>();
        for (String line : listString) {
            listSpecialty.add(stringToSpecialty(line));
        }
        return listSpecialty;
    }

    @Override
    public Specialty add() {
        logging("add");
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
    public Specialty get(int id) {
        logging("get");
        String line = Searcher.searchByIndex(PATH, id);
        return stringToSpecialty(line);
    }

    @Override
    public List<Specialty> getAll() {
        logging("getAll");
        List<String> allList = Reader.read(PATH);
        List<Specialty> listSpecialty = new ArrayList<>();
        for (String line : allList) {
            listSpecialty.add(stringToSpecialty(line));
        }
        return listSpecialty;
    }

    @Override
    public void updater(int variant) {
        logging("updater");
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
        logging("delete");
        int id;
        String specialty;
        while (true) {
            id = InputByUser.inputInt();
            if (id <= 0) return;
            specialty = Searcher.searchByIndex(PATH, id);
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
