package com.project.todolist.mapper;

import com.project.todolist.dto.ItemDto;
import com.project.todolist.model.Item;

public class ItemMapper {

    public static ItemDto toDto(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .done(item.isDone())
                .build();
    }
}
