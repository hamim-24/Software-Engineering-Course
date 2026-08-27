package com.blogapp.service;

import com.blogapp.dao.UserDao;
import com.blogapp.dao.UserDaoImpl;
import com.blogapp.model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Optional;

/**
 * AuthService — handles user registration and login.
 *
 * Password Security:
 *   Passwords are hashed with BCrypt (work factor 12) before storage.
 *   During login, BCrypt.checkpw() compares the submitted password
 *   against the stored hash — the plain-text is never retained.
 *
 * This class uses UserDao to remain decoupled from the database layer.
 */
public class AuthService {

    private final UserDao userDao;

    public AuthService() {
        this.userDao = new UserDaoImpl();
    }

    // ─────────────────────────────────────────────
    //  Register
    // ─────────────────────────────────────────────

    /**
     * Registers a new user.
     *
     * @param username  desired username (must be unique)
     * @param password  plain-text password
     * @param role      "AUTHOR" or "GUEST"
     * @return the newly created User, or empty if username is taken
     */
    public Optional<User> register(String username, String password, String role) {
        // Check for duplicate username
        if (userDao.findByUsername(username).isPresent()) {
            return Optional.empty();   // username already taken
        }

        // Hash password before storage
        String hash = BCrypt.hashpw(password, BCrypt.gensalt(12));
        User created = userDao.save(username, hash, role);
        return Optional.of(created);
    }

    // ─────────────────────────────────────────────
    //  Login
    // ─────────────────────────────────────────────

    /**
     * Authenticates a user.
     *
     * @param username  submitted username
     * @param password  submitted plain-text password
     * @return the authenticated User, or empty if credentials are invalid
     */
    public Optional<User> login(String username, String password) {
        Optional<User> found = userDao.findByUsername(username);
        if (found.isEmpty()) {
            return Optional.empty();   // no such user
        }

        User user = found.get();
        // BCrypt.checkpw() handles timing-safe comparison
        if (BCrypt.checkpw(password, user.getPasswordHash())) {
            return Optional.of(user);
        }
        return Optional.empty();       // wrong password
    }
}
