package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import confg.database;
import model.User;

public class UserRepo implements UserDAO{
	private Connection connection;
	final String insert ="INSERT INTO user (name, username, password) VALUES (?,?,?);";
	final String select ="Select * from user;";
	final String delete ="DELET from user where id=?;";
	final String update ="UPDATE user SET name=?, username=?, password=? WHERE id=?;";
	
	public UserRepo() {
		connection= database.koneksi();
	}
	@Override
	public void save (User user) {
		PreparedStatement st=null;
		try {
			st = connection.prepareStatement(insert);
			st.setString(1,  user.getNama());
			st.setString(2,  user.getUsername());
			st.setString(3,  user.getPassword());
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
	public void delete(String id) {
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(delete);
			st.setString(1, id);
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
}
