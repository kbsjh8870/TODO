package org.example.todo.service;

import lombok.RequiredArgsConstructor;
import org.example.todo.domain.Category;
import org.example.todo.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    // 카테고리 등록
    public Category createCategory(Category category) {

        if (category.getCategory() == null || category.getCategory().trim().isEmpty()) {
            return null;
        }
        return categoryRepository.save(category);
    }

    // 카테고리 삭제 (이름으로)
    public void deleteCategory(String categoryName) {
        categoryRepository.deleteByCategory(categoryName);
    }

    // id로 단건 조회
    public Category findCategoryById(int id) {
        return categoryRepository.findById(id).orElse(null);
    }

    // 전체 조회
    public List<Category> findAllCategory() {
        List<Category> list = new ArrayList<>();
        categoryRepository.findAll().forEach(list::add);
        return list;
    }

    public Category findCategoryByName(String name) {
        return categoryRepository.findByCategory(name).orElse(null);
    }
    
}
