package ru.vsouth.wellbeingdiary.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import ru.vsouth.wellbeingdiary.dto.user.UserRequest;
import ru.vsouth.wellbeingdiary.dto.user.UserResponse;
import ru.vsouth.wellbeingdiary.model.user.Role;
import ru.vsouth.wellbeingdiary.service.user.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthController authController;

    @Test
    void testShowLogin() {
        String result = authController.showLogin();
        assertEquals("login", result);
    }

    @Test
    void testShowRegister() {
        Model model = Mockito.mock(Model.class);
        List<String> roles = Arrays.stream(Role.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        when(model.addAttribute("roles", roles)).thenReturn(model);
        String result = authController.showRegister(model);
        assertEquals("register", result);
        verify(model).addAttribute("roles", roles);
    }

    @Test
    void testRegisterUser() {
        UserRequest userRequest = new UserRequest();
        UserResponse userResponse = new UserResponse();
        userResponse.setId(1);
        when(userService.registerUser(userRequest)).thenReturn(userResponse);
        String result = authController.registerUser(userRequest);
        assertEquals("redirect:/user/1", result);
        userResponse.setId(0);
        when(userService.registerUser(userRequest)).thenReturn(userResponse);
        result = authController.registerUser(userRequest);
        assertEquals("redirect:/user/0", result);
    }
}