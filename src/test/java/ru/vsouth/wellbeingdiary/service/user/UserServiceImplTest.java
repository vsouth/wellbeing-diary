package ru.vsouth.wellbeingdiary.service.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.vsouth.wellbeingdiary.dto.user.UserRequest;
import ru.vsouth.wellbeingdiary.dto.user.UserResponse;
import ru.vsouth.wellbeingdiary.model.user.Role;
import ru.vsouth.wellbeingdiary.repository.UserRepository;
import ru.vsouth.wellbeingdiary.utils.user.UserMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserRepository userRepository;

    private final UserMapper userMapper = new UserMapper();

    private UserServiceImpl userService;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @BeforeEach
    public void setUp() {
        userService = new UserServiceImpl(userRepository, userMapper, passwordEncoder);
    }


    @Test
    void existsByUsername() {
        assertTrue(userService.existsByUsername("admin"));
        assertFalse(userService.existsByUsername("unknown"));
    }

    @Test
    void registerUser() {
        UserRequest userRequest = new UserRequest();
        userRequest.setId(3);
        userRequest.setUsername("new");
        userRequest.setPassword("password");
        userRequest.setAllowsDataAccess(true);
        userRequest.setRole(Role.USER);
        UserResponse userResponse = userService.registerUser(userRequest);
        assertEquals(userMapper.toUserResponse(userMapper.toUser(userRequest)), userResponse);
    }

    @Test
    void getAllUsers() {
        List<UserResponse> result = userService.getAllUsers();
        assertEquals(3, result.size());
    }

    @Test
    void getUserById() {
        UserResponse result = userService.getUserById(2);
        assertEquals("admin", result.getUsername());
        assertEquals(Role.ANALYST, result.getRole());
        assertFalse(result.isAllowsDataAccess());
    }

    @Test
    void getUserByUsername() {
        UserResponse result = userService.getUserByUsername("vsouth");
        assertEquals("vsouth", result.getUsername());
        assertEquals(Role.USER, result.getRole());
        assertTrue(result.isAllowsDataAccess());
    }

    @Test
    @Disabled
    void saveandDeleteUser() {
        UserRequest userRequest = new UserRequest();
        userRequest.setId(3);
        userRequest.setUsername("new");
        userRequest.setPassword("password");
        userRequest.setAllowsDataAccess(true);
        userRequest.setRole(Role.USER);
        UserResponse userResponse = userService.saveUser(userRequest);
        assertEquals(userMapper.toUserResponse(userMapper.toUser(userRequest)), userResponse);

        UserResponse deleted = new UserResponse();
        deleted.setId(3);
        deleted.setUsername("new");
        deleted.setAllowsDataAccess(true);
        deleted.setRole(Role.USER);
        UserResponse result = userService.deleteUser(3);
        assertEquals(deleted, result);
    }

    @Test
    void updateUser() {
        UserRequest userRequest = new UserRequest();
        userRequest.setId(1);
        userRequest.setUsername("who");
        userRequest.setPassword("password");
        userRequest.setAllowsDataAccess(true);
        userRequest.setRole(Role.USER);
        UserResponse userResponse = userService.updateUser(userRequest);
        assertEquals(userMapper.toUserResponse(userMapper.toUser(userRequest)), userResponse);
    }

    @Test
    void updateUserPassword() {
        UserRequest userRequest = new UserRequest();
        userRequest.setId(1);
        userRequest.setUsername("vsouth");
        userRequest.setPassword("password");
        UserResponse userResponse = userService.updateUserPassword(userRequest);
        assertEquals(userRequest.getId(), userResponse.getId());
    }

    @Test
    void getUsersCount() {
        assertEquals(2, userService.getUsersCount());
    }
}