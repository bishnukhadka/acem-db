package com.acem.db.dao.impl;

import com.acem.db.dao.GenericDAO;
import com.acem.db.dao.StudentDAO;
import com.acem.db.dao.qualifier.DataSource;
import com.acem.db.dao.qualifier.DatasourceType;
import com.acem.db.model.Student;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@DataSource(DatasourceType.GENERIC_HIBERNATE)
public class StudentDAOImpl extends GenericDAOImpl<Student,Long> implements StudentDAO {

    @Override
    public Optional<Student> getByEmailAddress(String emailAddress) {
        return Optional.empty();
    }

    @Override
    public Optional<Student> getByContactNo(String contactNo) {
        return Optional.empty();
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
