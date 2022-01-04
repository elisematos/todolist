package com.project.todolist.controller;

import com.project.todolist.dto.ItemDto;
import com.project.todolist.dto.TodoDto;
import com.project.todolist.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/lists/")
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoDto createList(@RequestBody TodoDto todoDto) {
        return todoService.createList(todoDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TodoDto> getAllLists() {
        return todoService.getAllLists();
    }

    @GetMapping("{idList}")
    @ResponseStatus(HttpStatus.OK)
    public TodoDto getListById(@PathVariable(value = "idList") long idList) {
        return todoService.getListById(idList);
    }

    @DeleteMapping("{idList}")
    @ResponseStatus(HttpStatus.OK)
    public TodoDto deleteList(@PathVariable(value = "idList") long idList) {
       return todoService.deleteList(idList);
    }

    @PostMapping("{idList}")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto addItemToList(@PathVariable(value = "idList") long idLIst,
                              @RequestBody ItemDto itemDto) {
        return todoService.addItemToList(idLIst, itemDto);
    }

    @PutMapping("items/{idItem}")
    @ResponseStatus(HttpStatus.OK)
    public void updateItem(@PathVariable(value = "idItem") long idItem,
                           @RequestBody ItemDto itemDto) {
        todoService.updateItem(idItem, itemDto);
    }

    @PatchMapping("items/{idItem}")
    @ResponseStatus(HttpStatus.OK)
    public void changeCheck(@PathVariable("idItem") long idItem) {
        todoService.changeCheck(idItem);
    }

    @DeleteMapping("{idList}/items/{idItem}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteItem(@PathVariable(value = "idList") long idList,
                           @PathVariable(value = "idItem") long idItem) {
        todoService.deleteItem(idList, idItem);
    }
}
