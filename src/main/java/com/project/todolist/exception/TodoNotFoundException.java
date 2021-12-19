package com.project.todolist.exception;

public class TodoNotFoundException extends RuntimeException {
    public TodoNotFoundException(String s) {
        super(s);
    }
}
