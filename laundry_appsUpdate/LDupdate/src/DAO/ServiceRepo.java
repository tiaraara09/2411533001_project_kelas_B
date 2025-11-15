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
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(insert);
            st.setString(1, service.getjenis());
            st.setString(2, service.getharga());
            st.setString(3, service.getstatus());
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(st);
        }
    }

    @Override
    public List<Service> show() {

        List<Service> ls = new ArrayList<>();
        Statement st = null;
        ResultSet rs = null;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(select);

            while (rs.next()) {
                Service service = new Service();
                service.setId(rs.getString("id"));
                service.setjenis(rs.getString("jenis"));
                service.setharga(rs.getString("harga"));
                service.setstatus(rs.getString("status"));
                ls.add(service);
            }

        } catch (SQLException e) {
            Logger.getLogger(ServiceDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeResultSet(rs);
            closeStatement(st);
        }

        return ls;
    }

    @Override
    public void delete(String id) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(delete);
            st.setString(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(st);
        }
    }

    @Override
    public void update(Service service) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(update);
            st.setString(1, service.getjenis());
            st.setString(2, service.getharga());
            st.setString(3, service.getstatus());
            st.setString(4, service.getId());
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(st);
        }
    }

    // Utility methods
    private void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
