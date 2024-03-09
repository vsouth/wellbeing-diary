package ru.vsouth.wellbeingdiary.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.vsouth.wellbeingdiary.model.Role;
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequest {
    private final Integer id;
    private final String username;
    private final String password;
    private final Role role;
    private final boolean allowsDataAccess;

    public UserRequest(Integer id, String username, String password, Role role, boolean allowsDataAccess) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.allowsDataAccess = allowsDataAccess;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public boolean isAllowsDataAccess() {
        return allowsDataAccess;
    }
}
