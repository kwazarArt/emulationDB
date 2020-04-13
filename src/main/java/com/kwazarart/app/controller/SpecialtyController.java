package main.java.com.kwazarart.app.controller;


import main.java.com.kwazarart.app.model.Specialty;
import main.java.com.kwazarart.app.repository.SpecialtyRepository;

public class SpecialtyController implements Controller {
    SpecialtyRepository specialtyRepository = new SpecialtyRepository();


    @Override
    public void create() {
        specialtyRepository.add();
    }


    @Override
    public void read(int id) {
        Specialty specialty = specialtyRepository.get(id);
        if (specialty == null) {
            specialtyRepository.notFound();
        } else {
            specialtyRepository.printByIndex(specialty);
        }
    }

    @Override
    public void readAll() {
        specialtyRepository.printAll(specialtyRepository.getAll());
    }

    @Override
    public void update(int var) {
        if (var == 0) return;
        specialtyRepository.updater(var);
    }

    @Override
    public void delete() {
        specialtyRepository.delete();
    }
}
