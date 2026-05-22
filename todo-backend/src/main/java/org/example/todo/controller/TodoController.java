package org.example.todo.controller;

import org.example.todo.dto.TodoDTO;
import org.example.todo.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    @GetMapping
    public List<TodoDTO> findAllTodos(){
        return todoService.findAllTodo();
    }

    @PostMapping
    public boolean createTodo(@RequestBody TodoDTO todoDTO){
        return todoService.createTodo(todoDTO);
    }
}
