package com.blogapp.ui;

import com.blogapp.dao.BlogDao;
import com.blogapp.dao.BlogDaoImpl;
import com.blogapp.model.Blog;
import com.blogapp.model.User;

import java.util.List;

/**
 * AuthorView — Strategy: renders the author's personal blog dashboard.
 *
 * Authors see only their own blogs. The blog list is filtered by
 * the logged-in user's id via BlogDao.findByAuthorId().
 */
public class AuthorView implements View {

    private final BlogDao blogDao = new BlogDaoImpl();

    @Override
    public void render(User user) {
        List<Blog> blogs = blogDao.findByAuthorId(user.getId());

        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.printf ("║  AUTHOR DASHBOARD  —  Welcome, %-17s║%n", user.getUsername());
        System.out.println("╚══════════════════════════════════════════════════╝");
        System.out.println("  Showing: Your blogs only");
        System.out.println();

        if (blogs.isEmpty()) {
            System.out.println("  You have not written any blogs yet.");
            return;
        }

        printBlogs(blogs);
        System.out.println("  Total: " + blogs.size() + " blog(s)");
        System.out.println();
    }

    static void printBlogs(List<Blog> blogs) {
        for (int i = 0; i < blogs.size(); i++) {
            Blog b = blogs.get(i);
            System.out.println("  ┌─ [" + (i + 1) + "] " + b.getTitle());
            System.out.println("  │  Author   : " + b.getAuthorName());
            System.out.println("  │  Published: " + b.getCreatedAt());
            System.out.println("  │  " + truncate(b.getContent(), 80));
            System.out.println("  └" + "─".repeat(50));
            System.out.println();
        }
    }

    /** Truncates content and appends "..." if longer than maxLen. */
    private static String truncate(String text, int maxLen) {
        if (text.length() <= maxLen) return text;
        return text.substring(0, maxLen - 3) + "...";
    }
}
