package com.kwazarart.emulationdb.repository;

import java.util.List;

public interface Repository<E> {
    E add();

    E get(int x);

    List<E> getAll();

    void updater(int x);

    void delete();

    default void printByIndex(E e) {
        System.out.println(e);
    }

    default void printAll(List<E> list) {
        for (E e : list) {
            System.out.println(e);
        }
        System.out.println("\n");
    }

    default void notFound() {
        System.out.println("Object doesn't found");
    }
}
