package UI;

import java.awt.*;
import javax.swing.*;
import DAO.UserRepo;
import model.User;

public class Daftar extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField namatxt;
    private JTextField usernametxt;
    private JPasswordField pwssdtxt;
    private JPasswordField pwssdtxt2;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Daftar frame = new Daftar();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Daftar() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 350);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBackground(Theme.BG_CALENDAR);
        setContentPane(contentPane);

        JLabel lblTitle = new JLabel("DAFTAR AKUN BARU");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTitle.setBounds(80, 20, 280, 25);
        contentPane.add(lblTitle);

        JLabel lblNama = new JLabel("Nama");
        lblNama.setForeground(Color.WHITE);
        lblNama.setBounds(30, 70, 80, 25);
        contentPane.add(lblNama);

        namatxt = new JTextField();
        namatxt.setBounds(120, 70, 250, 25);
        contentPane.add(namatxt);

        JLabel lblUser = new JLabel("Username");
        lblUser.setForeground(Color.WHITE);
        lblUser.setBounds(30, 110, 80, 25);
        contentPane.add(lblUser);

        usernametxt = new JTextField();
        usernametxt.setBounds(120, 110, 250, 25);
        contentPane.add(usernametxt);

        JLabel lblPass = new JLabel("Password");
        lblPass.setForeground(Color.WHITE);
        lblPass.setBounds(30, 150, 80, 25);
        contentPane.add(lblPass);

        pwssdtxt = new JPasswordField();
        pwssdtxt.setBounds(120, 150, 250, 25);
        contentPane.add(pwssdtxt);

        JLabel lblKonf = new JLabel("Konfirmasi");
        lblKonf.setForeground(Color.WHITE);
        lblKonf.setBounds(30, 190, 80, 25);
        contentPane.add(lblKonf);

        pwssdtxt2 = new JPasswordField();
        pwssdtxt2.setBounds(120, 190, 250, 25);
        contentPane.add(pwssdtxt2);

        JButton btnDaftar = new JButton("DAFTAR");
        btnDaftar.setBounds(150, 240, 100, 25);
        btnDaftar.setBackground(Theme.BTN_DATE);
        btnDaftar.setForeground(Color.BLACK);
        btnDaftar.addActionListener(e -> daftarAction());
        contentPane.add(btnDaftar);

        JButton btnCancel = new JButton("CANCEL");
        btnCancel.setBounds(270, 240, 100, 25);
        btnCancel.setBackground(Theme.BTN_DATE);
        btnCancel.setForeground(Color.BLACK);
        btnCancel.addActionListener(e -> {
            Login login = new Login();
            login.setVisible(true);
            dispose();
        });
        contentPane.add(btnCancel);
    }

    private void daftarAction() {
        String nama = namatxt.getText().trim();
        String username = usernametxt.getText().trim();
        String password = new String(pwssdtxt.getPassword()).trim();
        String password2 = new String(pwssdtxt2.getPassword()).trim();

        if (nama.isEmpty() || username.isEmpty() || password.isEmpty() || password2.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua kolom harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!password.equals(password2)) {
            JOptionPane.showMessageDialog(this, "Password dan konfirmasi tidak sama!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        User user = new User();
        user.setNama(nama);
        user.setUsername(username);
        user.setPassword(password);

        UserRepo repo = new UserRepo();
        try {
            repo.save(user);
            JOptionPane.showMessageDialog(this, "Registrasi berhasil! Silakan login.");
            Login login = new Login();
            login.setVisible(true);
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Gagal daftar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
