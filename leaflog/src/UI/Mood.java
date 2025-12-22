package UI;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import DAO.MoodRepo;
import Enum.MoodType;
import model.MoodTracker;

public class Mood extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JComboBox<MoodType> modbox;
    private JComboBox<Integer> haribox;
    private JComboBox<String> blnbox;
    private JComboBox<String> thnbox;
    private JLabel lblMood;
    private JLabel lblTanggal;
    private JLabel lblMoodHariIni_1;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Mood frame = new Mood();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Mood() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 520, 430);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        contentPane.setBackground(Theme.BG_CALENDAR);
        setContentPane(contentPane);

        // ===== Mood ComboBox =====
        modbox = new JComboBox<>(MoodType.values());
        modbox.setBounds(107, 64, 120, 25);
        contentPane.add(modbox);

        // ===== Hari, Bulan, Tahun ComboBox =====
        haribox = new JComboBox<>();
        haribox.addItem(0);
        for (int i = 1; i <= 31; i++) haribox.addItem(i);
        haribox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                                                          boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value != null && (Integer) value == 0) setText("Tanggal");
                return this;
            }
        });
        haribox.setBounds(107, 128, 80, 25);
        contentPane.add(haribox);

        blnbox = new JComboBox<>(new String[]{"Bulan","Jan","Feb","Mar","Apr","Mei","Juni","Juli","Agus","Sep","Okt","Nov","Des"});
        blnbox.setBounds(197, 128, 80, 25);
        contentPane.add(blnbox);

        thnbox = new JComboBox<>(new String[]{"Tahun","2024","2025","2026"});
        thnbox.setBounds(287, 128, 80, 25);
        contentPane.add(thnbox);

        // ===== Set tanggal default setelah semua ComboBox dibuat =====
        LocalDate today = LocalDate.now();
        haribox.setSelectedItem(today.getDayOfMonth());
        blnbox.setSelectedIndex(today.getMonthValue());
        thnbox.setSelectedItem(String.valueOf(today.getYear()));

        // ===== Tombol Save =====
        JButton savebtn = new JButton("Save");
        savebtn.setBounds(107, 200, 90, 30);
        savebtn.setBackground(Theme.BTN_DATE);
        savebtn.setForeground(Color.BLACK);
        savebtn.addActionListener(e -> saveMood());
        contentPane.add(savebtn);

        // ===== Tombol Update =====
        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBounds(207, 200, 90, 30);
        btnUpdate.setBackground(Theme.BTN_DATE);
        btnUpdate.setForeground(Color.BLACK);
        btnUpdate.addActionListener(e -> updateMood());
        contentPane.add(btnUpdate);

        // ===== Tombol Cancel =====
        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(395, 350, 90, 30);
        btnCancel.setBackground(Theme.BTN_DATE);
        btnCancel.setForeground(Color.BLACK);
        btnCancel.addActionListener(e -> {
            Menu menu = new Menu();
            menu.setVisible(true);
            dispose();
        });
        contentPane.add(btnCancel);

        lblMood = new JLabel("Mood");
        lblMood.setBounds(10, 64, 90, 25);
        lblMood.setForeground(Color.WHITE);
        contentPane.add(lblMood);

        lblTanggal = new JLabel("Tanggal");
        lblTanggal.setBounds(10, 128, 90, 25);
        lblTanggal.setForeground(Color.WHITE);
        contentPane.add(lblTanggal);

        lblMoodHariIni_1 = new JLabel("Mood Hari Ini");
        lblMoodHariIni_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblMoodHariIni_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblMoodHariIni_1.setForeground(Color.WHITE);
        lblMoodHariIni_1.setBounds(211, 11, 120, 25);
        contentPane.add(lblMoodHariIni_1);

        // ===== Label Mood Hari Ini =====
        JLabel lblMoodHariIni = new JLabel("Mood Hari Ini");
        lblMoodHariIni.setBounds(10, 280, 100, 25);
        lblMoodHariIni.setForeground(Color.WHITE);
        contentPane.add(lblMoodHariIni);

        // ===== Tabel =====
        table = new JTable();
        table.setBounds(96, 259, 380, 80);
        contentPane.add(table);

        // Load mood saat frame dibuka
        loadMoodTable();
    }

    private void saveMood() {
        int day = (int) haribox.getSelectedItem();
        int month = blnbox.getSelectedIndex();
        int year = thnbox.getSelectedIndex() > 0 ? Integer.parseInt((String) thnbox.getSelectedItem()) : 0;

        if (day == 0 || month == 0 || year == 0) {
            JOptionPane.showMessageDialog(this,"Tanggal belum lengkap!","Peringatan",JOptionPane.WARNING_MESSAGE);
            return;
        }

        LocalDate date = LocalDate.of(year, month, day);
        MoodType selectedMood = (MoodType) modbox.getSelectedItem();

        MoodTracker mood = new MoodTracker() {{
            setMood(selectedMood);
            setTnggl(date);
        }};

        try {
            MoodRepo repo = new MoodRepo();
            repo.save(mood);
            JOptionPane.showMessageDialog(this,"Mood berhasil disimpan!");
            loadMoodTable();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,"Gagal menyimpan mood: "+ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateMood() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,"Pilih mood yang ingin diupdate!","Peringatan",JOptionPane.WARNING_MESSAGE);
            return;
        }

        MoodType selectedMood = (MoodType) modbox.getSelectedItem();

        try {
            MoodRepo repo = new MoodRepo();
            List<MoodTracker> moods = repo.show();
            if (moods == null) moods = new ArrayList<>();
            MoodTracker mood = moods.get(0); // langsung mood hari ini

            int day = (int) haribox.getSelectedItem();
            int month = blnbox.getSelectedIndex();
            int year = thnbox.getSelectedIndex() > 0 ? Integer.parseInt((String) thnbox.getSelectedItem()) : 0;
            if(day ==0 || month==0 || year==0){
                JOptionPane.showMessageDialog(this,"Tanggal belum lengkap!","Peringatan",JOptionPane.WARNING_MESSAGE);
                return;
            }
            LocalDate date = LocalDate.of(year, month, day);
            mood.setMood(selectedMood);
            mood.setTnggl(date);

            repo.update(mood);
            JOptionPane.showMessageDialog(this,"Mood berhasil diupdate!");
            loadMoodTable();

        } catch(Exception ex){
            JOptionPane.showMessageDialog(this,"Gagal update mood: "+ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadMoodTable() {
        try {
            MoodRepo repo = new MoodRepo();
            List<MoodTracker> moods = repo.show();
            if(moods == null) moods = new ArrayList<>();

            DefaultTableModel model = new DefaultTableModel(new Object[]{"ID","Mood","Tanggal"},0);
            for(MoodTracker m : moods){
                model.addRow(new Object[]{m.getId(), m.getMood(), m.getTnggl()});
            }
            table.setModel(model);

            table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                               boolean hasFocus, int row, int column){
                    Component c = super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);
                    if(!isSelected){
                        Object moodObj = table.getValueAt(row,1);
                        if(moodObj instanceof MoodType){
                            MoodType mood = (MoodType) moodObj;
                            c.setBackground(Theme.moodColor(mood));
                        } else {
                            c.setBackground(Theme.BG_CALENDAR);
                        }
                    } else {
                        c.setBackground(Theme.BTN_DATE);
                    }
                    return c;
                }
            });

        } catch(Exception ex){
            JOptionPane.showMessageDialog(this,"Gagal load mood: "+ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}
