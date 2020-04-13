package com.kwazarart.emulationdb.controller;

import com.kwazarart.emulationdb.model.Skill;
import com.kwazarart.emulationdb.repository.SkillRepository;

public class SkillController implements Controller{
    SkillRepository skillRepository = new SkillRepository();

    @Override
    public void create() {
        skillRepository.add();
    }

    @Override
    public void read(int id) {
        Skill skill = skillRepository.get(id);
        if (skill == null) {
            skillRepository.notFound();
        } else {
            skillRepository.printByIndex(skill);
        }
    }

    @Override
    public void readAll() {
        skillRepository.printAll(skillRepository.getAll());
    }

    @Override
    public void update(int var) {
        if (var == 0) return;
        skillRepository.updater(var);
    }

    @Override
    public void delete() {
        skillRepository.delete();
    }
}
