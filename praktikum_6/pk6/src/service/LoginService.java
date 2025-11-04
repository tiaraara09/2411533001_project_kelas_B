package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.User;


public class LoginService {

    public static boolean authneticate(User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    private Connection conn;

    @SuppressWarnings("UseSpecificCatch")
    public LoginService() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // pastikan driver MySQL dimuat
            String url = "jdbc:mysql://localhost:3306/laundy_apps";
            String username = "root";
            String password = "";
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Koneksi ke database berhasil!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Koneksi ke database gagal!");
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean authenticate(User user) {
        String query = "SELECT * FROM user WHERE username = ? AND password = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());

            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings({"CallToPrintStackTrace", "UseSpecificCatch"})
    public void closeConnection() {
        try {
            if(conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Koneksi ditutup.");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // Optional: cek koneksi
    @SuppressWarnings("UseSpecificCatch")
    public boolean isConnected() {
        try {
            return conn != null && !conn.isClosed();
        } catch(Exception e) {
            return false;
        }
    }
}
