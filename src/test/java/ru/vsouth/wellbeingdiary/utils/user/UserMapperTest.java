package ru.vsouth.wellbeingdiary.utils.user;

import org.junit.jupiter.api.Test;
import ru.vsouth.wellbeingdiary.dto.user.UserRequest;
import ru.vsouth.wellbeingdiary.dto.user.UserResponse;
import ru.vsouth.wellbeingdiary.model.user.Role;
import ru.vsouth.wellbeingdiary.model.user.User;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {
    private final UserMapper userMapper = new UserMapper();

    @Test
    void testToUser() {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("test-user");
        userRequest.setPassword("password");
        userRequest.setRole(Role.ANALYST);
        userRequest.setAllowsDataAccess(true);

        User user = userMapper.toUser(userRequest);

        assertEquals(0, user.getId());
        assertEquals("test-user", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals(Role.ANALYST, user.getRole());
        assertTrue(user.isAllowsDataAccess());
    }

    @Test
    void testToUserResponse() {
        User user = new User();
        user.setId(1);
        user.setUsername("test-user");
        user.setPassword("password");
        user.setRole(Role.ANALYST);
        user.setAllowsDataAccess(true);

        UserResponse userResponse = userMapper.toUserResponse(user);

        assertEquals(1, userResponse.getId());
        assertEquals("test-user", userResponse.getUsername());
        assertEquals(Role.ANALYST, userResponse.getRole());
        assertTrue(userResponse.isAllowsDataAccess());
    }
}