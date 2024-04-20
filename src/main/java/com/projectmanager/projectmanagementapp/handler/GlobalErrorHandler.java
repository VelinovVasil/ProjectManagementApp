package com.projectmanager.projectmanagementapp.handler;


import com.projectmanager.projectmanagementapp.handler.exceptions.EntityNotFoundException;
import com.projectmanager.projectmanagementapp.handler.exceptions.TaskAlreadyAssignedToUserException;
import com.projectmanager.projectmanagementapp.handler.exceptions.UserAlreadyExistsException;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.logging.Logger;

@RestControllerAdvice
public class GlobalErrorHandler {

    private final Logger logger = Logger.getLogger(GlobalErrorHandler.class.getName());

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public String handleEntityNotFound(final HttpServletRequest request, final Exception error) {
        logger.severe(error.getMessage() + " " + request.getRequestURI() + " " + request.getMethod());
        return "Entity not Found";
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TaskAlreadyAssignedToUserException.class)
    public String handleTaskAlreadyAssignedToUser(final HttpServletRequest request, final Exception error) {
        logger.severe(error.getMessage() + " " + request.getRequestURI() + " " + request.getMethod());
        return "Task already assigned to user.";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserAlreadyExistsException.class)
    public String handleUserAlreadyExists(final HttpServletRequest request, final Exception error) {
        logger.severe(error.getMessage() + " " + request.getRequestURI() + " " + request.getMethod());
        return "User already exists";
    }
}
