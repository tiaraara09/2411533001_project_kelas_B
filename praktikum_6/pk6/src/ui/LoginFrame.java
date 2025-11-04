package ui;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import error.ValidationException;
import model.User;
import service.LoginService;
import util.ValidationUtil;
import java.awt.Font;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtUsername;
    private JPasswordField txtPassword; // gunakan JPasswordField

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LoginFrame frame = new LoginFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public LoginFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("LOGIN FRAME");
        lblTitle.setFont(new Font("Verdana", Font.BOLD, 20));
        lblTitle.setBounds(130, 15, 200, 26);
        contentPane.add(lblTitle);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(54, 78, 68, 20);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(54, 141, 89, 14);
        contentPane.add(lblPassword);

        txtUsername = new JTextField();
        txtUsername.setBounds(64, 109, 215, 20);
        contentPane.add(txtUsername);
        txtUsername.setColumns(10);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(64, 166, 215, 20);
        contentPane.add(txtPassword);

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener((ActionEvent e) -> {
            String userValue = txtUsername.getText();
            String passwordValue = new String(txtPassword.getPassword());

            User user = new User(userValue, passwordValue);

            try {
                ValidationUtil.validate(user);
                LoginService loginService = new LoginService();

                if (loginService.authenticate(user)) {
                    JOptionPane.showMessageDialog(null, "Login Berhasil!");
                    dispose(); // tutup form login
                } else {
                    JOptionPane.showMessageDialog(null, "Login gagal: Username atau Password salah.");
                }

            } catch (ValidationException | NullPointerException exception) {
                System.out.println("Data tidak valid: " + exception.getMessage());
                JOptionPane.showMessageDialog(null, "Login Gagal: " + exception.getMessage());
            } finally {
                System.out.println("Blok finally dijalankan.");
            }
        });
        btnLogin.setBounds(190, 209, 89, 23);
        contentPane.add(btnLogin);
    }
}
