package ui;

import java.awt.EventQueue;
import model.User;
import table.TableUser;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.UserRepo;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Font;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import DAO.UserDAO;
import DAO.UserRepo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import table.TableUser;

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
	
	//user repo
	UserRepo usr= new UserRepo();
	List<User> ls;
	public String id;
	
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
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user = new User();
				user.setNama(txtnama.getText());
				user.setUsername(txtusername.getText());
				user.setPassword(txtpassword.getText());
				usr.save(user);
				reset();
				loadTable();
			}

			public void loadTable() {
				ls = usr.show();
				TableUser tu = new TableUser(ls);
				tableUser.setModel(tu);
				tableUser.getTableHeader().setVisible(true);
			}
		});
		btnSave.setBounds(69, 209, 89, 23);
		contentPane.add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        User user = new User();
		        user.setNama(txtnama.getText());
		        user.setUsername(txtusername.getText());
		        user.setPassword(txtpassword.getText());
		        user.setId(id);
		        user.update(user);
		        reset();
		        loadTable();
		    }
		});
		btnUpdate.setBounds(189, 209, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(313, 209, 89, 23);
		contentPane.add(btnDelete);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(439, 209, 89, 23);
		contentPane.add(btnCancel);
		
		tableUser = new JTable();
		tableUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id = tableUser.getValueAt(tableUser.getSelectedRow(), 0).toString();
				txtnama.setText(tableUser.getValueAt(tableUser.getSelectedRow(), 1).toString());
				txtusername.setText(tableUser.getValueAt(tableUser.getSelectedRow(), 2).toString());
				txtpassword.setText(tableUser.getValueAt(tableUser.getSelectedRow(), 3).toString());
			}
		});
		tableUser.setBounds(44, 256, 521, 133);
		contentPane.add(tableUser);
	}

	protected void loadTable() {
		// TODO Auto-generated method stub
		
	}

	protected void reset() {
		// TODO Auto-generated method stub
		
	}

}
