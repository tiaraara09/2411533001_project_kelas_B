package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;

public class Mainframe extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
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
	
	
	/**
	 * Create the frame.
	 */
	public Mainframe() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 808, 537);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnPesan = new JButton("Pesanan");
		btnPesan.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPesan.setBounds(67, 163, 136, 82);
		contentPane.add(btnPesan);
		
		JButton btnlayanan = new JButton("Layanan");
		btnlayanan.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnlayanan.setBounds(335, 163, 136, 82);
		contentPane.add(btnlayanan);
		
		JButton btnPelanggan = new JButton("Pelanggan");
		btnPelanggan.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPelanggan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPelanggan.setBounds(579, 163, 136, 82);
		contentPane.add(btnPelanggan);
		
		JButton btnPengguna = new JButton("Pengguna");
		btnPengguna.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPengguna.setBounds(67, 274, 136, 82);
		contentPane.add(btnPengguna);
		
		JButton btnLaporan = new JButton("Laporan");
		btnLaporan.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLaporan.setBounds(335, 274, 136, 82);
		contentPane.add(btnLaporan);
		
		JButton btnprofile = new JButton("Profile");
		btnprofile.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnprofile.setBounds(579, 274, 136, 82);
		contentPane.add(btnprofile);
		
		JButton btnkeluar = new JButton("Keluar");
		btnkeluar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnkeluar.setForeground(Color.DARK_GRAY);
		btnkeluar.setBounds(199, 403, 419, 36);
		contentPane.add(btnkeluar);
		
		JLabel lblNewLabel = new JLabel("Laundry Apps");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblNewLabel.setBounds(37, 28, 317, 61);
		contentPane.add(lblNewLabel);
	}
}
