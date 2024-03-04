package ru.vsouth.wellbeingdiary.service;

import org.springframework.stereotype.Service;
import ru.vsouth.wellbeingdiary.dto.UserResponse;
import ru.vsouth.wellbeingdiary.model.User;
import ru.vsouth.wellbeingdiary.repository.UserRepository;
import ru.vsouth.wellbeingdiary.utils.UserResponseMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserResponseMapper userResponseMapper;
    // TODO: add password encoder!

    public UserServiceImpl(UserRepository userRepository, UserResponseMapper userResponseMapper) {
        this.userRepository = userRepository;
        this.userResponseMapper = userResponseMapper;
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public UserResponse registerUser(User user) {
        if (!existsByUsername(user.getUsername())) {
            // TODO: add password encoder!
            String encodedPassword = user.getPassword();
            user.setPassword(encodedPassword);
            User savedUser = userRepository.save(user);
            return userResponseMapper.mapUserToUserResponse(savedUser);
        }
        return null;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userResponseMapper::mapUserToUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getUserById(int id) {
        User foundUser = userRepository.findById(id).orElse(null);
        return userResponseMapper.mapUserToUserResponse(foundUser);
    }

    @Override
    public UserResponse getUserByUsername(String username) {
        User foundUser = userRepository.findByUsername(username).orElse(null);
        return userResponseMapper.mapUserToUserResponse(foundUser);
    }

    @Override
    public UserResponse saveUser(User user) {
        User savedUser = userRepository.save(user);
        return userResponseMapper.mapUserToUserResponse(savedUser);
    }

    @Override
    public UserResponse deleteUser(int id) {
        User user = userRepository.findById(id).orElse(null);
        userRepository.deleteById(id);
        return userResponseMapper.mapUserToUserResponse(user);
    }

    @Override
    public UserResponse updateUser(User user) {
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        existingUser.setUsername(user.getUsername());
        existingUser.setRole(user.getRole());
        existingUser.setAllowsDataAccess(user.isAllowsDataAccess());
        User savedUser = userRepository.save(existingUser);
        return userResponseMapper.mapUserToUserResponse(savedUser);
    }

    @Override
    public UserResponse updateUserPassword(User user) {
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        // TODO: add password encoder!
        String encodedPassword = user.getPassword();
        existingUser.setPassword(encodedPassword);
        User savedUser = userRepository.save(existingUser);
        return userResponseMapper.mapUserToUserResponse(savedUser);
    }

    @Override
    public Integer getUsersCount() {
        return getAllUsers().size();
    }
}
