package org.example.todo.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.todo.domain.User;
import org.example.todo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/login")
    public User login(@RequestBody User user, HttpServletResponse response){
        User loginUser = userService.login(user.getName(),user.getPassword());

        if(loginUser!=null){
            Cookie cookie = new Cookie("loginUser",String.valueOf(loginUser.getId()));
            cookie.setPath("/");
            cookie.setMaxAge(60*60);

            response.addCookie(cookie);

            return loginUser;
        }

        return null;
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable int id){
        return userService.findUserById(id);
    }

    @GetMapping("/logout")
    public void logout(HttpServletResponse response){
         Cookie cookie = new Cookie("loginUser",null);
         cookie.setPath("/");
         cookie.setMaxAge(0);

         response.addCookie(cookie);
    }

}
