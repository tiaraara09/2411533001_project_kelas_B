package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private Connection conn;

    public boolean connect() {
        try {
            String url = "jdbc:mysql://localhost/laundy_apps";
            String username = "root";
            String password = "";

            conn = DriverManager.getConnection(url, username, password);

            if (conn != null && !conn.isClosed()) {
                System.out.println("Koneksi ke database berhasil!");
                return true;
            } else {
                System.out.println("Koneksi gagal.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error koneksi: " + e.getMessage());
            return false;
        }
    }

    // Method untuk menutup koneksi
    @SuppressWarnings("CallToPrintStackTrace")
    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Koneksi ditutup.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return conn;
    }

    // Main untuk test koneksi
    public static void main(String[] args) {
        DatabaseConnection db = new DatabaseConnection();
        boolean status = db.connect();
        System.out.println("Status koneksi: " + status);
        db.closeConnection();
    }
}
