package com.project.todolist.mapper;

import com.project.todolist.dto.ItemDto;
import com.project.todolist.model.Item;

import java.util.List;
import java.util.stream.Collectors;

public class ItemMapper {
    public List<ItemDto> mapListToListDto(List<Item> items) {
        return items
                .stream()
                .map(ItemMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<Item> mapListDtoToList(List<ItemDto> itemsDto) {
        return itemsDto
                .stream()
                .map(ItemMapper::toEntity)
                .collect(Collectors.toList());
    }


    public static Item toEntity(ItemDto itemDto) {
        return Item.builder()
                .name(itemDto.getName())
                .done(itemDto.getDone() != null ? itemDto.getDone() : false)
                .build();
    }

    public static ItemDto toDto(Item item) {
        return ItemDto.builder()
                .name(item.getName())
                .done(item.isDone())
                .build();
    }
}
