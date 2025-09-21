package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


import confg.database;

public class UserRepo implements UserDAO{
	private static final database Database = null;
	private Connection connection;
	final String insert ="INSERT INTO user (name, username, password) VALUES (?,?,?);";
	final String select ="Select * from user;";
	final String delete ="DELET from user where id=?;";
	final String update ="UPDATE user SET name=?, username=?, password=? WHERE id=?;";
	
	public UserRepo() {
		connection= Database.koneksi();
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
	public List<User> show(){
		List<User> ls=null;
		try {
			ls = new ArrayList<User>();
			Statement st = connection.createStatement();
			ResultSet rs =st.executeQuery(select);
			while(rs.next()) {
				User user = new User();
				user.setId(rs.getString("id"));
				user.setNama(rs.getString("name"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				ls.add(user);
			};
			
		}catch(SQLException e) {
			Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
			}
		return ls;
		
	}
	@Override
	public void update(User user) {
	    String updateQuery = "UPDATE users SET nama = ?, username = ?, password = ? WHERE id = ?";
	    try (PreparedStatement st = connection.prepareStatement(updateQuery)) {
	        st.setString(1, user.getNama());
	        st.setString(2, user.getUsername());
	        st.setString(3, user.getPassword());
	        st.setString(4, user.getId());
	        st.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
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