package org.example.todo.service;

import lombok.RequiredArgsConstructor;
import org.example.todo.domain.Todo;
import org.example.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    // 할일 등록
    public Todo createTodo(Todo todo) {
        if (todo.getContent() == null || todo.getContent().trim().isEmpty())
            return null;
        return todoRepository.save(todo);
    }

    // 할일 수정
    public Todo modifyTodo(Todo todo) {
        if (todo.getContent() == null || todo.getContent().trim().isEmpty())
            return null;
        return todoRepository.save(todo);
    }

    // 할일 삭제
    public void deleteTodo(int id) {
        todoRepository.deleteById(id);
    }


    // 전체 조회
    public List<Todo> findAllTodo() {
        List<Todo> list = new ArrayList<>();
        todoRepository.findAll().forEach(list::add);
        return list;
    }

    // id로 단건 조회
    public Todo findTodoById(int id) {
        return todoRepository.findById(id).orElse(null);
    }

/*    // 날짜로 조회
    public List<TodoDTO> findTodoByDate(LocalDate date) {
        return todoDAO.findByDate(date);
    }*/

    public Todo toggleTodoDone(int id, boolean isDone) {
        Todo todo = todoRepository.findById(id).orElse(null);
        if (todo == null)
            return null;

        todo.setDone(isDone);
        return todoRepository.save(todo);
    }
}

