package Config;
import java.sql.*;
import javax.swing.JOptionPane;

public class database {
    Connection conn;
    public static Connection koneksi() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/leaflog",
                    "root","");
            return conn;
        }catch(Exception e) {
            JOptionPane.showInternalMessageDialog(null, e);
            return null;
        }
    }

}