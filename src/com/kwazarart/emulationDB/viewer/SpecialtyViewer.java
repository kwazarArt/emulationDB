package com.kwazarart.emulationDB.viewer;

import com.kwazarart.emulationDB.model.Specialty;

import java.util.List;

public class SpecialtyViewer implements Viewer<Specialty> {
    @Override
    public void printByIndex(Specialty specialty) {
        System.out.println(specialty);
    }

    @Override
    public void printAll(List<Specialty> list) {
        for (Specialty specialty : list) {
            System.out.println(specialty);
        }
    }

    @Override
    public void notFound() {
        System.out.println("Specialty doesn't found");
    }
}
