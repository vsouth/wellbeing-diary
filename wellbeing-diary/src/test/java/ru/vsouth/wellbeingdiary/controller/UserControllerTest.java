package ru.vsouth.wellbeingdiary.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.vsouth.wellbeingdiary.dto.UserRequest;
import ru.vsouth.wellbeingdiary.dto.UserResponse;
import ru.vsouth.wellbeingdiary.model.Role;
import ru.vsouth.wellbeingdiary.service.user.UserService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void getUsersCount() throws Exception {
        when(userService.getUsersCount()).thenReturn(5);

        mockMvc.perform(get("/api/users/get_users_count"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value(5));

        verify(userService, times(1)).getUsersCount();
    }

    @Test
    void findUserById() throws Exception {
        UserResponse mockResponse = new UserResponse(1, "test_user", Role.USER, true);
        when(userService.getUserById(1)).thenReturn(mockResponse);

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("test_user"))
                .andExpect(jsonPath("$.role").value("USER"))
                .andExpect(jsonPath("$.allowsDataAccess").value(true));

        verify(userService, times(1)).getUserById(1);
    }

    @Test
    void updateUser() throws Exception {
        UserRequest userRequest = new UserRequest(1, "test_user", null, Role.ANALYST, true);
        UserResponse mockResponse = new UserResponse(1, "test_user", Role.ANALYST, true);
        when(userService.updateUser(userRequest)).thenReturn(mockResponse);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/users/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"username\": \"test_user\", \"role\": \"ANALYST\", \"allowsDataAccess\": true}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("test_user"))
                .andExpect(jsonPath("$.role").value("ANALYST"))
                .andExpect(jsonPath("$.allowsDataAccess").value(true));

        verify(userService, times(1)).updateUser(userRequest);
    }

    @Test
    void updateUserPassword() throws Exception {
        UserRequest userRequest = new UserRequest(1, "test_user", "new_password", Role.USER, true);
        UserResponse mockResponse = new UserResponse(1, "test_user", Role.USER, true);
        when(userService.updateUserPassword(userRequest)).thenReturn(mockResponse);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/users/update_password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"username\": \"test_user\", \"password\": \"new_password\", \"role\": \"USER\", \"allowsDataAccess\": true}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("test_user"))
                .andExpect(jsonPath("$.role").value("USER"))
                .andExpect(jsonPath("$.allowsDataAccess").value(true));

        verify(userService, times(1)).updateUserPassword(userRequest);
    }

    @Test
    void deleteUser() throws Exception {
        UserResponse mockResponse = new UserResponse(1, "test_user", Role.USER, true);
        when(userService.deleteUser(1)).thenReturn(mockResponse);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("test_user"))
                .andExpect(jsonPath("$.role").value("USER"))
                .andExpect(jsonPath("$.allowsDataAccess").value(true));

        verify(userService, times(1)).deleteUser(1);
    }
}