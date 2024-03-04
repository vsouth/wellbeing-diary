package ru.vsouth.wellbeingdiary.dto;

import ru.vsouth.wellbeingdiary.model.Role;

public class UserResponse {
    private final int id;
    private final String username;
    private final Role role;
    private final boolean allowsDataAccess;

    public UserResponse(int id, String username, Role role, boolean allowsDataAccess) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.allowsDataAccess = allowsDataAccess;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return role;
    }

    public boolean isAllowsDataAccess() {
        return allowsDataAccess;
    }
}
