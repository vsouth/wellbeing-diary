package ru.vsouth.wellbeingdiary.service;

import ru.vsouth.wellbeingdiary.dto.UserResponse;
import ru.vsouth.wellbeingdiary.model.User;

import java.util.List;

public interface UserService {
    Boolean existsByUsername(String username);

    UserResponse registerUser(User user);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(int id);

    UserResponse getUserByUsername(String username);

    UserResponse saveUser(User user);

    UserResponse deleteUser(int id);

    UserResponse updateUser(User user);

    UserResponse updateUserPassword (User user);

    Integer getUsersCount();

}
