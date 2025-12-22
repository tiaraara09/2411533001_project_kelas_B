package DAO;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Config.database;
import Enum.MoodType;
import model.Jurnal;
import model.Mood;
import model.MoodTracker;

public class JurnalRepo implements JurnalDao {

    private Connection conn;

    final String insert = "INSERT INTO jurnal (tanggal, judul, konten) VALUES (?, ?, ?);";
    final String update = "Update jurnal SET judul=?, konten=? Where tanggal= CURRENT_DATE;";
    final String select = "SELECT judul, konten, tanggal FROM jurnal WHERE tanggal = CURRENT_DATE;";

    
    public JurnalRepo() {
    	conn = database.koneksi();
    }

	@Override
	public void insert(Jurnal cctn) {
		PreparedStatement st =null;
		try {
			st = conn.prepareStatement(insert);
			st.setDate(1, cctn.getTanggal());
			st.setString(2, cctn.getJudul());
			st.setString(3, cctn.getKonten());
			st.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			try { st.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		}
	}

	@Override
	public boolean update(Jurnal cttn) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(update);
			st.setString(1, cttn.getJudul());
			st.setString(2, cttn.getKonten());
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
		// TODO Auto-generated method stub
		return false;
		
	}

	public List<Jurnal> show() {
	    List<Jurnal> ls = new ArrayList<>();
	    try {
	        Statement st = conn.createStatement();
	        ResultSet rs = st.executeQuery(select);
	        while(rs.next()) {
	            Jurnal cttn = new Jurnal();
	            cttn.setJudul(rs.getString("judul"));
	            java.sql.Date sqlDate = rs.getDate("tanggal");  // ambil dari DB
	            cttn.setKonten(rs.getString("konten"));
	            ls.add(cttn);
	        }
	    } catch(SQLException e) {
	        Logger.getLogger(JurnalRepo.class.getName()).log(Level.SEVERE, null, e);
	    }
	    return ls;
	}

		
	}

	

    
