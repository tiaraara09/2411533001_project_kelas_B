package UI;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import DAO.JurnalRepo;

public class Jurnal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField judulField;         // Input judul
    private JTextArea jurnaltxtArea;       // Input isi jurnal
    private JTable jurnaltable;
    private JComboBox<Integer> haribox;
    private JComboBox<String> blnbox;
    private JComboBox<String> thnbox;
    private List<JurnalItem> jurnalMemory = new ArrayList<>();

    // Warna tema
    public static final Color BG_CALENDAR = new Color(80, 115, 53);   // #507335
    public static final Color BTN_DATE = new Color(232, 220, 193);    // #e8dcc1

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Jurnal frame = new Jurnal();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Jurnal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 778, 569);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        contentPane.setBackground(BG_CALENDAR);
        setContentPane(contentPane);

        // Judul Frame
        JLabel judul = new JLabel("Jurnal");
        judul.setFont(new Font("Tahoma", Font.BOLD, 18));
        judul.setForeground(Color.WHITE);
        judul.setBounds(334, 11, 120, 25);
        contentPane.add(judul);

        // Input Judul 
        JLabel lblJudul = new JLabel("Judul");
        lblJudul.setForeground(Color.WHITE);
        lblJudul.setBounds(10, 52, 49, 14);
        contentPane.add(lblJudul);

        judulField = new JTextField();
        judulField.setBounds(93, 49, 630, 22);
        contentPane.add(judulField);
        judulField.setColumns(10);

        //Input Isi Jurnal dengan JScrollPane 
        JLabel jurnallbl = new JLabel("Isi Jurnal");
        jurnallbl.setForeground(Color.WHITE);
        jurnallbl.setBounds(10, 86, 70, 14);
        contentPane.add(jurnallbl);

        jurnaltxtArea = new JTextArea();
        jurnaltxtArea.setLineWrap(true);
        jurnaltxtArea.setWrapStyleWord(true);
        jurnaltxtArea.setFont(new Font("Tahoma", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(jurnaltxtArea);
        scrollPane.setBounds(93, 83, 630, 130);
        contentPane.add(scrollPane);

        //Hari, Bulan, Tahun 
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
        haribox.setBounds(93, 224, 102, 22);
        contentPane.add(haribox);

        blnbox = new JComboBox<>(new String[]{"Bulan","Jan","Feb","Mar","Apr","Mei","Juni","Juli","Agus","Sep","Okt","Nov","Des"});
        blnbox.setBounds(238, 224, 102, 22);
        contentPane.add(blnbox);

        thnbox = new JComboBox<>();
        thnbox.addItem("Tahun");
        thnbox.addItem("2024");
        thnbox.addItem("2025");
        thnbox.addItem("2026");
        thnbox.setBounds(383, 224, 89, 22);
        contentPane.add(thnbox);

        JLabel tggllbl = new JLabel("Tanggal");
        tggllbl.setForeground(Color.WHITE);
        tggllbl.setBounds(17, 228, 49, 14);
        contentPane.add(tggllbl);

        //Tombol Save 
        JButton savebtn = new JButton("Save");
        savebtn.setBounds(88, 266, 89, 30);
        savebtn.setBackground(BTN_DATE);
        savebtn.setForeground(Color.BLACK);
        savebtn.addActionListener(e -> saveJurnal());
        contentPane.add(savebtn);

        //Tombol Update 
        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBounds(197, 266, 89, 30);
        btnUpdate.setBackground(BTN_DATE);
        btnUpdate.setForeground(Color.BLACK);
        btnUpdate.addActionListener(e -> updateJurnal());
        contentPane.add(btnUpdate);

        //Tombol Cancel
        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(641, 491, 89, 30);
        btnCancel.setBackground(BTN_DATE);
        btnCancel.setForeground(Color.BLACK);
        btnCancel.addActionListener(e -> {
            Menu menu = new Menu();
            menu.setVisible(true);
            dispose();
        });
        contentPane.add(btnCancel);

        // ===== Tabel =====
        jurnaltable = new JTable();
        jurnaltable.setBounds(93, 350, 630, 123);
        contentPane.add(jurnaltable);

        JLabel lblJurnalHariIni = new JLabel("Jurnal Hari Ini");
        lblJurnalHariIni.setForeground(Color.WHITE);
        lblJurnalHariIni.setBounds(10, 325, 120, 14);
        contentPane.add(lblJurnalHariIni);

        // Tanggal Default
        LocalDate today = LocalDate.now();
        haribox.setSelectedItem(today.getDayOfMonth());
        blnbox.setSelectedIndex(today.getMonthValue());
        thnbox.setSelectedItem(String.valueOf(today.getYear()));
        
        //Button Show
        JButton btnShow = new JButton("Show");
        btnShow.setBounds(306, 266, 89, 30);
        btnShow.setBackground(BTN_DATE);
        btnShow.setForeground(Color.BLACK);
        btnShow.addActionListener(e -> showJurnal());
        contentPane.add(btnShow);


        // Load jurnal 
        loadJurnalTable();
    }

    // Simulasi item jurnal 
    private static class JurnalItem {
        LocalDate tanggal;
        String judul;
        String isi;

        JurnalItem(LocalDate tanggal, String judul, String isi) {
            this.tanggal = tanggal;
            this.judul = judul;
            this.isi = isi;
        }
    }

    private void saveJurnal() {
        int day = (int) haribox.getSelectedItem();
        int month = blnbox.getSelectedIndex();
        int year = thnbox.getSelectedIndex() > 0 ? Integer.parseInt((String) thnbox.getSelectedItem()) : 0;

        if(day==0 || month==0 || year==0){
            JOptionPane.showMessageDialog(this,"Tanggal belum lengkap!","Peringatan",JOptionPane.WARNING_MESSAGE);
            return;
        }

        String judul = judulField.getText().trim();
        String isi = jurnaltxtArea.getText().trim();

        if(judul.isEmpty()){
            JOptionPane.showMessageDialog(this,"Judul jurnal kosong!","Peringatan",JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(isi.isEmpty()){
            JOptionPane.showMessageDialog(this,"Isi jurnal kosong!","Peringatan",JOptionPane.WARNING_MESSAGE);
            return;
        }

        java.sql.Date sqlDate = java.sql.Date.valueOf(LocalDate.of(year, month, day));

        model.Jurnal j = new model.Jurnal();
        j.setTanggal(sqlDate);
        j.setJudul(judul);
        j.setKonten(isi);

        JurnalRepo repo = new JurnalRepo();
        repo.insert(j);  

        jurnalMemory.removeIf(x -> x.tanggal.equals(LocalDate.of(year, month, day)));
        jurnalMemory.add(new JurnalItem(LocalDate.of(year, month, day), judul, isi));

        JOptionPane.showMessageDialog(this,"Jurnal berhasil disimpan!");
        loadJurnalTable();
    }


    private void updateJurnal() {
        int day = (int) haribox.getSelectedItem();
        int month = blnbox.getSelectedIndex();
        int year = thnbox.getSelectedIndex() > 0 ? Integer.parseInt((String) thnbox.getSelectedItem()) : 0;

        if(day==0 || month==0 || year==0){
            JOptionPane.showMessageDialog(this,"Tanggal belum lengkap!","Peringatan",JOptionPane.WARNING_MESSAGE);
            return;
        }

        LocalDate date = LocalDate.of(year, month, day);
        String judul = judulField.getText().trim();
        String isi = jurnaltxtArea.getText().trim();
        boolean found = false;
        for(JurnalItem j : jurnalMemory){
            if(j.tanggal.equals(date)){
                j.judul = judul;
                j.isi = isi;
                found = true;
                break;
            }
        }
        if(!found){
            JOptionPane.showMessageDialog(this,"Tidak ada jurnal hari ini, gunakan Save untuk menambah!","Peringatan",JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,"Jurnal berhasil diupdate!");
        }
        loadJurnalTable();
    }

    private void loadJurnalTable(){
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Tanggal","Judul","Isi Jurnal"},0);
        LocalDate today = LocalDate.now();
        for(JurnalItem j : jurnalMemory){
            if(j.tanggal.equals(today)){
                model.addRow(new Object[]{j.tanggal, j.judul, j.isi});
            }
        }
        jurnaltable.setModel(model);
    }
    
 // ===== Tombol Show =====
    private void showJurnal() {
        int day = (int) haribox.getSelectedItem();
        int month = blnbox.getSelectedIndex();
        int year = thnbox.getSelectedIndex() > 0 ? Integer.parseInt((String) thnbox.getSelectedItem()) : 0;

        if(day==0 || month==0 || year==0){
            JOptionPane.showMessageDialog(this,"Tanggal belum lengkap!","Peringatan",JOptionPane.WARNING_MESSAGE);
            return;
        }

        LocalDate date = LocalDate.of(year, month, day);
        JurnalRepo repo = new JurnalRepo();
        List<model.Jurnal> list = repo.show();
        boolean found = false;

        for(model.Jurnal j : list){
            if(j.getTanggal().toLocalDate().equals(date)){
                jurnaltxtArea.setText(j.getKonten());
                judulField.setText(j.getJudul());  
                found = true;
                break;
            }
        }

        if(!found){
            JOptionPane.showMessageDialog(this,"Tidak ada jurnal pada tanggal ini!","Info",JOptionPane.INFORMATION_MESSAGE);
            jurnaltxtArea.setText("");
            judulField.setText("");
        }
    }

}
