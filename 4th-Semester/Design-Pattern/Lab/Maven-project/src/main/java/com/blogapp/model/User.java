package com.blogapp.model;

/**
 * User model — represents a row in the 'users' table.
 */
public class User {
    private final int    id;
    private final String username;
    private final String passwordHash;
    private final Role   role;

    public User(int id, String username, String passwordHash, Role role) {
        this.id           = id;
        this.username     = username;
        this.passwordHash = passwordHash;
        this.role         = role;
    }

    public int    getId()           { return id; }
    public String getUsername()     { return username; }
    public String getPasswordHash() { return passwordHash; }
    public Role   getRole()         { return role; }

    @Override
    public String toString() {
        return "User{id=" + id + ", username='" + username + "', role=" + role + "}";
    }
}
