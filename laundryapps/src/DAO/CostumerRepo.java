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
import model.Costumer;
import model.User;

public class CostumerRepo implements CostumerDAO{
	private Connection connection;
	final String insert ="INSERT INTO costumer (nama, alamat, nomor_hp) VALUES (?,?,?);";
	final String select ="Select * from costumer;";
	final String delete ="DELETE from costumer where id=?;";
	final String update ="UPDATE costumer SET nama=?, alamat=?, nomor_hp=? WHERE id=?;";

	public CostumerRepo() {
		connection= database.koneksi();
	}

	@Override
	public void save(Costumer costumer) {
		// save
		PreparedStatement st=null;
		try {
			st = connection.prepareStatement(insert);
			st.setString(1,  costumer.getNama());
			st.setString(2,  costumer.getAlamat());
			st.setString(3,  costumer.getNomor_hp());
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
		//select
		public List<Costumer> show(){
			List<Costumer> ls=null;
			try {
				ls = new ArrayList<>();
				Statement st = connection.createStatement();
				ResultSet rs =st.executeQuery(select);
				while(rs.next()) {
					Costumer costumer = new Costumer();
					costumer.setId(rs.getString("id"));
					costumer.setNama(rs.getString("nama"));
					costumer.setAlamat(rs.getString("alamat"));
					costumer.setNomor_hp(rs.getString("nomor_hp"));
					ls.add(costumer);
				}

			}catch(SQLException e) {
				Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
				}
			return ls;
		}
		
	@Override
	public void update(Costumer costumer) {
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(update);
			st.setString(1, costumer.getNama());
			st.setString(2, costumer.getAlamat());
			st.setString(3, costumer.getNomor_hp());
			st.setString(4, costumer.getId());
			st.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			}finally {
				try{
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
