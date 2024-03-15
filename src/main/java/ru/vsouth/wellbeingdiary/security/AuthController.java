package ru.vsouth.wellbeingdiary.security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vsouth.wellbeingdiary.dto.user.UserRequest;
import ru.vsouth.wellbeingdiary.dto.user.UserResponse;
import ru.vsouth.wellbeingdiary.model.user.Role;
import ru.vsouth.wellbeingdiary.service.user.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegister(Model model) {
        List<String> roles = Arrays.stream(Role.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        model.addAttribute("roles", roles);
        return "register";
    }

    @PostMapping("/register_user")
    public String registerUser(@ModelAttribute("userRequest") UserRequest userRequest) {
        UserResponse userResponse = userService.registerUser(userRequest);
        if (userResponse != null) {
            int userId = userResponse.getId();
            return "redirect:/user/" + userId;
        } else {
            return "redirect:/registration-error";
        }
    }
}
