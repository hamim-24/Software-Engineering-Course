package com.blogapp.db;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * DatabaseSeeder — creates tables and inserts sample data.
 *
 * Tables created:
 *   users  (id, username, password_hash, role)
 *   blogs  (id, title, content, author_id FK→users.id, created_at)
 *
 * Sample data:
 *   Users:  admin (ADMIN), alice (AUTHOR), bob (AUTHOR), charlie (GUEST)
 *   Blogs:  5 blogs split between alice and bob
 */
public class DatabaseSeeder {

    /**
     * Creates tables if they do not exist, then seeds sample data
     * only if the tables are currently empty (idempotent).
     */
    public static void seed() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║         Database Seeder               ║");
        System.out.println("╚══════════════════════════════════════╝");

        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            createTables(conn);
            insertSampleData(conn);
        } catch (Exception e) {
            System.err.println("[Seeder] Error: " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────────
    //  DDL – Create tables
    // ─────────────────────────────────────────────

    private static void createTables(Connection conn) throws Exception {
        Statement stmt = conn.createStatement();

        // users table
        stmt.execute("""
            CREATE TABLE IF NOT EXISTS users (
                id            INTEGER PRIMARY KEY AUTOINCREMENT,
                username      TEXT    NOT NULL UNIQUE,
                password_hash TEXT    NOT NULL,
                role          TEXT    NOT NULL CHECK(role IN ('ADMIN','AUTHOR','GUEST'))
            )
        """);

        // blogs table — author_id is a foreign key referencing users.id
        stmt.execute("""
            CREATE TABLE IF NOT EXISTS blogs (
                id         INTEGER PRIMARY KEY AUTOINCREMENT,
                title      TEXT    NOT NULL,
                content    TEXT    NOT NULL,
                author_id  INTEGER NOT NULL,
                created_at TEXT    NOT NULL DEFAULT (datetime('now')),
                FOREIGN KEY (author_id) REFERENCES users(id) ON DELETE CASCADE
            )
        """);

        System.out.println("[✓] Tables verified / created (users, blogs)");
    }

    // ─────────────────────────────────────────────
    //  DML – Insert sample records (idempotent)
    // ─────────────────────────────────────────────

    private static void insertSampleData(Connection conn) throws Exception {
        // Check if users table already has rows
        ResultSet rs = conn.createStatement().executeQuery("SELECT COUNT(*) AS cnt FROM users");
        if (rs.next() && rs.getInt("cnt") > 0) {
            System.out.println("[i] Sample data already present — skipping seed.\n");
            return;
        }

        // ── Insert users ──────────────────────────────────────────
        String insertUser = "INSERT INTO users (username, password_hash, role) VALUES (?, ?, ?)";

        int adminId   = insertUser(conn, insertUser, "admin",   "admin123",   "ADMIN");
        int aliceId   = insertUser(conn, insertUser, "alice",   "alice123",   "AUTHOR");
        int bobId     = insertUser(conn, insertUser, "bob",     "bob123",     "AUTHOR");
        /*int charlieId =*/ insertUser(conn, insertUser, "charlie", "charlie123", "GUEST");

        System.out.println("[✓] Users inserted: admin, alice, bob, charlie");

        // ── Insert blogs ──────────────────────────────────────────
        String insertBlog =
            "INSERT INTO blogs (title, content, author_id, created_at) VALUES (?, ?, ?, ?)";

        insertBlog(conn, insertBlog,
            "Getting Started with Java",
            "Java is a versatile, object-oriented programming language...",
            aliceId, "2024-01-10 09:00:00");

        insertBlog(conn, insertBlog,
            "Design Patterns Explained",
            "Design patterns are reusable solutions to common software design problems...",
            aliceId, "2024-02-14 11:30:00");

        insertBlog(conn, insertBlog,
            "SQLite with JDBC",
            "SQLite is a lightweight, serverless database engine perfect for desktop apps...",
            aliceId, "2024-03-05 14:00:00");

        insertBlog(conn, insertBlog,
            "Maven Project Setup",
            "Apache Maven is a powerful project management tool based on the POM concept...",
            bobId, "2024-03-20 16:00:00");

        insertBlog(conn, insertBlog,
            "Understanding Foreign Keys",
            "Foreign keys enforce referential integrity between related tables in a database...",
            bobId, "2024-04-01 08:45:00");

        System.out.println("[✓] Blogs inserted: 3 by alice, 2 by bob");
        System.out.println();
    }

    /** Inserts a user and returns the generated id. */
    private static int insertUser(Connection conn, String sql,
                                  String username, String plainPw, String role)
            throws Exception {
        PreparedStatement ps = conn.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, username);
        ps.setString(2, BCrypt.hashpw(plainPw, BCrypt.gensalt()));
        ps.setString(3, role);
        ps.executeUpdate();
        ResultSet keys = ps.getGeneratedKeys();
        return keys.next() ? keys.getInt(1) : -1;
    }

    /** Inserts a blog post. */
    private static void insertBlog(Connection conn, String sql,
                                   String title, String content,
                                   int authorId, String createdAt)
            throws Exception {
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, content);
        ps.setInt(3, authorId);
        ps.setString(4, createdAt);
        ps.executeUpdate();
    }
}
