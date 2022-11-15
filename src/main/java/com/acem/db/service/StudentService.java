package com.acem.db.service;

import com.acem.db.model.Student;
import com.acem.db.response.Response;


public interface StudentService {

    Response getAll();

    Response getById(Long id);

    Response getByEmailAddress(String emailAddress);

    Response getByContactNo(String contactNo);

    Response save(Student student);

    Response update(Student student);

    Response delete(Long id);
}
