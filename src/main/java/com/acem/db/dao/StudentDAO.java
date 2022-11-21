package com.acem.db.dao;

import com.acem.db.model.Student;

import java.util.Optional;

public interface StudentDAO extends GenericDAO<Student,Long>{

//    Optional<List<Student>> getAll();
//
//    Optional<Student> getById(Long id);
//
    Optional<Student> getByEmailAddress(String emailAddress);

    Optional<Student> getByContactNo(String contactNo);
//
//    Boolean save(Student student);
//
//    Boolean update(Student student);
//
    Boolean delete(Long id);
}
