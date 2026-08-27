package com.blogapp;

import com.blogapp.db.ConnectionTest;
import com.blogapp.db.DatabaseConnection;
import com.blogapp.db.DatabaseSeeder;
import com.blogapp.model.User;
import com.blogapp.service.AuthService;
import com.blogapp.ui.View;
import com.blogapp.ui.ViewFactory;

import java.util.Optional;
import java.util.Scanner;

/**
 * App — Main entry point for the Maven SQLite Blog Application.
 *
 * Startup sequence:
 *   1. Run SQLite connection test (SELECT 2 + 2 AS result)
 *   2. Run database seeder (create tables + insert sample data)
 *   3. Show main menu: Login / Register / Exit
 *   4. After successful auth, dispatch to the role-specific View
 *      via ViewFactory (Factory Method) which implements the View
 *      interface (Strategy pattern).
 *
 * Design Patterns Used:
 *   ┌─ Singleton     → DatabaseConnection
 *   ├─ DAO           → UserDao / BlogDao + Impl classes
 *   ├─ Factory Method→ ViewFactory.createView(Role)
 *   └─ Strategy      → View interface (AdminView, AuthorView, GuestView)
 */
public class App {

    private static final Scanner    scanner     = new Scanner(System.in);
    private static final AuthService authService = new AuthService();

    public static void main(String[] args) {

        printBanner();

        // ── Step 1: Test connection ───────────────────────────
        boolean ok = ConnectionTest.run();
        if (!ok) {
            System.err.println("Aborting: database connection failed.");
            return;
        }

        // ── Step 2: Seed database ─────────────────────────────
        DatabaseSeeder.seed();

        // ── Step 3: Main menu loop ────────────────────────────
        while (true) {
            printMainMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> handleLogin();
                case "2" -> handleRegister();
                case "3" -> {
                    System.out.println("\n  Goodbye!\n");
                    DatabaseConnection.close();
                    return;
                }
                default  -> System.out.println("\n  [!] Invalid option. Please enter 1, 2, or 3.\n");
            }
        }
    }

    // ─────────────────────────────────────────────
    //  Login flow
    // ─────────────────────────────────────────────

    private static void handleLogin() {
        System.out.println("\n── Login ──────────────────────────────────────────");
        System.out.print("  Username : ");
        String username = scanner.nextLine().trim();
        System.out.print("  Password : ");
        String password = scanner.nextLine().trim();

        Optional<User> result = authService.login(username, password);
        if (result.isEmpty()) {
            System.out.println("\n  [✗] Invalid username or password.\n");
            return;
        }

        User user = result.get();
        System.out.println("\n  [✓] Login successful!  Role: " + user.getRole());

        // Factory Method picks the right Strategy based on role
        View view = ViewFactory.createView(user.getRole());
        view.render(user);
    }

    // ─────────────────────────────────────────────
    //  Register flow
    // ─────────────────────────────────────────────

    private static void handleRegister() {
        System.out.println("\n── Register ────────────────────────────────────────");
        System.out.print("  Username     : ");
        String username = scanner.nextLine().trim();

        if (username.isBlank()) {
            System.out.println("\n  [!] Username cannot be empty.\n");
            return;
        }

        System.out.print("  Password     : ");
        String password = scanner.nextLine().trim();

        if (password.length() < 4) {
            System.out.println("\n  [!] Password must be at least 4 characters.\n");
            return;
        }

        System.out.println("  Role options : [1] Author   [2] Guest");
        System.out.print("  Choose role  : ");
        String roleChoice = scanner.nextLine().trim();

        String role = switch (roleChoice) {
            case "1" -> "AUTHOR";
            case "2" -> "GUEST";
            default  -> null;
        };

        if (role == null) {
            System.out.println("\n  [!] Invalid role selection.\n");
            return;
        }

        Optional<User> created = authService.register(username, password, role);
        if (created.isEmpty()) {
            System.out.println("\n  [✗] Username '" + username + "' is already taken.\n");
        } else {
            System.out.println("\n  [✓] Account created!  You can now log in.\n");
        }
    }

    // ─────────────────────────────────────────────
    //  Helpers
    // ─────────────────────────────────────────────

    private static void printBanner() {
        System.out.println();
        System.out.println("  ╔══════════════════════════════════════════════════╗");
        System.out.println("  ║       Maven SQLite Blog Application              ║");
        System.out.println("  ║  Design Patterns: Singleton · DAO · Factory · Strategy  ║");
        System.out.println("  ╚══════════════════════════════════════════════════╝");
        System.out.println();
    }

    private static void printMainMenu() {
        System.out.println("┌─────────────────────────────────┐");
        System.out.println("│           MAIN MENU             │");
        System.out.println("├─────────────────────────────────┤");
        System.out.println("│  [1]  Login                     │");
        System.out.println("│  [2]  Register                  │");
        System.out.println("│  [3]  Exit                      │");
        System.out.println("└─────────────────────────────────┘");
        System.out.print("  Choose an option: ");
    }
}
