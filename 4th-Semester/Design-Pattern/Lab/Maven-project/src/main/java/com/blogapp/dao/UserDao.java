package com.blogapp.dao;

import com.blogapp.model.User;

import java.util.List;
import java.util.Optional;

/**
 * UserDao — Data Access Object interface for the 'users' table.
 *
 * Pattern: DAO (Repository)
 *   Abstracts persistence details from business logic. Implementations
 *   may target SQLite, MySQL, or any other backend without changing
 *   the service layer.
 */
public interface UserDao {

    /**
     * Finds a user by their username.
     *
     * @param username the username to look up
     * @return Optional containing the User if found, empty otherwise
     */
    Optional<User> findByUsername(String username);

    /**
     * Persists a new user record.
     *
     * @param username     the chosen username
     * @param passwordHash bcrypt hash of the password
     * @param role         ADMIN | AUTHOR | GUEST
     * @return the newly created User with its generated id
     */
    User save(String username, String passwordHash, String role);

    /**
     * Returns all users with the AUTHOR role.
     *
     * @return list of authors
     */
    List<User> findAllAuthors();
}
