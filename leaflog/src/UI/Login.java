package UI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField usernametxt;
    private JPasswordField pwssdtxt;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Login frame = new Login();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(null);
        contentPane.setLayout(null);
        contentPane.setBackground(Theme.BG_CALENDAR);
        setContentPane(contentPane);

        JLabel lblNewLabel = new JLabel("WELCOME");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel.setBounds(133, 40, 173, 25);
        contentPane.add(lblNewLabel);

        usernametxt = new JTextField();
        usernametxt.setBounds(150, 90, 200, 25);
        contentPane.add(usernametxt);
        usernametxt.setColumns(10);

        pwssdtxt = new JPasswordField();
        pwssdtxt.setBounds(150, 130, 200, 25);
        contentPane.add(pwssdtxt);

        JButton btnLogin = new JButton("LOGIN");
        btnLogin.setBounds(150, 170, 90, 25);
        btnLogin.setBackground(Theme.BTN_DATE);
        btnLogin.setForeground(Color.BLACK);
        btnLogin.addActionListener((ActionEvent e) -> {
            Menu menu = new Menu();
            menu.setVisible(true);
            dispose();
        });
        contentPane.add(btnLogin);

        JButton btnDaftar = new JButton("DAFTAR");
        btnDaftar.setBounds(260, 170, 90, 25);
        btnDaftar.setBackground(Theme.BTN_DATE);
        btnDaftar.setForeground(Color.BLACK);
        btnDaftar.addActionListener((ActionEvent e) -> {
            Daftar daftar = new Daftar();
            daftar.setVisible(true);
            dispose();
        });
        contentPane.add(btnDaftar);

        JLabel lblUser = new JLabel("Username");
        lblUser.setForeground(Color.WHITE);
        lblUser.setBounds(60, 90, 80, 25);
        contentPane.add(lblUser);

        JLabel lblPass = new JLabel("Password");
        lblPass.setForeground(Color.WHITE);
        lblPass.setBounds(60, 130, 80, 25);
        contentPane.add(lblPass);
    }
}
