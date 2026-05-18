package org.example.todo.controller;

import org.example.todo.service.TodoService;

public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }
}
