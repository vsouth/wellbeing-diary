package ru.vsouth.wellbeingdiary.dto.user;

import ru.vsouth.wellbeingdiary.model.user.Role;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserResponse that = (UserResponse) o;
        return getId() == that.getId() && isAllowsDataAccess() == that.isAllowsDataAccess() && Objects.equals(getUsername(), that.getUsername()) && getRole() == that.getRole();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getRole(), isAllowsDataAccess());
    }
}
