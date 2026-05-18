package org.example.todo.config;

import org.example.todo.controller.CategoryController;
import org.example.todo.controller.TodoController;
import org.example.todo.controller.UserController;
import org.example.todo.dao.CategoryDAO;
import org.example.todo.dao.TodoDAO;
import org.example.todo.dao.UserDAO;
import org.example.todo.service.CategoryService;
import org.example.todo.service.TodoService;
import org.example.todo.service.UserService;
import org.springframework.context.annotation.Bean;

public class AppConfig {

    // Controller
    @Bean
    public UserController userController(UserService userService){
        return new UserController(userService);
    }

    @Bean
    public CategoryController categoryController(CategoryService categoryService){
        return new CategoryController(categoryService);
    }

    @Bean
    public TodoController todoController(TodoService todoService){
        return new TodoController(todoService);
    }

    // DAO
    @Bean
    public UserDAO userDAO() {
        return new UserDAO();
    }

    @Bean
    public CategoryDAO categoryDAO() {
        return new CategoryDAO();
    }

    @Bean
    public TodoDAO todoDAO() {
        return new TodoDAO();
    }

    // Service
    @Bean
    public UserService userService(UserDAO userDAO) {
        return new UserService(userDAO);
    }

    @Bean
    public CategoryService categoryService(CategoryDAO categoryDAO) {
        return new CategoryService(categoryDAO);
    }

    @Bean
    public TodoService todoService(TodoDAO todoDAO) {
        return new TodoService(todoDAO);
    }
}
