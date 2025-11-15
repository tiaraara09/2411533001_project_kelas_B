package ui;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import DAO.CostumerRepo;
import model.Costumer;
import model.CostumerBuilder;
import table.TableCostumer;

import java.awt.Font;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CostumerFrame extends JFrame {

    private JPanel contentPane;
    private JTextField txtnama;
    private JTextField txtalamat;
    private JTextField txtemail;
    private JTextField txtnomor_hp;
    private JTable tableCostumer;

    private CostumerRepo cst;
    private List<Costumer> ls;
    private String id;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                CostumerFrame frame = new CostumerFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        });
    }

    public CostumerFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 650, 500);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // LABEL
        JLabel lblNama = new JLabel("Nama");
        lblNama.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNama.setBounds(26, 41, 49, 14);
        contentPane.add(lblNama);

        JLabel lblAlamat = new JLabel("Alamat");
        lblAlamat.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblAlamat.setBounds(26, 77, 49, 14);
        contentPane.add(lblAlamat);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblEmail.setBounds(26, 115, 49, 14);
        contentPane.add(lblEmail);

        JLabel lblNomorHp = new JLabel("Nomor Hp");
        lblNomorHp.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNomorHp.setBounds(26, 153, 82, 23);
        contentPane.add(lblNomorHp);

        // INPUT
        txtnama = new JTextField();
        txtnama.setBounds(118, 38, 242, 20);
        contentPane.add(txtnama);

        txtalamat = new JTextField();
        txtalamat.setBounds(118, 74, 242, 20);
        contentPane.add(txtalamat);

        txtemail = new JTextField();
        txtemail.setBounds(118, 112, 242, 20);
        contentPane.add(txtemail);

        txtnomor_hp = new JTextField();
        txtnomor_hp.setBounds(118, 154, 242, 20);
        contentPane.add(txtnomor_hp);

        // BUTTONS
        JButton btnSave = new JButton("Save");
        btnSave.setBounds(25, 200, 112, 33);
        contentPane.add(btnSave);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBounds(161, 200, 115, 33);
        contentPane.add(btnUpdate);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(296, 200, 111, 33);
        contentPane.add(btnDelete);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(426, 200, 112, 33);
        contentPane.add(btnCancel);

        // TABLE
        tableCostumer = new JTable();
        tableCostumer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableCostumer.getSelectedRow();
                if (row == -1) return;

                id = tableCostumer.getValueAt(row, 0).toString();
                txtnama.setText(tableCostumer.getValueAt(row, 1).toString());
                txtalamat.setText(tableCostumer.getValueAt(row, 2).toString());
                txtemail.setText(tableCostumer.getValueAt(row, 3).toString());
                txtnomor_hp.setText(tableCostumer.getValueAt(row, 4).toString());
            }
        });

        JScrollPane scrollPane = new JScrollPane(tableCostumer);
        scrollPane.setBounds(35, 250, 560, 200);
        contentPane.add(scrollPane);

        // DAO INITIALIZE
        cst = new CostumerRepo();
        if (cst == null) {
            JOptionPane.showMessageDialog(null, "Gagal terhubung ke database!");
            return;
        }

        loadTable();

        // EVENT HANDLER
        btnSave.addActionListener(e -> saveCostumer());
        btnUpdate.addActionListener(e -> updateCostumer());
        btnDelete.addActionListener(e -> deleteCostumer());
        btnCancel.addActionListener(e -> resetForm());
    }

    private void saveCostumer() {
        if (!validateInput()) return;

        Costumer c = new CostumerBuilder()
                .setNama(txtnama.getText())
                .setAlamat(txtalamat.getText())
                .setEmail(txtemail.getText())
                .setNomorHP(txtnomor_hp.getText())
                .build();

        cst.save(c);
        JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
        resetForm();
        loadTable();
    }

    private void updateCostumer() {
        if (id == null) {
            JOptionPane.showMessageDialog(null, "Pilih data yang ingin diupdate!");
            return;
        }
        if (!validateInput()) return;

        Costumer c = new CostumerBuilder()
                .setId(id)
                .setNama(txtnama.getText())
                .setAlamat(txtalamat.getText())
                .setEmail(txtemail.getText())
                .setNomorHP(txtnomor_hp.getText())
                .build();

        cst.update(c);
        JOptionPane.showMessageDialog(null, "Data berhasil diupdate!");
        resetForm();
        loadTable();
    }

    private void deleteCostumer() {
        if (id == null) {
            JOptionPane.showMessageDialog(null, "Pilih data yang ingin dihapus!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                null,
                "Yakin ingin menghapus data?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            cst.delete(id);
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
            resetForm();
            loadTable();
        }
    }

    private boolean validateInput() {
        if (txtnama.getText().trim().isEmpty() ||
                txtalamat.getText().trim().isEmpty() ||
                txtemail.getText().trim().isEmpty() ||
                txtnomor_hp.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Semua field wajib diisi!");
            return false;
        }

        if (!txtemail.getText().contains("@")) {
            JOptionPane.showMessageDialog(null, "Email tidak valid!");
            return false;
        }

        if (!txtnomor_hp.getText().matches("[0-9]+")) {
            JOptionPane.showMessageDialog(null, "Nomor HP hanya boleh angka!");
            return false;
        }

        return true;
    }

    private void resetForm() {
        txtnama.setText("");
        txtalamat.setText("");
        txtemail.setText("");
        txtnomor_hp.setText("");
        id = null;
        tableCostumer.clearSelection();
    }

    private void loadTable() {
        ls = cst.show();
        if (ls != null) {
            TableCostumer tc = new TableCostumer(ls);
            tableCostumer.setModel(tc);
        }
    }
}
