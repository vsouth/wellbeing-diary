package ru.vsouth.wellbeingdiary.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.vsouth.wellbeingdiary.dto.UserRequest;
import ru.vsouth.wellbeingdiary.dto.UserResponse;
import ru.vsouth.wellbeingdiary.model.Role;
import ru.vsouth.wellbeingdiary.service.user.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String showUserInfo(@PathVariable int id, Model model) {
        UserResponse userResponse = userService.getUserById(id);
        model.addAttribute("userResponse", userResponse);
        List<String> roles = Arrays.stream(Role.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        model.addAttribute("roles", roles);
        return "user_info";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("userRequest") UserRequest user, Model model, RedirectAttributes redirectAttributes) {
        UserResponse updatedUser = userService.updateUser(user);
        model.addAttribute("userResponse", updatedUser);
        List<String> roles = Arrays.stream(Role.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        model.addAttribute("roles", roles);
        redirectAttributes.addAttribute("id", updatedUser.getId());
        return "redirect:/user/{id}";
    }

    @PostMapping("/update_password")
    public ResponseEntity<String> updateUserPassword(@ModelAttribute("userRequest") UserRequest user) {
        UserResponse userResponse = userService.updateUserPassword(user);
        if (userResponse != null) {
            return ResponseEntity.ok("Пароль изменен");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Пользователь не найден");
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteUser(@ModelAttribute("userRequest") UserRequest user) {
        UserResponse deletedUser = userService.deleteUser(user.getId());
        if (deletedUser != null) {
            return ResponseEntity.ok("Пользователь успешно удален");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Пользователь не найден");
        }
    }
}
