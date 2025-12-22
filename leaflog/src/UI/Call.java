package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.EventQueue;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Call extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JPanel gridPanel;
    private JLabel lblMonthYear;
    private JLabel lblToday;

    private LocalDate currentDate = LocalDate.now();
    private static final Locale LOCALE_ID = new Locale("id", "ID");

    // ===== WARNA =====
    private static final Color BG_CALENDAR = new Color(80, 115, 53);     // #507335
    private static final Color BTN_DATE = new Color(232, 220, 193);      // #e8dcc1
    private static final Color TEXT_WHITE = Color.WHITE;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Call frame = new Call();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Call() {
        setTitle("Kalender");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 476, 377);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(null);
        contentPane.setBackground(BG_CALENDAR);
        setContentPane(contentPane);

        lblMonthYear = new JLabel("", SwingConstants.CENTER);
        lblMonthYear.setBounds(10, 10, 420, 25);
        lblMonthYear.setFont(new Font("Arial", Font.BOLD, 18));
        lblMonthYear.setForeground(TEXT_WHITE);
        contentPane.add(lblMonthYear);

        lblToday = new JLabel("", SwingConstants.CENTER);
        lblToday.setBounds(10, 40, 420, 20);
        lblToday.setForeground(TEXT_WHITE);
        contentPane.add(lblToday);

        gridPanel = new JPanel();
        gridPanel.setBounds(10, 70, 420, 230);
        gridPanel.setLayout(new java.awt.GridLayout(0, 7, 5, 5));
        gridPanel.setBackground(BG_CALENDAR);
        contentPane.add(gridPanel);
        
        JButton btnNewButton = new JButton("Back Menu");
        btnNewButton.setBackground(new Color(245, 245, 220));
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Menu ayam = new Menu();
        		ayam.setVisible(true); 
        		dispose();
        		}
        });
        btnNewButton.setBounds(352, 311, 100, 23);
        contentPane.add(btnNewButton);

        loadCalendar();
    }

    private void loadCalendar() {
        gridPanel.removeAll();

        LocalDate today = LocalDate.now();
        YearMonth ym = YearMonth.from(currentDate);
        int daysInMonth = ym.lengthOfMonth();

        LocalDate firstDay = ym.atDay(1);
        int startDay = firstDay.getDayOfWeek().getValue();

        String bulanIndonesia = currentDate.getMonth()
                .getDisplayName(TextStyle.FULL, LOCALE_ID);

        lblMonthYear.setText(bulanIndonesia + " " + currentDate.getYear());

        lblToday.setText(
                "Hari ini: " +
                        today.getDayOfWeek().getDisplayName(TextStyle.FULL, LOCALE_ID)
                        + ", " + today.getDayOfMonth() + " " +
                        today.getMonth().getDisplayName(TextStyle.FULL, LOCALE_ID)
                        + " " + today.getYear()
        );

        // ===== HEADER HARI =====
        String[] hari = {"Sen", "Sel", "Rab", "Kam", "Jum", "Sab", "Min"};
        for (String h : hari) {
            JLabel lbl = new JLabel(h, SwingConstants.CENTER);
            lbl.setFont(new Font("Arial", Font.BOLD, 13));
            lbl.setForeground(TEXT_WHITE);
            gridPanel.add(lbl);
        }

        // ===== PADDING =====
        for (int i = 1; i < startDay; i++) {
            gridPanel.add(new JLabel(""));
        }

        // ===== TOMBOL TANGGAL =====
        for (int day = 1; day <= daysInMonth; day++) {

            LocalDate date = LocalDate.of(
                    currentDate.getYear(),
                    currentDate.getMonth(),
                    day
            );

            JButton btn = new JButton(String.valueOf(day));
            btn.setFont(new Font("Arial", Font.BOLD, 12));
            btn.setFocusPainted(false);
            btn.setBackground(BTN_DATE);
            btn.setBorder(new LineBorder(Color.DARK_GRAY));
            btn.setOpaque(true);

            if (date.equals(today)) {
                btn.setBorder(new LineBorder(Color.BLUE, 3));
            }

            btn.addActionListener(e ->
                    new History(date).setVisible(true)
            );

            gridPanel.add(btn);
        }

        gridPanel.revalidate();
        gridPanel.repaint();
    }
}
