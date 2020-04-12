package com.kwazarart.emulationdb.repository;

import java.util.List;

public interface CommonCrud<E> {
    E add();

    E get();

    List<E> getAll();

    void updater(int x);

    void delete();
}
