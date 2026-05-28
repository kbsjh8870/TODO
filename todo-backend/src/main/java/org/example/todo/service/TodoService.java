package org.example.todo.service;

import org.example.todo.dao.TodoDAO;
import org.example.todo.dto.TodoDTO;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Service
public class TodoService {

    private final TodoDAO todoDAO;

    public TodoService(TodoDAO todoDAO){
        this.todoDAO = todoDAO;
    }

    // 할일 등록
    public boolean createTodo(TodoDTO todoDTO) {
        if (todoDTO.getContent() == null || todoDTO.getContent().trim().isEmpty()) return false;
        return todoDAO.createTODO(todoDTO) > 0;
    }

    // 할일 수정
    public boolean modifyTodo(TodoDTO todoDTO) {
        if (todoDTO.getContent() == null || todoDTO.getContent().trim().isEmpty()) return false;
        return todoDAO.modifyTODO(todoDTO) > 0;
    }

    // 할일 삭제
    public boolean deleteTodo(int id) {
        return todoDAO.deleteTODO(id) > 0;
    }


    // 전체 조회
    public List<TodoDTO> findAllTodo() {
        return todoDAO.findAllTodo();
    }

    // id로 단건 조회
    public TodoDTO findTodoById(int id) {
        return todoDAO.findById(id);
    }

    // 날짜로 조회
    public List<TodoDTO> findTodoByDate(LocalDate date) {
        return todoDAO.findByDate(date);
    }

    public boolean toggleTodoDone(int id, boolean isDone) {
        return todoDAO.toggleDone(id, isDone) > 0;
    }
}

