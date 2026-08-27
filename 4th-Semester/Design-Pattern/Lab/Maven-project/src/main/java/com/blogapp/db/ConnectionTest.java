package com.blogapp.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * ConnectionTest — verifies the SQLite JDBC connection is working
 * by executing a trivial arithmetic query: SELECT 2 + 2 AS result
 *
 * If the returned value equals 4, the connection is confirmed working.
 */
public class ConnectionTest {

    /**
     * Runs the connection test.
     *
     * @return true if the query returns 4, false otherwise
     */
    public static boolean run() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║       SQLite Connection Test          ║");
        System.out.println("╚══════════════════════════════════════╝");

        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            Statement  stmt = conn.createStatement();
            ResultSet  rs   = stmt.executeQuery("SELECT 2 + 2 AS result");

            if (rs.next()) {
                int result = rs.getInt("result");
                if (result == 4) {
                    System.out.println("[✓] Query: SELECT 2 + 2 AS result");
                    System.out.println("[✓] Result: " + result + "  →  Connection is WORKING!\n");
                    return true;
                } else {
                    System.out.println("[✗] Unexpected result: " + result);
                }
            }
        } catch (Exception e) {
            System.err.println("[✗] Connection test FAILED: " + e.getMessage());
        }
        return false;
    }
}
