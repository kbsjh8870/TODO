package org.example.todo.controller;

import org.example.todo.dto.TodoDTO;
import org.example.todo.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    @ResponseBody
    @GetMapping
    public List<TodoDTO> findAllTodos(){
        return todoService.findAllTodo();
    }
}
