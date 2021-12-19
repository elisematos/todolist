package com.project.todolist.mapper;

import com.project.todolist.dto.TodoDto;
import com.project.todolist.model.Todo;

import java.util.stream.Collectors;

public class TodoMapper {

    public static Todo toEntity(TodoDto todoDto) {
        return Todo.builder()
                .title(todoDto.getTitle())
                .build();
    }

    public static TodoDto toDto(Todo todo) {
        return TodoDto.builder()
                .title(todo.getTitle())
                .itemsDto(todo.getItems().stream().map(ItemMapper::toDto).collect(Collectors.toList()))
                .build();
    }
}
