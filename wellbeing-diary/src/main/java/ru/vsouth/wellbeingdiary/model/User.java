package ru.vsouth.wellbeingdiary.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private boolean allowsDataAccess;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DiaryEntry> diaryEntries;

    public User(int id, String username, String password, Role role, boolean allowsDataAccess) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.allowsDataAccess = allowsDataAccess;
    }

    public User() {
    }

    public User(String username, String password, Role role, boolean allowsDataAccess) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.allowsDataAccess = allowsDataAccess;
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
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role=" + role +
                ", allows_data_access=" + allowsDataAccess +
                '}';
    }
}
