package org.example.todo.controller;

import org.example.todo.service.UserService;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }


}
