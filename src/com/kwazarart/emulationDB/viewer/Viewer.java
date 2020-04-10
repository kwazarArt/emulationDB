package com.kwazarart.emulationDB.viewer;

import java.util.List;

public interface Viewer<E> {
    void printByIndex(E e);

    void printAll(List<E> list);

    void notFound();
}
