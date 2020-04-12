package com.kwazarart.emulationdb.controller;

import com.kwazarart.emulationdb.model.Developer;
import com.kwazarart.emulationdb.repository.DeveloperRepository;
import com.kwazarart.emulationdb.viewer.DeveloperViewer;
import com.kwazarart.emulationdb.viewer.Viewer;

public class DeveloperController implements Controller {
    DeveloperRepository developerRepository = new DeveloperRepository();
    Viewer<Developer> viewer = new DeveloperViewer();

    @Override
    public void create() {
        developerRepository.add();
    }

    @Override
    public void read() {
        Developer developer = developerRepository.get();
        if (developer == null) {
            viewer.notFound();
        } else {
            viewer.printByIndex(developer);
        }
    }

    @Override
    public void readAll() {
        viewer.printAll(developerRepository.getAll());
    }

    @Override
    public void update() {
        int var = viewer.viewUpdate();
        if (var == 0) return;
        developerRepository.updater(var);
    }

    @Override
    public void delete() {
        developerRepository.delete();
    }
}
