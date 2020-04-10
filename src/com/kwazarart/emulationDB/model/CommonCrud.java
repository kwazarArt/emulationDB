package com.kwazarart.emulationDB.model;

import java.util.List;

public interface CommonCrud<E> {
    E add();

    E get();

    List<E> getAll();

    void updater(int x);

    void delete();
}
