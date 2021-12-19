package com.project.todolist.controller;

import com.project.todolist.dto.TodoDto;
import com.project.todolist.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("v1/lists/")
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createList(@RequestBody TodoDto todoDto) {
        todoService.createList(todoDto);
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
    public void deleteList(@PathVariable(value = "idList") long idList) {
        todoService.deleteList(idList);
    }

    @PostMapping("{idList}")
    public void createListItem(@PathVariable(value = "idList") long idLIst) {
        todoService.createItemInAList(idLIst);
    }

    @PutMapping("{idList}/items/{idItem}")
    public void updateAnItemInALIst(@PathVariable(value = "idList") long idList,
                                    @PathVariable(value = "idItem") long idItem) {
        todoService.updateAnItemInALIst(idList, idItem);
    }

    @DeleteMapping("{idList}/items/{idItem}")
    public void deleteAnItemInAList(@PathVariable(value = "idList") long idList,
                                    @PathVariable(value = "idItem") long idItem) {
        todoService.deleteAnItemInALIst(idList, idItem);
    }
}
