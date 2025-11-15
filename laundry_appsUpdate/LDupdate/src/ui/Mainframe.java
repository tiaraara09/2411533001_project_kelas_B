package ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;

public class Mainframe extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Mainframe frame = new Mainframe();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Mainframe() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 808, 537);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        // ===================== BUTTON PESANAN ===========================
        JButton btnPesan = new JButton("Pesanan");
        btnPesan.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnPesan.setBounds(67, 163, 136, 82);
        btnPesan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //new PesananFrame().setVisible(true);
            }
        });
        contentPane.add(btnPesan);

        // ===================== BUTTON LAYANAN ===========================
        JButton btnlayanan = new JButton("Layanan");
        btnlayanan.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnlayanan.setBounds(335, 163, 136, 82);
        btnlayanan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ServiceFrame().setVisible(true);
            }
        });
        contentPane.add(btnlayanan);

        // ===================== BUTTON PELANGGAN ===========================
        JButton btnPelanggan = new JButton("Pelanggan");
        btnPelanggan.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnPelanggan.setBounds(579, 163, 136, 82);
        btnPelanggan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CostumerFrame().setVisible(true);
            }
        });
        contentPane.add(btnPelanggan);

        // ===================== BUTTON PENGGUNA ===========================
        JButton btnPengguna = new JButton("Pengguna");
        btnPengguna.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnPengguna.setBounds(67, 274, 136, 82);
        btnPengguna.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new UserFrame().setVisible(true);
            }
        });
        contentPane.add(btnPengguna);

        // ===================== BUTTON LAPORAN ===========================
        JButton btnLaporan = new JButton("Laporan");
        btnLaporan.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnLaporan.setBounds(335, 274, 136, 82);
        btnLaporan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //new LaporanFrame().setVisible(true);
            }
        });
        contentPane.add(btnLaporan);

        // ===================== BUTTON PROFILE ===========================
        JButton btnprofile = new JButton("Profile");
        btnprofile.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnprofile.setBounds(579, 274, 136, 82);
        btnprofile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               // new ProfileFrame().setVisible(true);
            }
        });
        contentPane.add(btnprofile);

        // ===================== BUTTON KELUAR ===========================
        JButton btnkeluar = new JButton("Keluar");
        btnkeluar.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnkeluar.setForeground(Color.DARK_GRAY);
        btnkeluar.setBounds(199, 403, 419, 36);
        btnkeluar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // menutup window
            }
        });
        contentPane.add(btnkeluar);

        JLabel lblNewLabel = new JLabel("Laundry Apps");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 50));
        lblNewLabel.setBounds(37, 28, 317, 61);
        contentPane.add(lblNewLabel);
    }
}
