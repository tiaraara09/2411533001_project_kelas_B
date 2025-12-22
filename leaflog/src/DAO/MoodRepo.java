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
import model.Mood;
import model.MoodTracker;

public class MoodRepo implements MoodDao {
    private Connection conn;
    final String insert ="INSERT INTO moodtracker (tanggal, mood) VALUES (?, ?);";
    final String select ="SELECT id, tanggal, mood FROM moodtracker WHERE tanggal=CURRENT_DATE;";
    final String update ="UPDATE moodtracker SET mood=? WHERE tanggal=CURRENT_DATE;";

    public MoodRepo() {
        conn = database.koneksi();
    }

    @Override
    public void save(MoodTracker mt) {
        try (PreparedStatement st = conn.prepareStatement(insert)) {
            st.setDate(1, java.sql.Date.valueOf(mt.getTnggl()));
            st.setString(2, mt.getMood().name());
            st.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<MoodTracker> show() {
        List<MoodTracker> ls = new ArrayList<>();
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(select)) {

            while(rs.next()) {
                MoodTracker mt = new MoodTracker() {};
                mt.setId(rs.getInt("id"));
                mt.setTnggl(rs.getDate("tanggal").toLocalDate());
                mt.setMood(MoodType.valueOf(rs.getString("mood")));
                ls.add(mt);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return ls;
    }

    @Override
    public void update(MoodTracker mt) {
        try (PreparedStatement st = conn.prepareStatement(update)) {
            st.setString(1, mt.getMood().name()); // parameter index 1
            st.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
