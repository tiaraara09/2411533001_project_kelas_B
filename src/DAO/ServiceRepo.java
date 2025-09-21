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

import java.util.List;

import model.Service;

public class ServiceRepo implements ServiceDAO {
	
	private Connection connection;
	final String insert = "INSERT INTO service(jenis, harga, status) VALUES (?, ?, ?);";
	final String select = "SELECT * FROM service;";
	final String delete = "DELETE FROM service WHERE id=?;";
	final String update = "UPDATE service SET jenis=?, harga=?, status=? WHERE id=?;";
	
	public ServiceRepo() {
		 connection = database.koneksi();
	}

	@Override
	public void save(Service service) {
		// save
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(insert);
			st.setString(1, service.getjenis());
			st.setString(2, service.getharga());
			st.setString(3, service.getstatus());
						st.execute();
			
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
	public List<Service> show() {
		// show
		List<Service> ls=null;
		try {
			ls = new ArrayList<Service>();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(select);
			while(rs.next()) {
				Service service = new Service();
				service.setId(rs.getString("id"));
				service.setjenis(rs.getString("jenis"));
				service.setharga(rs.getString("harga"));
				service.setstatus(rs.getString("status"));
				ls.add(service);
			}
		}catch(SQLException e) {
			Logger.getLogger(ServiceDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return ls;
	}

	@Override
	public void delete(String id) {
		// delete
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(delete);
			st.setString(1, id);
			st.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void update(Service service) {
		// update
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(update);
			st.setString(1, service.getjenis());
			st.setString(2, service.getharga());
			st.setString(3, service.getstatus());
			st.setString(4, service.getId());
			st.execute();
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