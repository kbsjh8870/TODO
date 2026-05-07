package DAO;

import DTO.CategoryDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    // 카테고리 등록
    public int createCategory(Connection conn, CategoryDTO categoryDTO){

        String sql = "insert into category(category) values (?)";
        int result=0;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1,categoryDTO.getCategory());

            result = pstmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    // 카테고리 삭제
    public int deleteCategory(Connection conn,String categoryName){

        String sql = "delete from category where category = ?";
        int result = 0;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1,categoryName);

            result = pstmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public CategoryDTO findById(Connection conn, int id) {
        String sql = "select id, category from category where id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    CategoryDTO categoryDTO = new CategoryDTO();
                    categoryDTO.setId(rs.getInt("id"));
                    categoryDTO.setCategory(rs.getString("category"));
                    return categoryDTO;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 전체 조회
    public List<CategoryDTO> findAllCategory(Connection conn) {
        String sql = "select id, category from category";
        List<CategoryDTO> categories = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setId(rs.getInt("id"));
                categoryDTO.setCategory(rs.getString("category"));
                categories.add(categoryDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    // 카테고리 이름으로 조회
    public CategoryDTO findByName(Connection conn, String name) {
        String sql = "select id, category from category where category = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    CategoryDTO categoryDTO = new CategoryDTO();
                    categoryDTO.setId(rs.getInt("id"));
                    categoryDTO.setCategory(rs.getString("category"));
                    return categoryDTO;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
