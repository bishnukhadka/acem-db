package com.acem.db.request.mapper;

import com.acem.db.model.Student;
import com.acem.db.request.StudentSaveRequest;
import com.acem.db.request.StudentUpdateRequest;
import com.acem.db.utils.ModalMapperUtil;

public class StudentMapperUtil {

    public static Student mapStudent(StudentSaveRequest studentSaveRequest) {
        return ModalMapperUtil.map(studentSaveRequest, Student.class);
    }

    public static Student mapStudent(StudentUpdateRequest studentUpdateRequest) {
        return ModalMapperUtil.map(studentUpdateRequest, Student.class);
    }
}
