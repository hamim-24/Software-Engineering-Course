package com.blogapp.ui;

import com.blogapp.dao.BlogDao;
import com.blogapp.dao.BlogDaoImpl;
import com.blogapp.model.Blog;
import com.blogapp.model.User;

import java.util.List;
import java.util.Scanner;

/**
 * GuestView — Strategy: renders the public blog list with pagination.
 *
 * Guests see ALL blogs, paginated at 5 per page.
 * They can navigate forward/backward or quit back to main menu.
 */
public class GuestView implements View {

    private static final int PAGE_SIZE = 5;
    private final BlogDao   blogDao   = new BlogDaoImpl();
    private final Scanner   scanner   = new Scanner(System.in);

    @Override
    public void render(User user) {
        int totalBlogs = blogDao.countAll();
        int totalPages = (int) Math.ceil((double) totalBlogs / PAGE_SIZE);

        if (totalBlogs == 0) {
            System.out.println("\n  No blogs available yet.\n");
            return;
        }

        int currentPage = 1;

        while (true) {
            List<Blog> blogs = blogDao.findAllPaginated(currentPage, PAGE_SIZE);

            System.out.println("\n╔══════════════════════════════════════════════════╗");
            System.out.printf ("║  GUEST DASHBOARD  —  Welcome, %-18s║%n",
                    user != null ? user.getUsername() : "Guest");
            System.out.println("╠══════════════════════════════════════════════════╣");
            System.out.printf ("║  All Blogs  —  Page %2d of %-2d  (%d total)          ║%n",
                    currentPage, totalPages, totalBlogs);
            System.out.println("╚══════════════════════════════════════════════════╝");
            System.out.println();

            AuthorView.printBlogs(blogs);

            // ── Navigation prompt ───────────────────────────────
            System.out.print("  Navigation  [N]ext  [P]rev  [Q]uit  → ");
            String input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "n" -> {
                    if (currentPage < totalPages) {
                        currentPage++;
                    } else {
                        System.out.println("  (Already on the last page)");
                    }
                }
                case "p" -> {
                    if (currentPage > 1) {
                        currentPage--;
                    } else {
                        System.out.println("  (Already on the first page)");
                    }
                }
                case "q" -> {
                    System.out.println();
                    return;
                }
                default -> System.out.println("  Invalid input. Use N, P, or Q.");
            }
        }
    }
}
