package ru.vsouth.wellbeingdiary.controller;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import ru.vsouth.wellbeingdiary.dto.user.UserRequest;
import ru.vsouth.wellbeingdiary.dto.user.UserResponse;
import ru.vsouth.wellbeingdiary.model.user.Role;
import ru.vsouth.wellbeingdiary.model.user.User;
import ru.vsouth.wellbeingdiary.security.CustomUserDetailsService;
import ru.vsouth.wellbeingdiary.service.user.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private CustomUserDetailsService userDetailsService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void findUserById() throws Exception {
        UserResponse mockResponse = new UserResponse(1, "test_user", Role.USER, true);
        when(userService.getUserById(1)).thenReturn(mockResponse);

        mockMvc.perform(get("/user/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("user_info"))
                .andExpect(model().attribute("userResponse", mockResponse))
                .andExpect(model().attributeExists("roles"));

        verify(userService, times(1)).getUserById(1);
    }

    @Test
    void updateUser() throws Exception {
        UserRequest userRequest = new UserRequest(1, "test_user", null, Role.ANALYST, true);
        UserResponse mockResponse = new UserResponse(1, "test_user", Role.ANALYST, true);
        when(userService.updateUser(userRequest)).thenReturn(mockResponse);

        mockMvc.perform(post("/user/update")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "1")
                        .param("username", "test_user")
                        .param("role", "ANALYST")
                        .param("allowsDataAccess", "true"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/profile?id=1"));

        verify(userService, times(1)).updateUser(userRequest);
    }

    @Test
    void updateUserPassword() throws Exception {
        UserRequest userRequest = new UserRequest(1, "test_user", "new_password", Role.USER, true);
        UserResponse mockResponse = new UserResponse(1, "test_user", Role.USER, true);
        when(userService.updateUserPassword(userRequest)).thenReturn(mockResponse);

        mockMvc.perform(post("/user/update_password")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "1")
                        .param("username", "test_user")
                        .param("password", "new_password")
                        .param("role", "USER")
                        .param("allowsDataAccess", "true"))
                .andExpect(status().isOk())
                .andExpect(content().string("?????? ???????"));

        verify(userService, times(1)).updateUserPassword(userRequest);
    }

    @Test
    void deleteUser() throws Exception {
        UserResponse mockResponse = new UserResponse(1, "test_user", Role.USER, true);
        when(userService.deleteUser(1)).thenReturn(mockResponse);

        mockMvc.perform(post("/user/delete")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("???????????? ??????? ??????"));

        verify(userService, times(1)).deleteUser(1);
    }

    @Test
    public void testUserProfile() {
        Authentication authentication = new UsernamePasswordAuthenticationToken("username", "password");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = new User();
        user.setId(1);
        UserResponse userResponse = new UserResponse();
        userResponse.setId(1);
        userResponse.setUsername("username");
        List<String> roles = Arrays.stream(Role.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        Mockito.when(userDetailsService.loadUserDetailsByUsername("username")).thenReturn(user);
        Mockito.when(userService.getUserById(1)).thenReturn(userResponse);
        Model model = new ConcurrentModel();
        model.addAttribute("userResponse", userResponse);
        model.addAttribute("roles", roles);
        String viewName = userController.userProfile(model);
        Assert.assertEquals("user_info", viewName);
        Assert.assertTrue(model.containsAttribute("userResponse"));
        Assert.assertTrue(model.containsAttribute("roles"));
        UserResponse retrievedUserResponse = (UserResponse) model.asMap().get("userResponse");
        Assert.assertEquals(userResponse.getId(), retrievedUserResponse.getId());
        Assert.assertEquals(userResponse.getUsername(), retrievedUserResponse.getUsername());
        List<String> retrievedRoles = (List<String>) model.asMap().get("roles");
        Assert.assertEquals(roles.size(), retrievedRoles.size());
        Assert.assertTrue(retrievedRoles.containsAll(roles));
    }
}