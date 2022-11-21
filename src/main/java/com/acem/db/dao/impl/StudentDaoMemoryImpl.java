package com.acem.db.dao.impl;

import com.acem.db.dao.StudentDAO;
import com.acem.db.dao.qualifier.DataSource;
import com.acem.db.dao.qualifier.DatasourceType;
import com.acem.db.model.Student;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@ApplicationScoped
@DataSource(DatasourceType.MEMORY)
public class StudentDaoMemoryImpl implements StudentDAO {

    private List<Student> studentList = new ArrayList<>();

    @Override
    public Optional<List<Student>> getAll() {
        return Optional.of(studentList);
    }

    @Override
    public Optional<Student> getById(Long id) {
        return studentList.stream().filter(student -> student.getId().compareTo(id) == 0).findFirst();
    }

    @Override
    public Optional<Student> getByEmailAddress(String emailAddress) {
        return studentList.stream().filter(student -> student.getEmail().equals(emailAddress)).findFirst();
    }

    @Override
    public Optional<Student> getByContactNo(String contactNo) {
        return studentList.stream().filter(student -> student.getContactNo().equals(contactNo)).findFirst();
    }

    @Override
    public Boolean save(Student student) {
        try {
            studentList.add(student);
            return true;
        } catch (Exception ex) {
            System.err.println("Exception: "+ ex.getMessage());
            return false;
        }
    }

    @Override
    public Boolean update(Student student) {
        try {
            Optional<Student> optionalStudent = getById(student.getId());
            if (optionalStudent.isPresent()) {
                Student memoryStudent = optionalStudent.get();
                memoryStudent.setName(student.getName());
                memoryStudent.setId(student.getId());
                memoryStudent.setContactNo(student.getContactNo());
                memoryStudent.setEmail(student.getEmail());
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.err.println("Exception: "+ ex.getMessage());
            return false;
        }
    }

    @Override
    public Boolean delete(Long id) {
        try {
            Optional<Student> optionalStudent = getById(id);
            if (optionalStudent.isPresent()) {
                Student memoryStudent = optionalStudent.get();
                studentList = studentList
                        .stream()
                        .filter(student -> student.getId().compareTo(memoryStudent.getId()) != 0)
                        .collect(Collectors.toList());
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.err.println("Exception: "+ ex.getMessage());
            return false;
        }
    }
}
