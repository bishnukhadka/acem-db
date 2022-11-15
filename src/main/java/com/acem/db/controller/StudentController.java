package com.acem.db.controller;

import com.acem.db.builder.ResponseBuilder;
import com.acem.db.constant.RegexConstant;
import com.acem.db.exception.ExceptionHandler;
import com.acem.db.request.StudentSaveRequest;
import com.acem.db.request.StudentUpdateRequest;
import com.acem.db.request.mapper.StudentMapperUtil;
import com.acem.db.response.Response;
import com.acem.db.service.StudentService;
import com.acem.db.service.impl.StudentServiceImpl;
import com.acem.db.utils.InputStreamMapperUtil;
import com.acem.db.utils.ValidationUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


public class StudentController extends Controller {

    private static final StudentService studentService = new StudentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        ExceptionHandler.handleWithFallBack(
                () -> {
                    String url = request.getRequestURL().toString();
                    String[] urlTokenized = url.split("/");
                    String id = urlTokenized[urlTokenized.length - 1];
                    Response responseBody = null;
                    if (id.matches(RegexConstant.isNumber)) {
                        responseBody = studentService.getById(Long.parseLong(id));
                    } else {
                        responseBody = studentService.getAll();
                    }
                    buildResponse(response, responseBody);
                },
                () -> buildResponse(response, ResponseBuilder.serverError())
        );
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ExceptionHandler.handleWithFallBack(
                () -> {
                    StudentSaveRequest studentSaveRequest = InputStreamMapperUtil
                            .mapToObject(request.getInputStream(), StudentSaveRequest.class);

                    Optional<List<String>> violations = ValidationUtil.validate(studentSaveRequest);

                    Response responseBody = null;
                    if (!violations.isPresent()) {
                        responseBody = ResponseBuilder.validationFailed(violations.get());
                    } else {
                        responseBody = studentService.save(StudentMapperUtil.mapStudent(studentSaveRequest));
                    }
                    buildResponse(response, responseBody);
                },
                () -> buildResponse(response, ResponseBuilder.serverError())
        );
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ExceptionHandler.handleWithFallBack(
                () -> {
                    StudentUpdateRequest studentUpdateRequest = InputStreamMapperUtil
                            .mapToObject(request.getInputStream(), StudentUpdateRequest.class);

                    Optional<List<String>> violations = ValidationUtil.validate(studentUpdateRequest);

                    Response responseBody = null;
                    if (!violations.isPresent()) {
                        responseBody = ResponseBuilder.validationFailed(violations.get());
                    } else {
                        responseBody = studentService.update(StudentMapperUtil.mapStudent(studentUpdateRequest));
                    }
                    buildResponse(response, responseBody);
                },
                () -> buildResponse(response, ResponseBuilder.serverError())
        );
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ExceptionHandler.handleWithFallBack(
                () -> {
                    String url = request.getRequestURL().toString();
                    String[] urlTokenized = url.split("/");
                    String id = urlTokenized[urlTokenized.length - 1];
                    Response responseBody = null;
                    if (id.matches(RegexConstant.isNumber)) {
                        responseBody = studentService.delete(Long.parseLong(id));
                    } else {
                        responseBody = ResponseBuilder.invalidPathParameter();
                    }
                    buildResponse(response, responseBody);
                },
                () -> buildResponse(response, ResponseBuilder.serverError())
        );
    }
}
