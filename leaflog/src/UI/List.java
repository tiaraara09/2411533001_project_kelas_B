package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JTable;

public class List extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					List frame = new List();
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
	public List() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 490, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel judullbl = new JLabel("To-Do List");
		judullbl.setHorizontalAlignment(SwingConstants.LEFT);
		judullbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		judullbl.setBounds(10, 11, 122, 34);
		contentPane.add(judullbl);
		
		JButton bckbtn = new JButton("Back Menu");
		bckbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		bckbtn.setBounds(349, 260, 117, 23);
		contentPane.add(bckbtn);
		
		JButton addbtn = new JButton("+");
		addbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		addbtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		addbtn.setBounds(10, 56, 49, 39);
		contentPane.add(addbtn);
		
		table = new JTable();
		table.setBounds(10, 99, 456, 147);
		contentPane.add(table);
		
		JButton dltbtn = new JButton("-");
		dltbtn.setFont(new Font("Tahoma", Font.BOLD, 15));
		dltbtn.setBounds(69, 56, 49, 39);
		contentPane.add(dltbtn);
	}
}
