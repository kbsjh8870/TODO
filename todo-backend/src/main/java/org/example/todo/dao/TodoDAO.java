/*
package org.example.todo.dao;

import lombok.RequiredArgsConstructor;
import org.example.todo.dto.TodoDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TodoDAO {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<TodoDTO> todoRowMapper = (rs, rowNum) -> {
        TodoDTO dto = new TodoDTO();
        dto.setId(rs.getInt("id"));
        dto.setUser_id(rs.getInt("user_id"));
        dto.setContent(rs.getString("content"));
        dto.setUpload_date(rs.getObject("upload_date", LocalDate.class));
        dto.setCategory_id(rs.getInt("category_id"));
        dto.setDone(rs.getBoolean("isdone"));
        return dto;
    };

    // 할일 등록
    public int createTODO(TodoDTO todoDTO){
        String sql = "insert into todo(user_id,content,category_id,isdone) values(?,?,?,?)";
        return jdbcTemplate.update(sql, todoDTO.getUser_id(), todoDTO.getContent(), todoDTO.getCategory_id(), todoDTO.isDone());
    }

    public int modifyTODO(TodoDTO todoDTO) {
        String sql = "update todo set content = ?, category_id = ? where id = ?";
        return jdbcTemplate.update(sql, todoDTO.getContent(), todoDTO.getCategory_id(), todoDTO.getId());
    }

    public int deleteTODO(int id) {
        String sql = "delete from todo where id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public List<TodoDTO> findAllTodo() {
        String sql = "select id,user_id,content,upload_date,category_id,isdone from todo";
        return jdbcTemplate.query(sql, todoRowMapper);
    }

    public TodoDTO findById(int id) {
        String sql = "select id,user_id,content,upload_date,category_id,isdone from todo where id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, todoRowMapper, id);
        } catch (Exception e) {
            return null;
        }
    }

    public List<TodoDTO> findByDate(LocalDate date) {
        String sql = "select id,user_id,content,upload_date,category_id,isdone from todo where upload_date = ?";
        return jdbcTemplate.query(sql, todoRowMapper, date);
    }

    public int toggleDone(int id, boolean isDone) {
        String sql = "update todo set isdone = ? where id = ?";
        return jdbcTemplate.update(sql, isDone, id);
    }
}
*/
