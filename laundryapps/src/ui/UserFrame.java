package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Font;

public class UserFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtnama;
	private JTextField txtusername;
	private JTextField txtpassword;
	private JTable tableUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserFrame frame = new UserFrame();
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
	
	
	public UserFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 625, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nama");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(50, 71, 49, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(50, 106, 83, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(50, 140, 69, 14);
		contentPane.add(lblNewLabel_1_1);
		
		txtnama = new JTextField();
		txtnama.setBounds(143, 68, 295, 20);
		contentPane.add(txtnama);
		txtnama.setColumns(10);
		
		txtusername = new JTextField();
		txtusername.setColumns(10);
		txtusername.setBounds(143, 106, 295, 20);
		contentPane.add(txtusername);
		
		txtpassword = new JTextField();
		txtpassword.setColumns(10);
		txtpassword.setBounds(143, 140, 295, 20);
		contentPane.add(txtpassword);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setBounds(69, 209, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(189, 209, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(313, 209, 89, 23);
		contentPane.add(btnDelete);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(439, 209, 89, 23);
		contentPane.add(btnCancel);
		
		tableUser = new JTable();
		tableUser.setBounds(44, 256, 521, 133);
		contentPane.add(tableUser);
	}

}
