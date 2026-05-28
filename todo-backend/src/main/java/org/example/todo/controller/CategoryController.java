package org.example.todo.controller;

import lombok.RequiredArgsConstructor;
import org.example.todo.domain.Category;
import org.example.todo.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<Category> findAllCategories(){
        return categoryService.findAllCategory();
    }
}