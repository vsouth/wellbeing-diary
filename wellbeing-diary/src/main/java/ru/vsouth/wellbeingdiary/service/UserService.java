package ru.vsouth.wellbeingdiary.service;

import ru.vsouth.wellbeingdiary.model.User;

import java.util.List;

public interface UserService {
    Boolean existByUsername(String username);

    User registerUser(User user);

    List<User> getAllUsers();
}
