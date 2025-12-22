package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import Config.database;
import Enum.MoodType;
import model.HistoryModel;

public class HistoryRepo {
    private static final String SSHOW = """
        SELECT 
            h.tanggal,
            m.mood,
            j.judul AS judul_jurnal,
            j.konten AS isi_jurnal,
            t.title AS todo_title
        FROM history h
        LEFT JOIN moodtracker m ON h.mood_id = m.id
        LEFT JOIN jurnal j ON h.jurnal_id = j.id
        LEFT JOIN todolist t ON h.todolist_id = t.id
        WHERE h.tanggal = ?
    """;

    private static final String Insert = """
        INSERT INTO history (todolist_id, jurnal_id, mood_id, tanggal)
        VALUES (?, ?, ?, CURDATE())
    """;


    public HistoryModel show(LocalDate tanggal) {

        HistoryModel history = null;

        try (
            Connection conn = database.koneksi();
            PreparedStatement ps = conn.prepareStatement(SSHOW)
        ) {

            ps.setDate(1, java.sql.Date.valueOf(tanggal));
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                MoodType mood = null;
                String moodStr = rs.getString("mood");
                if (moodStr != null) {
                    mood = MoodType.valueOf(moodStr);
                }

                history = new HistoryModel(
                        rs.getDate("tanggal").toLocalDate(),
                        mood,
                        rs.getString("judul_jurnal"),
                        rs.getString("isi_jurnal"),
                        rs.getString("todo_title")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return history;
    }

   
    public void insert(int todolistId, int jurnalId, int moodId) {

        try (
            Connection conn = database.koneksi();
            PreparedStatement ps = conn.prepareStatement(Insert)
        ) {

            ps.setInt(1, todolistId);
            ps.setInt(2, jurnalId);
            ps.setInt(3, moodId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
