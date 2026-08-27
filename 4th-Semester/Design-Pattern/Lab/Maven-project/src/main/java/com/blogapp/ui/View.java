package com.blogapp.ui;

import com.blogapp.model.User;

/**
 * View — Strategy interface for role-based dashboard views.
 *
 * Pattern: Strategy
 *   Each concrete View (AdminView, AuthorView, GuestView) encapsulates
 *   the display algorithm for a specific role. The client (App) only
 *   depends on this interface, unaware of which concrete View is used.
 *
 * @see AdminView
 * @see AuthorView
 * @see GuestView
 */
public interface View {

    /**
     * Renders the dashboard for the given logged-in user.
     *
     * @param user the currently authenticated user
     */
    void render(User user);
}
