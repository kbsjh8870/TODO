package org.example.todo.service;

import org.example.todo.dao.TodoDAO;
import org.example.todo.DBUtil.DBConnection;
import org.example.todo.dto.TodoDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class TodoService {

    private final TodoDAO todoDAO;

    public TodoService(TodoDAO todoDAO){
        this.todoDAO = todoDAO;
    }

    // 할일 등록
    public boolean createTodo(TodoDTO todoDTO) {

        if (todoDTO.getContent() == null || todoDTO.getContent().trim().isEmpty()) {
            return false;
        }

        try (Connection conn = DBConnection.getConnection()) {
            int result = todoDAO.createTODO(conn, todoDTO);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 할일 수정
    public boolean modifyTodo(TodoDTO todoDTO) {

        if (todoDTO.getContent() == null || todoDTO.getContent().trim().isEmpty()) {
            return false;
        }

        try (Connection conn = DBConnection.getConnection()) {
            int result = todoDAO.modifyTODO(conn, todoDTO);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 할일 삭제
    public boolean deleteTodo(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            int result = todoDAO.deleteTODO(conn, id);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 전체 조회
    public List<TodoDTO> findAllTodo() {
        try (Connection conn = DBConnection.getConnection()) {
            return todoDAO.findAllTodo(conn);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // id로 단건 조회
    public TodoDTO findTodoById(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            return todoDAO.findById(conn, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 날짜로 조회
    public List<TodoDTO> findTodoByDate(LocalDate date) {
        try (Connection conn = DBConnection.getConnection()) {
            return todoDAO.findByDate(conn, date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean toggleTodoDone(int id, boolean isDone){
        try(Connection conn = DBConnection.getConnection()){
            int result = todoDAO.toggleDone(conn,id,isDone);
            return result > 0;
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}

