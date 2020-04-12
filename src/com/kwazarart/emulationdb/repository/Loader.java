package com.kwazarart.emulationdb.repository;

import java.util.List;

public interface Loader<E> {
    List<E> loadList(List<String> listString);
}
