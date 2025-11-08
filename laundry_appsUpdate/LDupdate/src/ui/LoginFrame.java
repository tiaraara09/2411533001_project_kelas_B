package ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.User;
import util.ValidationUtil; // import ValidationUtil

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtusername;
	private JTextField txtPassword;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 647, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Laundry Apss");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 50));
		lblNewLabel.setBounds(10, 11, 319, 50);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Males aja nyucii... biar kami cuciin");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 72, 311, 21);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Username");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(10, 117, 84, 21);
		contentPane.add(lblNewLabel_1_1);
		
		txtusername = new JTextField();
		txtusername.setBounds(10, 149, 311, 30);
		contentPane.add(txtusername);
		txtusername.setColumns(10);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Password");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1.setBounds(10, 193, 84, 21);
		contentPane.add(lblNewLabel_1_1_1);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(10, 234, 311, 30);
		contentPane.add(txtPassword);
		
		JButton btnlogin = new JButton("Login");
		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtusername.getText();
				String password = txtPassword.getText();

				// Validasi input
				if (!ValidationUtil.validateNotEmpty("Username", username)) return;
				if (!ValidationUtil.validatePassword(password)) return;

				// Jika validasi lolos, cek login
				if (User.login(username, password)) {
					new Mainframe().setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Username atau password salah!", "Login Gagal", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnlogin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnlogin.setBounds(241, 298, 179, 30);
		contentPane.add(btnlogin);
	}
}
