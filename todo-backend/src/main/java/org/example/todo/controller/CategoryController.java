package org.example.todo.controller;

import org.example.todo.service.CategoryService;

public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }
}
