package ru.vsouth.wellbeingdiary.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.vsouth.wellbeingdiary.model.user.Role;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequest {
    private Integer id;
    private String username;
    private String password;
    private Role role;
    private boolean allowsDataAccess;

    public UserRequest(Integer id, String username, String password, Role role, boolean allowsDataAccess) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.allowsDataAccess = allowsDataAccess;
    }

    public UserRequest() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        UserRequest that = (UserRequest) o;
        return isAllowsDataAccess() == that.isAllowsDataAccess() && Objects.equals(getId(), that.getId()) && getUsername().equals(that.getUsername()) && Objects.equals(getPassword(), that.getPassword()) && getRole() == that.getRole();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getPassword(), getRole(), isAllowsDataAccess());
    }
}
