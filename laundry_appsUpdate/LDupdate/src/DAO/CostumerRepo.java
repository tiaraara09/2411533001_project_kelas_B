package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import confg.database;
import model.Costumer;

public class CostumerRepo implements CostumerDAO {

    private Connection connection;

    private static final String INSERT =
            "INSERT INTO costumer (nama, alamat, email, nomor_hp) VALUES (?,?,?,?);";

    private static final String SELECT =
            "SELECT * FROM costumer;";

    private static final String DELETE =
            "DELETE FROM costumer WHERE id=?;";

    private static final String UPDATE =
            "UPDATE costumer SET nama=?, alamat=?, email=?, nomor_hp=? WHERE id=?;";

    public CostumerRepo() {
        connection = database.koneksi();
    }

    @Override
    public void save(Costumer costumer) {
        PreparedStatement st = null;

        try {
            st = connection.prepareStatement(INSERT);
            st.setString(1, costumer.getNama());
            st.setString(2, costumer.getAlamat());
            st.setString(3, costumer.getEmail());
            st.setString(4, costumer.getNomor_hp());
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            closeStatement(st);
        }
    }

    @Override
    public List<Costumer> show() {

        List<Costumer> ls = new ArrayList<>();
        Statement st = null;
        ResultSet rs = null;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(SELECT);

            while (rs.next()) {
                String id = rs.getString("id");
                String nama = rs.getString("nama");
                String email = rs.getString("alamat");
                String alamat = rs.getString("email");
                String nomorHp = rs.getString("nomor_hp");

                Costumer costumer = new Costumer(id, nama, email, alamat, nomorHp);
                ls.add(costumer);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            closeResultSet(rs);
            closeStatement(st);
        }

        return ls;
    }

    @Override
    public void update(Costumer costumer) {
        PreparedStatement st = null;

        try {
            st = connection.prepareStatement(UPDATE);
            st.setString(1, costumer.getNama());
            st.setString(2, costumer.getAlamat());
            st.setString(3, costumer.getEmail());
            st.setString(4, costumer.getNomor_hp());
            st.setString(5, costumer.getId());
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            closeStatement(st);
        }
    }

    @Override
    public void delete(String id) {
        PreparedStatement st = null;

        try {
            st = connection.prepareStatement(DELETE);
            st.setString(1, id);
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            closeStatement(st);
        }
    }

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
