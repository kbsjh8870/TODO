package org.example.todo.dao;

import org.example.todo.dto.TodoDTO;
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
public class TodoDAO {
    // 할일 등록
    public int createTODO(Connection conn, TodoDTO todoDTO){
        String sql = "insert into todo(user_id,content,category_id,isdone) values(?,?,?,?)";
        int result = 0;

        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,todoDTO.getUser_id());
            pstmt.setString(2,todoDTO.getContent());
            pstmt.setInt(3,todoDTO.getCategory_id());
            pstmt.setBoolean(4,todoDTO.getIsDone());

            result = pstmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public int modifyTODO(Connection conn, TodoDTO todoDTO){
        String sql = "update todo set content = ?, category_id = ? where id = ?";
        int result =0;

        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, todoDTO.getContent());
            pstmt.setInt(2,todoDTO.getCategory_id());
            pstmt.setInt(3, todoDTO.getId());

            result = pstmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }

    public int deleteTODO(Connection conn, int id){
        String sql = "delete from todo where id = ?";
        int result =0;

        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,id);

            result = pstmt.executeUpdate();
        }
        catch (SQLException  e){
            e.printStackTrace();
        }

        return result;
    }

    public List<TodoDTO> findAllTodo(Connection conn){
        String sql = "select id,user_id,content,upload_date,category_id,isdone from todo";
        List<TodoDTO> todos = new ArrayList<>();

        try(PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()){

            while(rs.next()){
                TodoDTO todoDTO = new TodoDTO();

                todoDTO.setId(rs.getInt("id"));
                todoDTO.setUser_id(rs.getInt("user_id"));
                todoDTO.setContent(rs.getString("content"));
                todoDTO.setUpload_date(rs.getObject("upload_date", LocalDate.class));
                todoDTO.setCategory_id(rs.getInt("category_id"));
                todoDTO.setDone(rs.getBoolean("isdone"));

                todos.add(todoDTO);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return todos;
    }

    public TodoDTO findById(Connection conn, int id){
        String sql = "select id,user_id,content,upload_date,category_id,isdone from todo where id = ?";

        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,id);

            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    TodoDTO todoDTO = new TodoDTO();

                    todoDTO.setId(rs.getInt("id"));
                    todoDTO.setUser_id(rs.getInt("user_id"));
                    todoDTO.setContent(rs.getString("content"));
                    todoDTO.setUpload_date(rs.getObject("upload_date", LocalDate.class));
                    todoDTO.setCategory_id(rs.getInt("category_id"));
                    todoDTO.setDone(rs.getBoolean("isdone"));

                    return todoDTO;
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public  List<TodoDTO>  findByDate(Connection conn,LocalDate date){
        String sql = "select id,user_id,content,upload_date,category_id,isdone from todo where upload_date = ?";
        List<TodoDTO> todos= new ArrayList<>();

        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setObject(1,date);

            try(ResultSet rs = pstmt.executeQuery()){
                while(rs.next()){
                    TodoDTO todoDTO = new TodoDTO();

                    todoDTO.setId(rs.getInt("id"));
                    todoDTO.setUser_id(rs.getInt("user_id"));
                    todoDTO.setContent(rs.getString("content"));
                    todoDTO.setUpload_date(rs.getObject("upload_date", LocalDate.class));
                    todoDTO.setCategory_id(rs.getInt("category_id"));
                    todoDTO.setDone(rs.getBoolean("isdone"));

                    todos.add(todoDTO);
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return todos;
    }

    public int toggleDone(Connection conn, int id, boolean isDone) {
        String sql = "update todo set isdone = ? where id = ?";
        int result = 0;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setBoolean(1, isDone);
            pstmt.setInt(2, id);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
