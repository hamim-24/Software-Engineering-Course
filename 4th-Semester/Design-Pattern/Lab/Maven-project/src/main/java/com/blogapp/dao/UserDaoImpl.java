package com.blogapp.dao;

import com.blogapp.db.DatabaseConnection;
import com.blogapp.model.Role;
import com.blogapp.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * UserDaoImpl — SQLite implementation of UserDao.
 *
 * Uses PreparedStatements to prevent SQL injection.
 * Role is stored as a TEXT string in SQLite and parsed to the Role enum on read.
 */
public class UserDaoImpl implements UserDao {

    // ─────────────────────────────────────────────
    //  findByUsername
    // ─────────────────────────────────────────────

    @Override
    public Optional<User> findByUsername(String username) {
        String sql = "SELECT id, username, password_hash, role FROM users WHERE username = ?";
        try {
            PreparedStatement ps = getConn().prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(mapRow(rs));
            }
        } catch (SQLException e) {
            System.err.println("[UserDao] findByUsername error: " + e.getMessage());
        }
        return Optional.empty();
    }

    // ─────────────────────────────────────────────
    //  save
    // ─────────────────────────────────────────────

    @Override
    public User save(String username, String passwordHash, String role) {
        String sql = "INSERT INTO users (username, password_hash, role) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = getConn().prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, username);
            ps.setString(2, passwordHash);
            ps.setString(3, role);
            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                int id = keys.getInt(1);
                return new User(id, username, passwordHash, Role.valueOf(role));
            }
        } catch (SQLException e) {
            System.err.println("[UserDao] save error: " + e.getMessage());
        }
        throw new RuntimeException("Failed to save user: " + username);
    }

    // ─────────────────────────────────────────────
    //  findAllAuthors
    // ─────────────────────────────────────────────

    @Override
    public List<User> findAllAuthors() {
        String sql = "SELECT id, username, password_hash, role FROM users WHERE role = 'AUTHOR' ORDER BY username";
        List<User> authors = new ArrayList<>();
        try {
            ResultSet rs = getConn().createStatement().executeQuery(sql);
            while (rs.next()) {
                authors.add(mapRow(rs));
            }
        } catch (SQLException e) {
            System.err.println("[UserDao] findAllAuthors error: " + e.getMessage());
        }
        return authors;
    }

    // ─────────────────────────────────────────────
    //  Helpers
    // ─────────────────────────────────────────────

    /** Maps the current ResultSet row to a User object. */
    private User mapRow(ResultSet rs) throws SQLException {
        return new User(
            rs.getInt("id"),
            rs.getString("username"),
            rs.getString("password_hash"),
            Role.valueOf(rs.getString("role"))
        );
    }

    private Connection getConn() throws SQLException {
        return DatabaseConnection.getInstance().getConnection();
    }
}
