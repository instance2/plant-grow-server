package com.rasp.server.api;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ErrorHandlingController {

    @ExceptionHandler(IllegalArgumentException.class)
    private void handleIllegalArgument(Exception ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_CONFLICT);
    }

    @ExceptionHandler(NoSuchElementException.class)
    private void handleNosSuchElemntExeption(Exception ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE);
    }
}
