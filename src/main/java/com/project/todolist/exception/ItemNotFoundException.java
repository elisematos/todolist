package com.project.todolist.exception;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String s) {
        super(s);
    }
}
