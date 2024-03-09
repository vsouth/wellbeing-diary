package ru.vsouth.wellbeingdiary.utils;

import org.springframework.stereotype.Component;
import ru.vsouth.wellbeingdiary.dto.UserRequest;
import ru.vsouth.wellbeingdiary.dto.UserResponse;
import ru.vsouth.wellbeingdiary.model.User;

import java.util.Optional;

@Component
public class UserMapper {
    public User toUser(UserRequest userRequest) {
        User user = new User();
        user.setId(Optional.ofNullable(userRequest.getId()).orElse(0));
        user.setUsername(userRequest.getUsername());
        user.setPassword(Optional.ofNullable(userRequest.getPassword()).orElse(""));
        user.setRole(userRequest.getRole());
        user.setAllowsDataAccess(userRequest.isAllowsDataAccess());
        return user;
    }

    public UserResponse toUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getRole(),
                user.isAllowsDataAccess()
        );
    }
}
