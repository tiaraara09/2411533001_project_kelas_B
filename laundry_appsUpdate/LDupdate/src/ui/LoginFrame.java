package ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import model.User;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtusername;
    private JTextField txtPassword;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginFrame frame = new LoginFrame();
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
    public LoginFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 647, 413);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Laundry Apps");
        lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 50));
        lblNewLabel.setBounds(10, 11, 400, 50);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Males nyuci? Biar kami yang cuci!");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(10, 72, 311, 21);
        contentPane.add(lblNewLabel_1);

        txtusername = new JTextField();
        txtusername.setBounds(10, 149, 311, 30);
        contentPane.add(txtusername);
        txtusername.setColumns(10);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblUsername.setBounds(10, 117, 84, 21);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblPassword.setBounds(10, 193, 84, 21);
        contentPane.add(lblPassword);

        txtPassword = new JTextField();
        txtPassword.setColumns(10);
        txtPassword.setBounds(10, 234, 311, 30);
        contentPane.add(txtPassword);

        JButton btnlogin = new JButton("Login");
        btnlogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String user = txtusername.getText();
                String pass = txtPassword.getText();

                // Panggil login dari model User
                if (User.login(user, pass)) {
                    JOptionPane.showMessageDialog(null, "Login berhasil!");
                    new Mainframe().setVisible(true);
                    dispose();  // Tutup login frame
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Username atau Password salah!",
                            "Login Gagal",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        btnlogin.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnlogin.setBounds(241, 298, 179, 30);
        contentPane.add(btnlogin);
    }
}
