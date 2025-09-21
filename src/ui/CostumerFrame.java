package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import DAO.CostumerRepo;
import model.Costumer;
import table.TableCostumer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CostumerFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtnama;
	private JTextField txtalamat;
	private JTextField txtnomor_hp;
	private JTable tableCostumer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CostumerFrame frame = new CostumerFrame();
					frame.setVisible(true);
					frame.loadTable();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	 	public void reset() {
	 		txtnama.setText("");
	 		txtalamat.setText("");
	 		txtnomor_hp.setText("");
	 	}
	 	CostumerRepo cst = new CostumerRepo();
	 	List<Costumer> ls;
	 	public String id;
	 	
	 	public void loadTable() {
	 		ls = cst.show();
	 		TableCostumer tu = new TableCostumer(ls);
	 		tableCostumer.setModel(tu);
	 		tableCostumer.getTableHeader().setVisible(true);
	 	}
	/**
	 * Create the frame.
	 */
	public CostumerFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 574, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nama");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(26, 41, 49, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblAlamat = new JLabel("Alamat");
		lblAlamat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAlamat.setBounds(26, 77, 49, 14);
		contentPane.add(lblAlamat);
		
		JLabel lblNomorHp = new JLabel("Nomor Hp");
		lblNomorHp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNomorHp.setBounds(26, 104, 82, 23);
		contentPane.add(lblNomorHp);
		
		txtnama = new JTextField();
		txtnama.setBounds(118, 38, 242, 20);
		contentPane.add(txtnama);
		txtnama.setColumns(10);
		
		txtalamat = new JTextField();
		txtalamat.setColumns(10);
		txtalamat.setBounds(118, 74, 242, 20);
		contentPane.add(txtalamat);
		
		txtnomor_hp = new JTextField();
		txtnomor_hp.setColumns(10);
		txtnomor_hp.setBounds(118, 107, 242, 20);
		contentPane.add(txtnomor_hp);
		
		JButton btnsave = new JButton("Save");
		btnsave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Costumer costumer = new Costumer();
				costumer.setNama(txtnama.getText());
				costumer.setAlamat(txtalamat.getText());
				costumer.setNomor_hp(txtnomor_hp.getText());
				cst.save(costumer);
				reset();				
			}
		});
		btnsave.setBounds(25, 156, 112, 33);
		contentPane.add(btnsave);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Costumer costumer = new Costumer();
				costumer.setNama(txtnama.getText());
				costumer.setAlamat(txtalamat.getText());
				costumer.setNomor_hp(txtnomor_hp.getText());
				costumer.setId(id);
				cst.update(costumer);
				reset();
				loadTable();
			}
		});
		btnUpdate.setBounds(161, 156, 115, 33);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id != null) {
					cst.delete(id);
					reset();
					loadTable();
				}else {
					JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan dihapus");
				}
			}
		});
		btnDelete.setBounds(296, 156, 111, 33);
		contentPane.add(btnDelete);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(426, 156, 112, 33);
		contentPane.add(btnCancel);
		
		tableCostumer = new JTable();
		tableCostumer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id = tableCostumer.getValueAt(tableCostumer.getSelectedRow(), 0).toString();
				txtnama.setText(tableCostumer.getValueAt(tableCostumer.getSelectedRow(),1).toString());
				txtalamat.setText(tableCostumer.getValueAt(tableCostumer.getSelectedRow(),2).toString());
				txtnomor_hp.setText(tableCostumer.getValueAt(tableCostumer.getSelectedRow(),3).toString());
			}
		});
		JScrollPane scrollPane = new JScrollPane(tableCostumer);
	    scrollPane.setBounds(35, 200, 481, 197);
	    contentPane.add(scrollPane);
	}
}