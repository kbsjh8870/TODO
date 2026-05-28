/*
package org.example.todo.dao;

import lombok.RequiredArgsConstructor;
import org.example.todo.dto.UserDTO;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserDAO {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<UserDTO> userRowMapper = (rs, rowNum) -> {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(rs.getInt("id"));
        userDTO.setName(rs.getString("name"));

        return userDTO;
    };

    // 사용자 등록
    public int createUser(UserDTO userDTO){
        String sql = "insert into user(name, password) values (?, ?)";
        return jdbcTemplate.update(sql,userDTO.getName(),userDTO.getPassword());
    }

    // 사용자 수정
    public int modifyUser(UserDTO userDTO){
        String sql = "update user set name = ? where id=?";
        return jdbcTemplate.update(sql,userDTO.getName(),userDTO.getId());
    }

    // 사용자 삭제
    public int deleteUser(int userID){
        String sql = "delete from user where id = ?";
        return jdbcTemplate.update(sql,userID);
    }

    public UserDTO findById(int id) {
        String sql = "select id, name from user where id = ?";

        try{
            return jdbcTemplate.queryForObject(sql,userRowMapper,id);
        }
        catch (DataAccessException e){
            return null;
        }
    }

    public List<UserDTO> findAllUser() {
        String sql = "select id, name from user";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(UserDTO.class));
    }

    public UserDTO login(String name,String password){
        String sql = "select id, name from user where name = ? and password = ?";

        try{
            return jdbcTemplate.queryForObject(sql,userRowMapper,name,password);
        }
        catch (DataAccessException e){
            return null;
        }
    }
}
*/
