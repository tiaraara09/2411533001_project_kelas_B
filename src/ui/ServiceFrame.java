package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.ServiceRepo;
import model.Service;
import table.TableService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ServiceFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtjenis;
	private JTextField txtharga;
	private JTextField txtstatus;
	private JTable tableService;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServiceFrame frame = new ServiceFrame();
					frame.setVisible(true);
					frame.loadTable();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void reset() {
		txtjenis.setText("");
		txtharga.setText("");
		txtstatus.setText("");
		
	}

	ServiceRepo srv = new ServiceRepo();
	List<Service> ls;
	public String id;
	
	public void loadTable() {
		ls = srv.show();
		TableService tu = new TableService(ls);
		tableService.setModel(tu);
		tableService.getTableHeader().setVisible(true);
	}
	/**
	 * Create the frame.
	 */
	public ServiceFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 552, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Jenis");
		lblNewLabel.setBounds(36, 62, 49, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblHarga = new JLabel("Harga");
		lblHarga.setBounds(36, 95, 49, 14);
		contentPane.add(lblHarga);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(36, 134, 49, 14);
		contentPane.add(lblStatus);
		
		txtjenis = new JTextField();
		txtjenis.setBounds(114, 59, 241, 20);
		contentPane.add(txtjenis);
		txtjenis.setColumns(10);
		
		txtharga = new JTextField();
		txtharga.setColumns(10);
		txtharga.setBounds(114, 92, 241, 20);
		contentPane.add(txtharga);
		
		txtstatus = new JTextField();
		txtstatus.setColumns(10);
		txtstatus.setBounds(114, 131, 241, 20);
		contentPane.add(txtstatus);
		
		JButton btnsave = new JButton("Save");
		btnsave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Service service = new Service();
				service.setjenis(txtjenis.getText());
				service.setharga(txtharga.getText());
				service.setstatus(txtstatus.getText());
				srv.save(service);
				reset();
			}
		});
		btnsave.setBounds(54, 183, 89, 23);
		contentPane.add(btnsave);
		
		JButton btnupdate = new JButton("Update");
		btnupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Service service = new Service();
				service.setjenis(txtjenis.getText());
				service.setharga(txtharga.getText());
				service.setstatus(txtstatus.getText());
				service.setId(id);
				srv.update(service);
				reset();
				loadTable();
			}
		});
		btnupdate.setBounds(170, 183, 89, 23);
		contentPane.add(btnupdate);
		
		JButton btndelete = new JButton("Delete");
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id != null) {
					srv.delete(id);
					reset();
					loadTable();
				}else {
					JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan dihapus");
				}
			}
		});
		btndelete.setBounds(279, 183, 89, 23);
		contentPane.add(btndelete);
		
		JButton btnclear = new JButton("Cancel");
		btnclear.setBounds(392, 183, 89, 23);
		contentPane.add(btnclear);
		
		tableService = new JTable();
		tableService.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id = tableService.getValueAt(tableService.getSelectedRow(),0).toString();
				txtjenis.setText(tableService.getValueAt(tableService.getSelectedRow(),1).toString());
				txtharga.setText(tableService.getValueAt(tableService.getSelectedRow(),2).toString());
				txtstatus.setText(tableService.getValueAt(tableService.getSelectedRow(),3).toString());
			}
		});
		tableService.setBounds(36, 217, 474, 161);
		contentPane.add(tableService);
	}
}