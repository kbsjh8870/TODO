/*
package org.example.todo.dao;

import lombok.RequiredArgsConstructor;
import org.example.todo.dto.CategoryDTO;
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
public class CategoryDAO {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<CategoryDTO> categoryRowMapper = (rs, rowNum) -> {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(rs.getInt("id"));
        dto.setCategory(rs.getString("category"));
        return dto;
    };

    // 카테고리 등록
    public int createCategory(CategoryDTO categoryDTO){
        String sql = "insert into category(category) values (?)";
        return jdbcTemplate.update(sql,categoryDTO.getCategory());
    }

    // 카테고리 삭제
    public int deleteCategory(String categoryName){

        String sql = "delete from category where category = ?";
        return jdbcTemplate.update(sql,categoryName);
    }

    public CategoryDTO findById(int id) {
        String sql = "select id, category from category where id = ?";
        return jdbcTemplate.queryForObject(sql,categoryRowMapper,id);
    }

    // 전체 조회
    public List<CategoryDTO> findAllCategory() {
        String sql = "select id, category from category";

        return jdbcTemplate.query(sql,categoryRowMapper);
    }

    // 카테고리 이름으로 조회
    public CategoryDTO findByName(String name) {
        String sql = "select id, category from category where category = ?";

        return jdbcTemplate.queryForObject(sql,categoryRowMapper,name);
    }
}
*/
