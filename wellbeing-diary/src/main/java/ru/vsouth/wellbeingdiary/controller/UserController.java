package ru.vsouth.wellbeingdiary.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vsouth.wellbeingdiary.model.User;
import ru.vsouth.wellbeingdiary.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        return allUsers.toString();
    }
}
