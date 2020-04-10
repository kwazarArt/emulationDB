package com.kwazarart.emulationDB.model;

import com.kwazarart.emulationDB.InputOutput.*;
import com.kwazarart.emulationDB.repository.SpecialtyRepository;

import java.util.ArrayList;
import java.util.List;

import static com.kwazarart.emulationDB.InputOutput.Searcher.searchByIndex;

public class SpecialtyCrud implements CommonCrud<Specialty> {
    SpecialtyRepository specialtyRepository = new SpecialtyRepository();
    String path = SpecialtyRepository.getPath();

    @Override
    public Specialty add() {
        System.out.print("Enter specialty name: ");
        Specialty specialty;
        specialty = new Specialty(
                Searcher.searchMaxIndex(path) + 1,
                InputByUser.inputData(),
                Status.ACTIVE
        );
        if (!Checker.containsSpecialty(specialty)) {
            Writer.write(path, specialty.toString());
            specialtyRepository.addSpecialty(specialty);
        }
        return specialty;
    }

    @Override
    public Specialty get() {
        int id = InputByUser.inputInt();
        if (id <= 0) return null;
        String line = searchByIndex(path, id);
        return stringToSpecialty(line);
    }

    @Override
    public List<Specialty> getAll() {
        List<String> allList = Reader.read(path);
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
        List<String> list = Reader.read(path);
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
        Writer.rewrite(path, buffer);
    }

    @Override
    public void delete() {
        int id;
        String specialty;
        while (true) {
            id = InputByUser.inputInt();
            if (id <= 0) return;
            specialty = searchByIndex(path, id);
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
