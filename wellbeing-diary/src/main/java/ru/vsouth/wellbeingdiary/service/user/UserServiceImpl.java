package ru.vsouth.wellbeingdiary.service.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.vsouth.wellbeingdiary.dto.UserRequest;
import ru.vsouth.wellbeingdiary.dto.UserResponse;
import ru.vsouth.wellbeingdiary.model.User;
import ru.vsouth.wellbeingdiary.repository.UserRepository;
import ru.vsouth.wellbeingdiary.utils.UserMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public UserResponse registerUser(UserRequest userRequest) {
        if (!existsByUsername(userRequest.getUsername())) {
            User user = userMapper.toUser(userRequest);
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            User savedUser = userRepository.save(user);
            return userMapper.toUserResponse(savedUser);
        }
        return null;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getUserById(int id) {
        Optional<User> optionalFoundUser = userRepository.findById(id);
        if (optionalFoundUser.isPresent()) {
            User foundUser = optionalFoundUser.get();
            return userMapper.toUserResponse(foundUser);
        } else {
            return null;
        }
    }

    @Override
    public UserResponse getUserByUsername(String username) {
        Optional<User> optionalFoundUser = userRepository.findByUsername(username);
        if (optionalFoundUser.isPresent()) {
            User foundUser = optionalFoundUser.get();
            return userMapper.toUserResponse(foundUser);
        } else {
            return null;
        }
    }

    @Override
    public UserResponse saveUser(UserRequest userRequest) {
        User user = userMapper.toUser(userRequest);
        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }

    @Override
    public UserResponse deleteUser(int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            userRepository.deleteById(id);
            return userMapper.toUserResponse(user);
        } else {
            return null;
        }
    }

    @Override
    public UserResponse updateUser(UserRequest user) {
        Optional<User> optionalExistingUser = userRepository.findById(user.getId());
        if (optionalExistingUser.isPresent()) {
            User existingUser = optionalExistingUser.get();

            existingUser.setUsername(user.getUsername());
            existingUser.setRole(user.getRole());
            existingUser.setAllowsDataAccess(user.isAllowsDataAccess());

            User savedUser = userRepository.save(existingUser);
            return userMapper.toUserResponse(savedUser);
        } else {
            return null;
        }
    }

    @Override
    public UserResponse updateUserPassword(UserRequest user) {
        Optional<User> optionalExistingUser = userRepository.findById(user.getId());
        if (optionalExistingUser.isPresent()) {
            User existingUser = optionalExistingUser.get();

            String encodedPassword = passwordEncoder.encode(user.getPassword());
            existingUser.setPassword(encodedPassword);

            User savedUser = userRepository.save(existingUser);
            return userMapper.toUserResponse(savedUser);
        } else {
            return null;
        }
    }

    @Override
    public Integer getUsersCount() {
        return getAllUsers().size();
    }
}
