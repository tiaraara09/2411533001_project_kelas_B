package UI;

import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import DAO.HistoryRepo;
import model.HistoryModel;

public class History extends JFrame {

    public History(LocalDate tanggal) {

        setTitle("History " + tanggal);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(null);

        HistoryRepo repo = new HistoryRepo();
        HistoryModel history = repo.show(tanggal);

        if (history == null) {
            JLabel lbl = new JLabel("Tidak ada data pada tanggal ini");
            lbl.setBounds(20, 20, 300, 20);
            add(lbl);
            return;
        }

        JLabel lblMood = new JLabel("Mood: " + history.getMood());
        lblMood.setBounds(20, 20, 300, 20);
        add(lblMood);

        JLabel lblJudul = new JLabel("Judul: " + history.getJudulJurnal());
        lblJudul.setBounds(20, 50, 300, 20);
        add(lblJudul);

        JTextArea txtJurnal = new JTextArea(history.getIsiJurnal());
        txtJurnal.setBounds(20, 80, 340, 100);
        txtJurnal.setEditable(false);
        add(txtJurnal);

        JLabel lblTodo = new JLabel("Todo: " + history.getTodo());
        lblTodo.setBounds(20, 190, 300, 20);
        add(lblTodo);
    }
}
