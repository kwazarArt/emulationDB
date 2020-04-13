package com.kwazarart.app.repository;

import java.util.List;

public interface Loader<E> {
    List<E> loadList(List<String> listString);
}
