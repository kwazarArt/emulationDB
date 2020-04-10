package com.kwazarart.emulationDB.repository;

import java.util.List;

public interface Loader<E> {
    List<E> loadList(List<String> listString);
}
