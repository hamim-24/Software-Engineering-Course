package com.blogapp.ui;

import com.blogapp.dao.UserDao;
import com.blogapp.dao.UserDaoImpl;
import com.blogapp.model.User;

import java.util.List;

/**
 * AdminView — Strategy: renders the admin dashboard.
 *
 * Admins see a list of all registered Authors in the system.
 */
public class AdminView implements View {

    private final UserDao userDao = new UserDaoImpl();

    @Override
    public void render(User user) {
        List<User> authors = userDao.findAllAuthors();

        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.printf ("║  ADMIN DASHBOARD  —  Welcome, %-18s║%n", user.getUsername());
        System.out.println("╚══════════════════════════════════════════════════╝");
        System.out.println();

        if (authors.isEmpty()) {
            System.out.println("  No authors registered yet.");
            return;
        }

        System.out.printf("  %-5s  %-20s  %-10s%n", "ID", "Username", "Role");
        System.out.println("  " + "─".repeat(40));

        for (User author : authors) {
            System.out.printf("  %-5d  %-20s  %-10s%n",
                author.getId(),
                author.getUsername(),
                author.getRole());
        }

        System.out.println("\n  Total authors: " + authors.size());
        System.out.println();
    }
}
