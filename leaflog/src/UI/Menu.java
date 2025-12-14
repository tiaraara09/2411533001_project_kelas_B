package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 473, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton todolistbtn = new JButton("To-Do List");
		todolistbtn.setBounds(165, 117, 113, 23);
		contentPane.add(todolistbtn);
		
		JButton Moodbtn = new JButton("Mood Tracker");
		Moodbtn.setBounds(165, 166, 113, 23);
		contentPane.add(Moodbtn);
		
		JButton jurnalbtn = new JButton("Jurnal");
		jurnalbtn.setBounds(165, 217, 113, 23);
		contentPane.add(jurnalbtn);
		
		JButton Historybtn = new JButton("History");
		Historybtn.setBounds(165, 271, 113, 23);
		contentPane.add(Historybtn);
		
		JButton backbtn = new JButton("Back");
		backbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		backbtn.setBounds(360, 338, 89, 23);
		contentPane.add(backbtn);
		
		JLabel judullbl = new JLabel("MENU");
		judullbl.setFont(new Font("Tahoma", Font.BOLD, 25));
		judullbl.setHorizontalAlignment(SwingConstants.CENTER);
		judullbl.setBounds(165, 28, 124, 58);
		contentPane.add(judullbl);
	}
}
