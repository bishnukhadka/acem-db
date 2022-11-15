package com.acem.db.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response implements Serializable {

    private Integer statusCode;
    private Boolean success;
    private String description;

    private Object data;

    private Object error;

    public Integer getStatusCode() {
        return statusCode;
    }

    public Response statusCode(Integer statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Response success(Boolean success) {
        this.success = success;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Response description(String description) {
        this.description = description;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Response data(Object data) {
        this.data = data;
        return this;
    }

    public Object getError() {
        return error;
    }

    public Response error(Object error) {
        this.error = error;
        return this;
    }
}
