package org.example.todo.service;

import org.example.todo.dao.CategoryDAO;
import org.example.todo.dto.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryDAO categoryDAO;

    public CategoryService(CategoryDAO categoryDAO){
        this.categoryDAO = categoryDAO;
    }

    // 카테고리 등록
    public boolean createCategory(CategoryDTO categoryDTO) {

        if (categoryDTO.getCategory() == null || categoryDTO.getCategory().trim().isEmpty()) {
            return false;
        }
        return categoryDAO.createCategory(categoryDTO)>0;
    }

    // 카테고리 삭제 (이름으로)
    public boolean deleteCategory(String categoryName) {

        if(categoryName == null || categoryName.trim().isEmpty())
            return false;
        return categoryDAO.deleteCategory(categoryName)>0;
    }

    // id로 단건 조회
    public CategoryDTO findCategoryById(int id) {
        return categoryDAO.findById(id);
    }

    // 전체 조회
    public List<CategoryDTO> findAllCategory() {
        return categoryDAO.findAllCategory();
    }

    public CategoryDTO findCategoryByName(String name) {
        return categoryDAO.findByName(name);
    }
    
}
