package Service;

import DAO.CategoryDAO;
import DBUtil.DBConnection;
import DTO.CategoryDTO;

import java.sql.Connection;
import java.util.List;

public class CategoryService {

    private final CategoryDAO categoryDAO = new CategoryDAO();

    // 카테고리 등록
    public boolean createCategory(CategoryDTO categoryDTO) {

        if (categoryDTO.getCategory() == null || categoryDTO.getCategory().trim().isEmpty()) {
            return false;
        }

        try (Connection conn = DBConnection.getConnection()) {
            int result = categoryDAO.createCategory(conn, categoryDTO);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 카테고리 삭제 (이름으로)
    public boolean deleteCategory(String categoryName) {

        if(categoryName == null || categoryName.trim().isEmpty())
            return false;

        try (Connection conn = DBConnection.getConnection()) {
            int result = categoryDAO.deleteCategory(conn, categoryName);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // id로 단건 조회
    public CategoryDTO findCategoryById(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            return categoryDAO.findById(conn, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 전체 조회
    public List<CategoryDTO> findAllCategory() {
        try (Connection conn = DBConnection.getConnection()) {
            return categoryDAO.findAllCategory(conn);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public CategoryDTO findCategoryByName(String name) {
        try (Connection conn = DBConnection.getConnection()) {
            return categoryDAO.findByName(conn, name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
