package ru.vsouth.wellbeingdiary.service.user;

import ru.vsouth.wellbeingdiary.dto.UserRequest;
import ru.vsouth.wellbeingdiary.dto.UserResponse;

import java.util.List;

public interface UserService {
    Boolean existsByUsername(String username);

    UserResponse registerUser(UserRequest user);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(int id);

    UserResponse getUserByUsername(String username);

    UserResponse saveUser(UserRequest user);

    UserResponse deleteUser(int id);

    UserResponse updateUser(UserRequest user);

    UserResponse updateUserPassword (UserRequest user);

    Integer getUsersCount();

}
