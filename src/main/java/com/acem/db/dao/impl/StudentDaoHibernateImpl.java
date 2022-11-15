package com.acem.db.dao.impl;

import com.acem.db.config.JdbcTemplate;
import com.acem.db.constant.DbQueryConstant;
import com.acem.db.dao.StudentDao;
import com.acem.db.exception.ExceptionHandler;
import com.acem.db.mapper.impl.StudentRowMapperImpl;
import com.acem.db.model.Student;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;

public class StudentDaoHibernateImpl implements StudentDao {

    private static EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory("collegePersistenceUnit");

    @Override
    public Optional<List<Student>> getAll() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> studentCriteriaQuery = criteriaBuilder.createQuery(Student.class);
        Root<Student> studentRoot = studentCriteriaQuery.from(Student.class);
        studentCriteriaQuery = studentCriteriaQuery.select(studentRoot);
        TypedQuery<Student> studentTypedQuery = entityManager.createQuery(studentCriteriaQuery);
        List<Student> students = studentTypedQuery.getResultList();

        return ExceptionHandler.handle(
                () -> Optional.of(students),
                Optional.empty());
    }

    @Override
    public Optional<Student> getById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return ExceptionHandler.handle(
                () -> Optional.of(entityManager.find(Student.class, id)),
                Optional.empty());
    }

    @Override
    public Optional<Student> getByEmailAddress(String emailAddress) {
        return JdbcTemplate.process((connection) -> ExceptionHandler.handle(
                () -> Optional.of(connection.executeSingle(DbQueryConstant.Student.GET_BY_EMAIL,
                        new StudentRowMapperImpl(), emailAddress)),
                () -> ExceptionHandler.handle(connection::close),
                Optional.empty()));
    }

    @Override
    public Optional<Student> getByContactNo(String contactNo) {
        return JdbcTemplate.process((connection) -> ExceptionHandler.handle(
                () -> Optional.of(connection.executeSingle(DbQueryConstant.Student.GET_BY_CONTACT_NO,
                        new StudentRowMapperImpl(), contactNo)),
                () -> ExceptionHandler.handle(connection::close),
                Optional.empty()));
    }

    @Override
    public Boolean save(Student student) {
        return JdbcTemplate.process((connection) -> ExceptionHandler.handle(
                () -> connection.execute(DbQueryConstant.Student.SAVE,
                        student.getName(), student.getEmail(), student.getContactNo()) >= 1,
                () -> ExceptionHandler.handle(connection::close),
                false
        ));
    }

    @Override
    public Boolean update(Student student) {
        return JdbcTemplate.process((connection) -> ExceptionHandler.handle(
                () -> connection.execute(DbQueryConstant.Student.UPDATE,
                        student.getName(), student.getEmail(), student.getContactNo(), student.getId()) >= 1,
                () -> ExceptionHandler.handle(connection::close),
                false
        ));
    }

    @Override
    public Boolean delete(Long id) {
        return JdbcTemplate.process((connection) -> ExceptionHandler.handle(
                () -> connection.execute(DbQueryConstant.Student.DELETE, id) >= 1,
                () -> ExceptionHandler.handle(connection::close),
                false
        ));
    }
}
