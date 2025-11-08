package ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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

		JLabel lblNewLabel = new JLabel("Laundry Apps");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblNewLabel.setBounds(37, 28, 317, 61);
		contentPane.add(lblNewLabel);

		JButton btnPesan = new JButton("Pesanan");
		btnPesan.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPesan.setBounds(67, 163, 136, 82);
		btnPesan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Contoh: nanti bisa diarahkan ke frame Pesanan
				// new OrderFrame().setVisible(true);
			}
		});
		contentPane.add(btnPesan);

		JButton btnLayanan = new JButton("Layanan");
		btnLayanan.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLayanan.setBounds(335, 163, 136, 82);
		btnLayanan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ServiceFrame sf = new ServiceFrame();
				sf.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnLayanan);

		JButton btnPelanggan = new JButton("Pelanggan");
		btnPelanggan.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPelanggan.setBounds(579, 163, 136, 82);
		btnPelanggan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CostumerFrame cf = new CostumerFrame();
				cf.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnPelanggan);

		JButton btnPengguna = new JButton("Pengguna");
		btnPengguna.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPengguna.setBounds(67, 274, 136, 82);
		btnPengguna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserFrame uf = new UserFrame();
				uf.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnPengguna);

		JButton btnLaporan = new JButton("Laporan");
		btnLaporan.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLaporan.setBounds(335, 274, 136, 82);
		btnLaporan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Nanti bisa diarahkan ke LaporanFrame
				// new ReportFrame().setVisible(true);
			}
		});
		contentPane.add(btnLaporan);

		JButton btnProfile = new JButton("Profile");
		btnProfile.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnProfile.setBounds(579, 274, 136, 82);
		btnProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Bisa diarahkan ke frame profil user
			}
		});
		contentPane.add(btnProfile);

		JButton btnKeluar = new JButton("Keluar");
		btnKeluar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnKeluar.setForeground(Color.DARK_GRAY);
		btnKeluar.setBounds(199, 403, 419, 36);
		btnKeluar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame login = new LoginFrame();
				login.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnKeluar);
	}
}
