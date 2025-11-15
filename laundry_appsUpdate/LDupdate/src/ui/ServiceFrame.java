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
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ServiceFrame extends JFrame {

    private JPanel contentPane;
    private JTextField txtjenis;
    private JTextField txtharga;
    private JTextField txtstatus;
    private JTable tableService;

    ServiceRepo srv = new ServiceRepo();
    List<Service> ls;
    public String id;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ServiceFrame frame = new ServiceFrame();
                frame.setVisible(true);
                frame.loadTable();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ServiceFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 450);
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

        txtharga = new JTextField();
        txtharga.setBounds(114, 92, 241, 20);
        contentPane.add(txtharga);

        txtstatus = new JTextField();
        txtstatus.setBounds(114, 131, 241, 20);
        contentPane.add(txtstatus);

        JButton btnsave = new JButton("Save");
        btnsave.addActionListener(e -> {
            Service service = new Service();
            service.setjenis(txtjenis.getText());
            service.setharga(txtharga.getText());
            service.setstatus(txtstatus.getText());
            srv.save(service);
            reset();
            loadTable(); // <-- WAJIB
        });
        btnsave.setBounds(54, 183, 89, 23);
        contentPane.add(btnsave);

        JButton btnupdate = new JButton("Update");
        btnupdate.addActionListener(e -> {
            Service service = new Service();
            service.setjenis(txtjenis.getText());
            service.setharga(txtharga.getText());
            service.setstatus(txtstatus.getText());
            service.setId(id);
            srv.update(service);
            reset();
            loadTable();
        });
        btnupdate.setBounds(170, 183, 89, 23);
        contentPane.add(btnupdate);

        JButton btndelete = new JButton("Delete");
        btndelete.addActionListener(e -> {
            if (id != null) {
                srv.delete(id);
                reset();
                loadTable();
            } else {
                JOptionPane.showMessageDialog(null, "Silahkan pilih data terlebih dahulu.");
            }
        });
        btndelete.setBounds(279, 183, 89, 23);
        contentPane.add(btndelete);

        JButton btnclear = new JButton("Cancel");
        btnclear.addActionListener(e -> reset());
        btnclear.setBounds(392, 183, 89, 23);
        contentPane.add(btnclear);

        // SCROLLPANE WAJIB
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(36, 217, 520, 170);
        contentPane.add(scrollPane);

        tableService = new JTable();
        tableService.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tableService.getSelectedRow();
                id = tableService.getValueAt(row, 0).toString();
                txtjenis.setText(tableService.getValueAt(row, 1).toString());
                txtharga.setText(tableService.getValueAt(row, 2).toString());
                txtstatus.setText(tableService.getValueAt(row, 3).toString());
            }
        });
        scrollPane.setViewportView(tableService);
    }

    public void loadTable() {
        ls = srv.show();
        TableService tb = new TableService(ls);
        tableService.setModel(tb);
        tableService.getTableHeader().setVisible(true);
    }

    public void reset() {
        txtjenis.setText("");
        txtharga.setText("");
        txtstatus.setText("");
        id = null;
    }
}
