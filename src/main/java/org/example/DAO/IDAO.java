package org.example.DAO;

import java.util.List;

public interface IDAO<T> {
    List<T> showAll();

    void add(T entity);

    void delete(T entity);
}
