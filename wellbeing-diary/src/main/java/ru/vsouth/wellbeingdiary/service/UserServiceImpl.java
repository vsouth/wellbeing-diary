package ru.vsouth.wellbeingdiary.service;

import org.springframework.stereotype.Service;
import ru.vsouth.wellbeingdiary.dto.UserResponse;
import ru.vsouth.wellbeingdiary.model.User;
import ru.vsouth.wellbeingdiary.repository.UserRepository;
import ru.vsouth.wellbeingdiary.utils.UserResponseMapper;

import java.util.List;
import java.util.Optional;
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
        Optional<User> optionalFoundUser = userRepository.findById(id);
        if (optionalFoundUser.isPresent()) {
            User foundUser = optionalFoundUser.get();
            return userResponseMapper.mapUserToUserResponse(foundUser);
        } else {
            return null;
        }
    }

    @Override
    public UserResponse getUserByUsername(String username) {
        Optional<User> optionalFoundUser = userRepository.findByUsername(username);
        if (optionalFoundUser.isPresent()) {
            User foundUser = optionalFoundUser.get();
            return userResponseMapper.mapUserToUserResponse(foundUser);
        } else {
            return null;
        }
    }

    @Override
    public UserResponse saveUser(User user) {
        User savedUser = userRepository.save(user);
        return userResponseMapper.mapUserToUserResponse(savedUser);
    }

    @Override
    public UserResponse deleteUser(int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            userRepository.deleteById(id);
            return userResponseMapper.mapUserToUserResponse(user);
        } else {
            return null;
        }
    }

    @Override
    public UserResponse updateUser(User user) {
        Optional<User> optionalExistingUser = userRepository.findById(user.getId());
        if (optionalExistingUser.isPresent()) {
            User existingUser = optionalExistingUser.get();

            existingUser.setUsername(user.getUsername());
            existingUser.setRole(user.getRole());
            existingUser.setAllowsDataAccess(user.isAllowsDataAccess());

            User savedUser = userRepository.save(existingUser);
            return userResponseMapper.mapUserToUserResponse(savedUser);
        } else {
            return null;
        }
    }

    @Override
    public UserResponse updateUserPassword(User user) {
        Optional<User> optionalExistingUser = userRepository.findById(user.getId());
        if (optionalExistingUser.isPresent()) {
            User existingUser = optionalExistingUser.get();

            // TODO: add password encoder!
            String encodedPassword = user.getPassword();
            existingUser.setPassword(encodedPassword);

            User savedUser = userRepository.save(existingUser);
            return userResponseMapper.mapUserToUserResponse(savedUser);
        } else {
            return null;
        }
    }

    @Override
    public Integer getUsersCount() {
        return getAllUsers().size();
    }
}
