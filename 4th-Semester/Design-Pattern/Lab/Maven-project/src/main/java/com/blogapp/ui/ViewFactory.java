package com.blogapp.ui;

import com.blogapp.model.Role;

/**
 * ViewFactory — Factory Method pattern.
 *
 * Creates the appropriate View implementation based on the user's Role.
 * This decouples the main application from the concrete View classes —
 * adding a new role only requires adding a new View class and a case here.
 *
 * Pattern: Factory Method
 *   createView(Role) is the factory method. It decides which concrete
 *   product (View implementation) to instantiate.
 */
public class ViewFactory {

    /**
     * Returns the appropriate View strategy for the given role.
     *
     * @param role the authenticated user's role
     * @return a View implementation matching the role
     * @throws IllegalArgumentException for unsupported roles
     */
    public static View createView(Role role) {
        return switch (role) {
            case ADMIN  -> new AdminView();
            case AUTHOR -> new AuthorView();
            case GUEST  -> new GuestView();
        };
    }
}
