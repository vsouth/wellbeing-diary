package ru.vsouth.wellbeingdiary.controller;

import org.springframework.web.bind.annotation.*;
import ru.vsouth.wellbeingdiary.dto.UserResponse;
import ru.vsouth.wellbeingdiary.model.User;
import ru.vsouth.wellbeingdiary.service.UserService;

@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get_users_count")
    public Integer getUsersCount() {
        return userService.getUsersCount();
    }

    @GetMapping("/{id}")
    public UserResponse findUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PutMapping("/update")
    public UserResponse updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @PutMapping("/update_password")
    public UserResponse updateUserPassword(@RequestBody User user) {
        return userService.updateUserPassword(user);
    }

    @DeleteMapping("/delete/{id}")
    public UserResponse deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }
}
