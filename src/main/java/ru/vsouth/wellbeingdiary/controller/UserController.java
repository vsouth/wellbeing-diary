package ru.vsouth.wellbeingdiary.controller;

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

/**
 * Контроллер пользователей. Отвечает за работу с данными пользователей
 */
@Controller
@RequestMapping("user")
public class UserController {
    private final UserService userService;
    private final CustomUserDetailsService userDetailsService;

    public UserController(UserService userService, CustomUserDetailsService userDetailsService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Метод для отображения информации о пользователе
     *
     * @param id    Идентификатор пользователя
     * @param model Модель для передачи данных в представление
     * @return      Страница "Профиль"
     */
    @GetMapping("/{id}")
    public String showUserInfo(@PathVariable int id, Model model) {
        User user = getAuthorizedUser();
        Role role = user.getRole();
        model.addAttribute("role", role);
        UserResponse userResponse = userService.getUserById(id);
        model.addAttribute("userResponse", userResponse);
        List<String> roles = Arrays.stream(Role.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        model.addAttribute("roles", roles);
        return "user_info";
    }

    /**
     * Метод для отображения информации об авторизованном пользователе
     *
     * @param model Модель для передачи данных в представление
     * @return      Страница "Профиль"
     */
    @GetMapping("/profile")
    public String userProfile(Model model) {
        User user = getAuthorizedUser();
        int userId = user.getId();
        Role role = user.getRole();
        model.addAttribute("role", role);
        UserResponse userResponse = userService.getUserById(userId);
        model.addAttribute("userResponse", userResponse);
        return "user_info";
    }

    /**
     * Метод для отображения страницы обновления профиля
     *
     * @param model Модель для передачи данных в представление
     * @return      Страница обновления профиля
     */
    @GetMapping("/update")
    public String showUpdateUser(Model model) {
        User user = getAuthorizedUser();
        int userId = user.getId();
        Role role = user.getRole();
        model.addAttribute("role", role);
        UserResponse userResponse = userService.getUserById(userId);
        model.addAttribute("userResponse", userResponse);
        List<String> roles = Arrays.stream(Role.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        model.addAttribute("roles", roles);
        return "update_user";
    }

    /**
     * Метод для отображения страницы изменения пароля
     *
     * @param model Модель для передачи данных в представление
     * @return      Страница изменения пароля
     */
    @GetMapping("/update_password")
    public String showUpdateUserPassword(Model model) {
        User user = getAuthorizedUser();
        int userId = user.getId();
        Role role = user.getRole();
        model.addAttribute("role", role);
        UserResponse userResponse = userService.getUserById(userId);
        model.addAttribute("userResponse", userResponse);
        return "update_password";
    }

    /**
     * Метод для обновления профиля
     *
     * @param user               Запрос на изменение данных профиля
     * @param model              Модель для передачи данных в представление
     * @param redirectAttributes Атрибуты для перенаправления
     * @return                   Перенаправление на страницу профиля
     */
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

    /**
     * Метод для изменения пароля пользователя
     *
     * @param user  Запрос на изменение пароля
     * @return      Перенаправление на страницу профиля пользователя или страницу обновления пароля
     */
    @PostMapping("/update_password")
    public String updateUserPassword(@ModelAttribute("userRequest") UserRequest user) {
        UserResponse userResponse = userService.updateUserPassword(user);
        if (userResponse != null) {
            return "redirect:/user/profile";
        }
        return "redirect:/user/update";
    }

    /**
     * Метод для удаления пользователя
     *
     * @param user  Запрос на удаление пользователя
     * @return      Перенаправление на страницу выхода или обновления профиля
     */
    @PostMapping("/delete")
    public String deleteUser(@ModelAttribute("userRequest") UserRequest user) {
        UserResponse deletedUser = userService.deleteUser(user.getId());
        if (deletedUser != null) {
            return "redirect:/logout";
        } else {
            return "redirect:/user/update";
        }
    }

    /**
     * Вспомогательный метод для получения авторизованного пользователя
     *
     * @return Авторизованный пользователь
     */
    private User getAuthorizedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userDetailsService.loadUserDetailsByUsername(username);
    }
}
