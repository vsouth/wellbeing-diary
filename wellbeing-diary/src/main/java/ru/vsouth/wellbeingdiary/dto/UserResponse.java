package ru.vsouth.wellbeingdiary.dto;

import ru.vsouth.wellbeingdiary.model.Role;

public class UserResponse {
    private int id;
    private String username;
    private Role role;
    private boolean allowsDataAccess;

    public UserResponse(int id, String username, Role role, boolean allowsDataAccess) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.allowsDataAccess = allowsDataAccess;
    }

    public UserResponse() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isAllowsDataAccess() {
        return allowsDataAccess;
    }

    public void setAllowsDataAccess(boolean allowsDataAccess) {
        this.allowsDataAccess = allowsDataAccess;
    }
}
