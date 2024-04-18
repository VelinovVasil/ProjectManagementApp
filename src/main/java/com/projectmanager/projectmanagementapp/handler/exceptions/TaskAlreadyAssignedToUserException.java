package com.projectmanager.projectmanagementapp.handler.exceptions;

public class TaskAlreadyAssignedToUserException extends RuntimeException {
    public TaskAlreadyAssignedToUserException(String message) {
        super(message);
    }
}
