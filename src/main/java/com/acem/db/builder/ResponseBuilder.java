package com.acem.db.builder;

import com.acem.db.constant.ResponseMessageConstant;
import com.acem.db.response.Response;
import jakarta.servlet.http.HttpServletResponse;


public class ResponseBuilder {

    public static Response success(String description) {
        return new Response()
                .success(true)
                .description(description)
                .statusCode(HttpServletResponse.SC_OK);
    }

    public static Response success(String description, Object data) {
        return new Response()
                .data(data)
                .success(true)
                .description(description)
                .statusCode(HttpServletResponse.SC_OK);
    }

    public static Response notFound(String description) {
        return new Response()
                .success(false)
                .description(description)
                .statusCode(HttpServletResponse.SC_NOT_FOUND);
    }

    public static Response failure(String description) {
        return new Response()
                .success(false)
                .description(description)
                .statusCode(HttpServletResponse.SC_BAD_REQUEST);
    }

    public static Response serverError() {
        return new Response()
                .success(false)
                .description(ResponseMessageConstant.SERVER_ERROR)
                .statusCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

    public static Response invalidPathParameter() {
        return new Response()
                .success(false)
                .description(ResponseMessageConstant.INVALID_PATH_PARAMETER)
                .statusCode(HttpServletResponse.SC_BAD_REQUEST);
    }

    public static Response validationFailed(Object error) {
        return new Response()
                .success(false)
                .error(error)
                .description(ResponseMessageConstant.INVALID_REQUEST_BODY)
                .statusCode(HttpServletResponse.SC_BAD_REQUEST);
    }

}
