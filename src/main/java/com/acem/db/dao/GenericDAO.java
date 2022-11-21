package com.acem.db.dao;

import com.acem.db.model.Student;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<T,ID> {

    Boolean save(T object);

    Boolean update(T object);

    Optional<List<T>> getAll();

    Optional<T> getById(ID id);


}