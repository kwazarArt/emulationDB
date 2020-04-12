package com.kwazarart.emulationdb.controller;

import com.kwazarart.emulationdb.model.Specialty;
import com.kwazarart.emulationdb.repository.SpecialtyRepository;
import com.kwazarart.emulationdb.viewer.SpecialtyViewer;

public class SpecialtyController implements Controller {
    SpecialtyRepository specialtyRepository = new SpecialtyRepository();
    SpecialtyViewer viewer = new SpecialtyViewer();

    @Override
    public void create() {
        specialtyRepository.add();
    }

    @Override
    public void read() {
        Specialty specialty = specialtyRepository.get();
        if (specialty == null) {
            viewer.notFound();
        } else {
            viewer.printByIndex(specialty);
        }
    }

    @Override
    public void readAll() {
        viewer.printAll(specialtyRepository.getAll());
    }

    @Override
    public void update() {
        int var = viewer.viewUpdate();
        if (var == 0) return;
        specialtyRepository.updater(var);
    }

    @Override
    public void delete() {
        specialtyRepository.delete();
    }
}
