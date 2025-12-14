package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Mood extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mood frame = new Mood();
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
	public Mood() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(95, 81, 277, 22);
		contentPane.add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(95, 137, 277, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton savebtn = new JButton("Save");
		savebtn.setBounds(58, 209, 89, 23);
		contentPane.add(savebtn);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(283, 209, 89, 23);
		contentPane.add(btnCancel);
	}

}
