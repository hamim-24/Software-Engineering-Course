package com.blogapp.model;

/**
 * Role enum representing the three user roles in the system.
 *
 * Design Note:
 *   Used by the Factory Method pattern (ViewFactory) to select the
 *   correct View strategy at runtime.
 */
public enum Role {
    ADMIN,
    AUTHOR,
    GUEST
}
