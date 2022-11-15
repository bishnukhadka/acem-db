package com.acem.db.mapper.impl;

import com.acem.db.mapper.RowMapper;
import com.acem.db.model.Student;

import java.sql.ResultSet;

public class StudentRowMapperImpl implements RowMapper<Student> {

    @Override
    public Student map(ResultSet resultSet) throws Exception{
        Student student = new Student();
        student.setId(resultSet.getLong("ID"));
        student.setName(resultSet.getString("NAME"));
        student.setEmail(resultSet.getString("EMAIL"));
        student.setContactNo(resultSet.getString("CONTACT_NO"));
        return student;
    }
}
