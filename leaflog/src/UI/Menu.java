package UI;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class Menu extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static final Color BG_CALENDAR = new Color(80, 115, 53); // hijau gelap
    public static final Color BTN_DATE = new Color(232, 220, 193);  // krem

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Menu frame = new Menu();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Menu() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 473, 409);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        contentPane.setBackground(BG_CALENDAR); // set background hijau
        setContentPane(contentPane);

        JButton todolistbtn = new JButton("To-Do List");
        todolistbtn.setBackground(BTN_DATE);
        todolistbtn.setForeground(Color.BLACK);
        todolistbtn.addActionListener(e -> {
            TodoListFrm ayam = new TodoListFrm();
            ayam.setVisible(true);
            dispose();
        });
        todolistbtn.setBounds(165, 117, 113, 23);
        contentPane.add(todolistbtn);

        JButton Moodbtn = new JButton("Mood Tracker");
        Moodbtn.setBackground(BTN_DATE);
        Moodbtn.setForeground(Color.BLACK);
        Moodbtn.addActionListener(e -> {
            Mood gajah = new Mood();
            gajah.setVisible(true);
            dispose();
        });
        Moodbtn.setBounds(165, 166, 113, 23);
        contentPane.add(Moodbtn);

        JButton jurnalbtn = new JButton("Jurnal");
        jurnalbtn.setBackground(BTN_DATE);
        jurnalbtn.setForeground(Color.BLACK);
        jurnalbtn.addActionListener(e -> {
            Jurnal jerapah = new Jurnal();
            jerapah.setVisible(true);
            dispose();
        });
        jurnalbtn.setBounds(165, 217, 113, 23);
        contentPane.add(jurnalbtn);

        JButton Historybtn = new JButton("History");
        Historybtn.setBackground(BTN_DATE);
        Historybtn.setForeground(Color.BLACK);
        Historybtn.addActionListener(e -> {
            Call meng = new Call();
            meng.setVisible(true);
            dispose();
        });
        Historybtn.setBounds(165, 271, 113, 23);
        contentPane.add(Historybtn);

        JButton backbtn = new JButton("Back");
        backbtn.setBackground(BTN_DATE);
        backbtn.setForeground(Color.BLACK);
        backbtn.addActionListener(e -> {
            Login kucing = new Login();
            kucing.setVisible(true);
            dispose();
        });
        backbtn.setBounds(42, 338, 89, 23);
        contentPane.add(backbtn);

        JLabel judullbl = new JLabel("MENU", SwingConstants.CENTER);
        judullbl.setFont(new Font("Tahoma", Font.BOLD, 25));
        judullbl.setForeground(Color.WHITE); // teks putih agar terlihat
        judullbl.setBounds(165, 54, 113, 58);
        contentPane.add(judullbl);

        JButton Exitbtn = new JButton("Exit");
        Exitbtn.setBackground(BTN_DATE);
        Exitbtn.setForeground(Color.BLACK);
        Exitbtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    Menu.this,
                    "Yakin Ingin Keluar?",
                    "Konfirmasi",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(Menu.this, "Anda Telah Keluar");
                dispose();
            }
        });
        Exitbtn.setBounds(347, 338, 89, 23);
        contentPane.add(Exitbtn);
        
        JLabel lblLeaflog = new JLabel("LEAFLOG", SwingConstants.CENTER);
        lblLeaflog.setForeground(Color.WHITE);
        lblLeaflog.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblLeaflog.setBounds(151, 11, 139, 58);
        contentPane.add(lblLeaflog);
    }
}
