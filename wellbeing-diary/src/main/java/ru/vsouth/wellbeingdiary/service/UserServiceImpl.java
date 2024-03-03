package ru.vsouth.wellbeingdiary.service;

import org.springframework.stereotype.Service;
import ru.vsouth.wellbeingdiary.model.User;
import ru.vsouth.wellbeingdiary.repository.UserRepository;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Boolean existByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public User registerUser(User user) {
        String encodedPassword = user.getPassword();
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
