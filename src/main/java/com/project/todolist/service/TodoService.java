package com.project.todolist.service;

import com.project.todolist.dto.TodoDto;
import com.project.todolist.exception.TodoNotFoundException;
import com.project.todolist.mapper.TodoMapper;
import com.project.todolist.model.Item;
import com.project.todolist.model.Todo;
import com.project.todolist.repository.ItemRepository;
import com.project.todolist.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final ItemRepository itemRepository;

    public void createList(TodoDto todoDto) {
        Todo todo = TodoMapper.toEntity(todoDto);
        List<Item> items = new ArrayList<>();
        todoDto.getItemsDto().forEach(item -> {
            boolean isDone = item.getDone() != null ? item.getDone() : false;
            Item itemSaved = itemRepository.save(Item.builder().name(item.getName()).done(isDone).build());
            items.add(itemSaved);
            }
        );
        todo.setItems(items);
        todoRepository.save(todo);
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

    public void deleteList(long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(
                () -> new TodoNotFoundException("Todo not found with id : " + id));
        todoRepository.delete(todo);
    }

    public void createItemInAList(long idList) {

    }

    public void updateAnItemInALIst(long idList, long id) {
    }

    public void deleteAnItemInALIst(long idList, long idItem) {
    }
}

