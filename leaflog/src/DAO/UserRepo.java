package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Config.database;
import model.User;

public class UserRepo implements UserDao{
	private Connection conn;
	final String insert = "INSERT INTO user (nama, username, password) VALUES (?,?,?);";
	final String update = "UPDATE user SET nama=?, username=?, password=? WHERE id=?;";
	final String select = "SELECT * FROM user WHERE username=? AND password=?;";
 
	public UserRepo() {
		conn = database.koneksi();
	}
	
	@Override
	public void save(User user) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(insert);
			st.setString(1, user.getNama());
			st.setString(2, user.getUsername());
			st.setString(3, user.getPassword());
			st.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				st.close();	
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}	
	}

	@Override
	public void update(User user) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(update);
			st.setString(1, user.getNama());
			st.setString(2, user.getUsername());
			st.setString(3, user.getPassword());
			st.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				st.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	@Override
	public boolean check(User user) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(select);
            st.setString(1, user.getUsername());
            st.setString(2, user.getPassword());
            rs = st.executeQuery();

            return rs.next(); // ✅ true jika user ditemukan
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


	}
