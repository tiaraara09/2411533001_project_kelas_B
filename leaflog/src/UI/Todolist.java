package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Todolist extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField lsttxt;
	private JTextField tnggltxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Todolist frame = new Todolist();
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
	public Todolist() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 469, 326);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lsttxt = new JTextField();
		lsttxt.setBounds(78, 44, 331, 43);
		contentPane.add(lsttxt);
		lsttxt.setColumns(10);
		
		tnggltxt = new JTextField();
		tnggltxt.setText("dd/mm/yyyy");
		tnggltxt.setColumns(10);
		tnggltxt.setBounds(78, 109, 331, 20);
		contentPane.add(tnggltxt);
		
		JComboBox statusbox = new JComboBox();
		statusbox.setBounds(78, 149, 177, 22);
		contentPane.add(statusbox);
		
		JButton savebtn = new JButton("Save");
		savebtn.setBounds(78, 230, 89, 23);
		contentPane.add(savebtn);
		
		JButton cancelbtn = new JButton("Cancel");
		cancelbtn.setBounds(304, 230, 89, 23);
		contentPane.add(cancelbtn);
		
		JLabel lblNewLabel = new JLabel("List");
		lblNewLabel.setBounds(10, 58, 49, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblDueDate = new JLabel("Due Date");
		lblDueDate.setBounds(10, 112, 64, 14);
		contentPane.add(lblDueDate);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(10, 153, 49, 14);
		contentPane.add(lblStatus);
	}
}
