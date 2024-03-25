package ru.vsouth.wellbeingdiary.service.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.vsouth.wellbeingdiary.dto.user.UserRequest;
import ru.vsouth.wellbeingdiary.dto.user.UserResponse;
import ru.vsouth.wellbeingdiary.model.user.User;
import ru.vsouth.wellbeingdiary.repository.UserRepository;
import ru.vsouth.wellbeingdiary.utils.user.UserMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Сервис работы с пользователями
 */
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

    /**
     * Метод для проверки существования пользователя по имени
     *
     * @param username Имя пользователя
     * @return true, если пользователь с указанным именем существует, false, если нет
     */
    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    /**
     * Метод для регистрации пользователя
     *
     * @param userRequest Запрос на регистрацию нового пользователя
     * @return Информация о пользователе, или null, если пользователь уже существует
     */
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

    /**
     * Метод для получения списка всех пользователей
     *
     * @return Список информации о пользователях
     */
    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());
    }

    /**
     * Метод для получения пользователя по идентификатору
     *
     * @param id Идентификатор пользователя
     * @return Информация о пользователе, или null, если пользователь не найден
     */
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

    /**
     * Метод для получения пользователя по имени
     *
     * @param username Имя пользователя
     * @return Информация о пользователе, или null, если пользователь не найден
     */
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

    /**
     * Метод для добавления нового пользователя
     *
     * @param userRequest Запрос на сохранение нового пользователя
     * @return Информация о сохраненном пользователе
     */
    @Override
    public UserResponse saveUser(UserRequest userRequest) {
        User user = userMapper.toUser(userRequest);
        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }

    /**
     * Метод для удаления пользователя по идентификатору
     *
     * @param id Идентификатор пользователя
     * @return Информация об удаленном пользователе, или null, если пользователь не найден
     */
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

    /**
     * Метод для обновления информации о пользователе
     *
     * @param user Запрос на обновление информации о пользователе
     * @return Обновленная информация о пользователе, или null, если пользователь не найден
     */
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

    /**
     * Метод для обновления пароля пользователя
     *
     * @param user Запрос на обновление пароля пользователя
     * @return Информация о пользователе, или null, если пользователь не найден
     */
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

    /**
     * Метод для получения общего количества зарегистрированных пользователей
     *
     * @return Общее количество зарегистрированных пользователей
     */
    @Override
    public Integer getUsersCount() {
        return getAllUsers().size();
    }
}
