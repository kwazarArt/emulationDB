package com.kwazarart.emulationdb.controller;

import com.kwazarart.emulationdb.model.Skill;
import com.kwazarart.emulationdb.repository.SkillRepository;
import com.kwazarart.emulationdb.viewer.SkillViewer;
import com.kwazarart.emulationdb.viewer.Viewer;

public class SkillController implements Controller{
    SkillRepository skillRepository = new SkillRepository();
    Viewer<Skill> viewer = new SkillViewer();

    @Override
    public void create() {
        skillRepository.add();
    }

    @Override
    public void read() {
        Skill skill = skillRepository.get();
        if (skill == null) {
            viewer.notFound();
        } else {
            viewer.printByIndex(skill);
        }
    }

    @Override
    public void readAll() {
        viewer.printAll(skillRepository.getAll());
    }

    @Override
    public void update() {
        int var = viewer.viewUpdate();
        if (var == 0) return;
        skillRepository.updater(var);
    }

    @Override
    public void delete() {
        skillRepository.delete();
    }
}
