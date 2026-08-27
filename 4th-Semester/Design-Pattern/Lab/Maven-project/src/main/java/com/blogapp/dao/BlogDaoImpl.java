package com.blogapp.dao;

import com.blogapp.db.DatabaseConnection;
import com.blogapp.model.Blog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * BlogDaoImpl — SQLite implementation of BlogDao.
 *
 * Uses a JOIN between blogs and users to fetch the author's username
 * without requiring the caller to perform a separate lookup.
 *
 * Pagination is implemented using LIMIT + OFFSET in SQLite.
 */
public class BlogDaoImpl implements BlogDao {

    // ─────────────────────────────────────────────
    //  findAllPaginated
    // ─────────────────────────────────────────────

    @Override
    public List<Blog> findAllPaginated(int page, int pageSize) {
        String sql = """
            SELECT b.id, b.title, b.content, b.author_id, u.username AS author_name, b.created_at
            FROM blogs b
            JOIN users u ON b.author_id = u.id
            ORDER BY b.created_at DESC
            LIMIT ? OFFSET ?
        """;
        List<Blog> blogs = new ArrayList<>();
        try {
            PreparedStatement ps = getConn().prepareStatement(sql);
            ps.setInt(1, pageSize);
            ps.setInt(2, (page - 1) * pageSize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                blogs.add(mapRow(rs));
            }
        } catch (SQLException e) {
            System.err.println("[BlogDao] findAllPaginated error: " + e.getMessage());
        }
        return blogs;
    }

    // ─────────────────────────────────────────────
    //  findByAuthorId
    // ─────────────────────────────────────────────

    @Override
    public List<Blog> findByAuthorId(int authorId) {
        String sql = """
            SELECT b.id, b.title, b.content, b.author_id, u.username AS author_name, b.created_at
            FROM blogs b
            JOIN users u ON b.author_id = u.id
            WHERE b.author_id = ?
            ORDER BY b.created_at DESC
        """;
        List<Blog> blogs = new ArrayList<>();
        try {
            PreparedStatement ps = getConn().prepareStatement(sql);
            ps.setInt(1, authorId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                blogs.add(mapRow(rs));
            }
        } catch (SQLException e) {
            System.err.println("[BlogDao] findByAuthorId error: " + e.getMessage());
        }
        return blogs;
    }

    // ─────────────────────────────────────────────
    //  countAll
    // ─────────────────────────────────────────────

    @Override
    public int countAll() {
        try {
            ResultSet rs = getConn().createStatement()
                    .executeQuery("SELECT COUNT(*) AS cnt FROM blogs");
            if (rs.next()) return rs.getInt("cnt");
        } catch (SQLException e) {
            System.err.println("[BlogDao] countAll error: " + e.getMessage());
        }
        return 0;
    }

    // ─────────────────────────────────────────────
    //  Helpers
    // ─────────────────────────────────────────────

    /** Maps the current ResultSet row to a Blog object. */
    private Blog mapRow(ResultSet rs) throws SQLException {
        return new Blog(
            rs.getInt("id"),
            rs.getString("title"),
            rs.getString("content"),
            rs.getInt("author_id"),
            rs.getString("author_name"),
            rs.getString("created_at")
        );
    }

    private Connection getConn() throws SQLException {
        return DatabaseConnection.getInstance().getConnection();
    }
}
