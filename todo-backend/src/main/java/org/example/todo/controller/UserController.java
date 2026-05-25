package org.example.todo.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.User;
import org.example.todo.dto.UserDTO;
import org.example.todo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/login")
    public UserDTO login(@RequestBody UserDTO userDTO, HttpServletResponse response){
        UserDTO user = userService.login(userDTO.getName(),userDTO.getPassword());

        if(user!=null){
            Cookie cookie = new Cookie("loginUser",String.valueOf(user.getId()));
            cookie.setPath("/");
            cookie.setMaxAge(60*60);

            response.addCookie(cookie);

            return user;
        }

        return new UserDTO();
    }

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable int id){
        return userService.findUserById(id);
    }

}
