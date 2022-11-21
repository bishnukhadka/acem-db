package com.acem.db.dao.impl;

import com.acem.db.constant.DbQueryConstant;
import com.acem.db.dao.StudentDAO;
import com.acem.db.dao.qualifier.DataSource;
import com.acem.db.dao.qualifier.DatasourceType;
import com.acem.db.exception.ExceptionHandler;
import com.acem.db.mapper.impl.StudentRowMapperImpl;
import com.acem.db.model.Student;
import com.acem.db.config.JdbcTemplate;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;
@ApplicationScoped
@DataSource(DatasourceType.JDBC_TEMPLATE)
public class StudentDaoJdbcTemplateImpl implements StudentDAO {
    @Override
    public Optional<List<Student>> getAll() {
        return JdbcTemplate.process((connection) -> ExceptionHandler.handle(
                () -> Optional.of(connection.execute(DbQueryConstant.Student.GET_ALL, new StudentRowMapperImpl())),
                () -> ExceptionHandler.handle(connection::close),
                Optional.empty()));
    }

    @Override
    public Optional<Student> getById(Long id) {
        return JdbcTemplate.process((connection) -> ExceptionHandler.handle(
                () -> Optional.of(connection.executeSingle(DbQueryConstant.Student.GET_BY_ID,
                        new StudentRowMapperImpl(), id)),
                () -> ExceptionHandler.handle(connection::close),
                Optional.empty()));
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
