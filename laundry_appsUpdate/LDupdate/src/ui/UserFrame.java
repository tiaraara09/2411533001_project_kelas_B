package ui;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import DAO.UserRepo;
import model.User;
import table.TableUser;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserFrame extends JFrame {

    private JPanel contentPane;
    private JTextField txtName;
    private JTextField txtUsername;
    private JTextField txtPassword;
    private JTable tableUsers;

    UserRepo usr = new UserRepo();
    List<User> ls;
    public String id;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UserFrame frame = new UserFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /** Load tabel user */
    public void loadTable() {
        ls = usr.show();
        TableUser tu = new TableUser(ls);
        tableUsers.setModel(tu);
        tableUsers.getTableHeader().setVisible(true);
    }

    /** Reset kolom input */
    public void reset() {
        txtName.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        id = null;
    }

    /** Constructor */
    public UserFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 582, 445);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // ─────────────── INPUT FIELD ───────────────

        JLabel lblNewLabel = new JLabel("Name");
        lblNewLabel.setBounds(42, 14, 64, 34);
        contentPane.add(lblNewLabel);

        txtName = new JTextField();
        txtName.setBounds(116, 21, 383, 20);
        contentPane.add(txtName);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(42, 45, 64, 34);
        contentPane.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(116, 52, 383, 20);
        contentPane.add(txtUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(42, 76, 64, 34);
        contentPane.add(lblPassword);

        txtPassword = new JTextField();
        txtPassword.setBounds(116, 83, 383, 20);
        contentPane.add(txtPassword);

        // ─────────────── BUTTON CRUD ───────────────

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                User user = new User();
                user.setNama(txtName.getText());
                user.setUsername(txtUsername.getText());
                user.setPassword(txtPassword.getText());

                usr.save(user);

                reset();
                loadTable();
            }
        });
        btnSave.setBounds(116, 118, 89, 23);
        contentPane.add(btnSave);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (id == null) {
                    JOptionPane.showMessageDialog(null, "Pilih data terlebih dahulu!");
                    return;
                }

                User user = new User();
                user.setId(id);
                user.setNama(txtName.getText());
                user.setUsername(txtUsername.getText());
                user.setPassword(txtPassword.getText());

                usr.update(user);

                reset();
                loadTable();
            }
        });
        btnUpdate.setBounds(215, 118, 89, 23);
        contentPane.add(btnUpdate);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (id == null) {
                    JOptionPane.showMessageDialog(null, "Pilih data yang akan dihapus!");
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(null,
                        "Yakin ingin menghapus?",
                        "Konfirmasi",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    usr.delete(id);
                    reset();
                    loadTable();
                }
            }
        });

        btnDelete.setBounds(315, 118, 89, 23);
        contentPane.add(btnDelete);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(e -> reset());
        btnCancel.setBounds(419, 118, 89, 23);
        contentPane.add(btnCancel);

        // ─────────────── TABLE ───────────────

        tableUsers = new JTable();
        tableUsers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int row = tableUsers.getSelectedRow();
                if (row == -1) return;

                id = tableUsers.getValueAt(row, 0).toString();
                txtName.setText(tableUsers.getValueAt(row, 1).toString());
                txtUsername.setText(tableUsers.getValueAt(row, 2).toString());
                txtPassword.setText(tableUsers.getValueAt(row, 3).toString());
            }
        });

        JScrollPane scrollPane = new JScrollPane(tableUsers);
        scrollPane.setBounds(42, 154, 495, 243);
        contentPane.add(scrollPane);

        // ─────────────── LOAD TABEL LANGSUNG ───────────────
        loadTable();
    }
}
