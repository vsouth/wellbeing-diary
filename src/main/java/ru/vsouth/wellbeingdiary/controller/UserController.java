package ru.vsouth.wellbeingdiary.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.vsouth.wellbeingdiary.dto.user.UserRequest;
import ru.vsouth.wellbeingdiary.dto.user.UserResponse;
import ru.vsouth.wellbeingdiary.model.user.Role;
import ru.vsouth.wellbeingdiary.model.user.User;
import ru.vsouth.wellbeingdiary.security.CustomUserDetailsService;
import ru.vsouth.wellbeingdiary.service.user.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("user")
public class UserController {
    private final UserService userService;
    private final CustomUserDetailsService userDetailsService;

    public UserController(UserService userService, CustomUserDetailsService userDetailsService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
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

    @GetMapping("/profile")
    public String userProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userDetailsService.loadUserDetailsByUsername(username);
        int userId = user.getId();
        UserResponse userResponse = userService.getUserById(userId);
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
        return "redirect:/user/profile";
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
