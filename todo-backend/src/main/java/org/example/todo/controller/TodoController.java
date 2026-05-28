package org.example.todo.controller;

import lombok.RequiredArgsConstructor;
import org.example.todo.domain.Todo;
import org.example.todo.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public List<Todo> findAllTodos(){
        return todoService.findAllTodo();
    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo){
        return todoService.createTodo(todo);
    }

    @PostMapping("/modify")
    public Todo modifyTodo(@RequestBody Todo todo){
        return todoService.modifyTodo(todo);
    }

    @GetMapping("/delete/{id}")
    public void deleteTodo(@PathVariable int id){
        todoService.deleteTodo(id);
    }

    @GetMapping("/done/{id}")
    public Todo doneToggle(@PathVariable int id, @RequestParam boolean isDone){
        return todoService.toggleTodoDone(id, isDone);
    }

}
