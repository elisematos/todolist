package com.project.todolist.service;

import com.project.todolist.dto.ItemDto;
import com.project.todolist.dto.TodoDto;
import com.project.todolist.exception.ItemNotFoundException;
import com.project.todolist.exception.TodoNotFoundException;
import com.project.todolist.mapper.TodoMapper;
import com.project.todolist.model.Item;
import com.project.todolist.model.Todo;
import com.project.todolist.repository.ItemRepository;
import com.project.todolist.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final ItemRepository itemRepository;

    public TodoDto createList(TodoDto todoDto) {
        Todo todo = TodoMapper.toEntity(todoDto);
        List<Item> items = new ArrayList<>();
        if (todoDto.getItemsDto() != null) {
            todoDto.getItemsDto().forEach(item -> {
                        boolean isDone = item.getDone() != null ? item.getDone() : false;
                        Item itemSaved = itemRepository.save(Item.builder().name(item.getName()).done(isDone).build());
                        items.add(itemSaved);
                    }
            );
            todo.setItems(items);
        }

        Todo todoSaved = todoRepository.save(todo);
        todoDto.setId(todoSaved.getId());
        return todoDto;
    }

    public List<TodoDto> getAllLists() {
        return todoRepository.findAll()
                .stream()
                .map(TodoMapper::toDto)
                .collect(Collectors.toList());
    }

    public TodoDto getListById(long idList) {
        Todo todo = todoRepository.findById(idList).orElseThrow(
                () -> new TodoNotFoundException("Todo not found with id : " + idList));
        return TodoMapper.toDto(todo);
    }

    public TodoDto deleteList(long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(
                () -> new TodoNotFoundException("Todo not found with id : " + id));
        todoRepository.delete(todo);
        return TodoDto.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .build();
    }

    public ItemDto addItemToList(long idList, ItemDto itemDto) {
        Todo todo = todoRepository.findById(idList).orElseThrow(
                () -> new TodoNotFoundException("Todo not found with id : " + idList));
        boolean isDone = itemDto.getDone() != null ? itemDto.getDone() : false;
        Item itemSaved = itemRepository.save(Item.builder().name(itemDto.getName()).done(isDone).build());
        List<Item> items = todo.getItems();
        items.add(itemSaved);
        todo.setItems(items);
        todoRepository.save(todo);
        itemDto.setId(itemSaved.getId());
        itemDto.setDone(itemSaved.isDone());
        return itemDto;
    }

    public void updateItem(long idItem, ItemDto itemDto) {
        boolean isDone = itemDto.getDone() != null ? itemDto.getDone() : false;
        Item item = itemRepository.findById(idItem).orElseThrow(
                () -> new ItemNotFoundException("Item not found with id " + idItem)
        );
        item.setName(itemDto.getName());
        item.setDone(isDone);
        itemRepository.save(item);
    }

    public void changeCheck(long idItem) {
        Item item = itemRepository.findById(idItem).orElseThrow(
                () -> new ItemNotFoundException("Item not found with id " + idItem)
        );
       item.setDone(!item.isDone());
       itemRepository.save(item);
    }

    @Transactional
    public void deleteItem(long idList, long idItem) {
        Todo todo = todoRepository.findById(idList).orElseThrow(
                () -> new TodoNotFoundException("Todo not found with id " + idList));
        List<Item> items = todo.getItems();
        Item item = itemRepository.findById(idItem).orElseThrow(
                () -> new ItemNotFoundException("Item not found with id " + idItem)
        );
        items.remove(item);
        todo.setItems(items);
        todoRepository.save(todo);
        itemRepository.delete(item);
    }
}

