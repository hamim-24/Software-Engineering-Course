package com.blogapp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DatabaseConnection — Singleton pattern.
 *
 * Ensures a single shared SQLite Connection is reused throughout the
 * application lifecycle. The database file 'blog.db' is created in the
 * project root directory the first time the connection is opened.
 *
 * Pattern: Singleton (thread-safe lazy initialization via synchronized)
 */
public class DatabaseConnection {

    private static final String DB_URL = "jdbc:sqlite:blog.db";

    // The one-and-only instance
    private static DatabaseConnection instance;

    // The underlying JDBC connection
    private Connection connection;

    /** Private constructor — no external instantiation allowed. */
    private DatabaseConnection() throws SQLException {
        this.connection = DriverManager.getConnection(DB_URL);
        // Enable foreign key enforcement in SQLite
        this.connection.createStatement().execute("PRAGMA foreign_keys = ON");
    }

    /**
     * Returns the singleton instance, creating it on first call.
     *
     * @return DatabaseConnection singleton
     * @throws SQLException if the connection cannot be established
     */
    public static synchronized DatabaseConnection getInstance() throws SQLException {
        if (instance == null || instance.connection.isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    /**
     * Returns the live JDBC Connection for use by DAOs.
     *
     * @return java.sql.Connection
     */
    public Connection getConnection() {
        return connection;
    }

    /** Closes the connection and clears the singleton instance. */
    public static synchronized void close() {
        if (instance != null) {
            try {
                if (!instance.connection.isClosed()) {
                    instance.connection.close();
                }
            } catch (SQLException e) {
                System.err.println("[DB] Failed to close connection: " + e.getMessage());
            } finally {
                instance = null;
            }
        }
    }
}
