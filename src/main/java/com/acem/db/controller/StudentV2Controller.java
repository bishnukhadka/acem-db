package com.acem.db.controller;

import com.acem.db.service.StudentService;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.print.DocFlavor;
import javax.print.attribute.standard.Media;

@Path("v2/students")
public class StudentV2Controller {

    @Inject
    private StudentService studentService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response students(){
        com.acem.db.response.Response responseBody = studentService.getAll();

        return Response
                .status(responseBody.getStatusCode())
                .entity(responseBody)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(){
        com.acem.db.response.Response responseBody = studentService.getAll();

        return Response
                .status(responseBody.getStatusCode())
                .entity(responseBody)
                .build();
    }

}
