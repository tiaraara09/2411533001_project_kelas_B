package UI;

import java.awt.EventQueue;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import DAO.TodoRepo;
import Enum.TaskStatus;
import model.TodoItem;

public class TodoUpdate extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtTitle;
    private JTextPane txtDesc;
    private JComboBox<Integer> haribox;
    private JComboBox<String> blnbox;
    private JComboBox<String> thnbox;
    private JComboBox<TaskStatus> statusbox;
    private int todoId; // menyimpan ID todo yang diupdate

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TodoUpdate frame = new TodoUpdate(new TodoItem()); // contoh dummy
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Constructor menerima TodoItem yang akan diupdate
    public TodoUpdate(TodoItem todo) {
        setTitle("Update Todo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 350);
        contentPane = new JPanel();
        contentPane.setBackground(Theme.BG_CALENDAR);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // ===== TITLE =====
        JLabel lblTitle = new JLabel("Judul:");
        lblTitle.setBounds(10, 20, 60, 25);
        contentPane.add(lblTitle);

        txtTitle = new JTextField(todo.getTitle());
        txtTitle.setBounds(80, 20, 330, 25);
        contentPane.add(txtTitle);

        // ===== DESKRIPSI =====
        JLabel lblDesc = new JLabel("Deskripsi:");
        lblDesc.setBounds(10, 60, 60, 25);
        contentPane.add(lblDesc);

        txtDesc = new JTextPane();
        txtDesc.setText(todo.getTodo1());
        txtDesc.setBounds(80, 60, 330, 70);
        contentPane.add(txtDesc);

        // ===== DUE DATE =====
        JLabel lblDate = new JLabel("Due Date:");
        lblDate.setBounds(10, 150, 60, 25);
        contentPane.add(lblDate);

        haribox = new JComboBox<>();
        haribox.addItem(0);
        for (int i = 1; i <= 31; i++) haribox.addItem(i);
        haribox.setBounds(80, 150, 60, 25);
        contentPane.add(haribox);

        blnbox = new JComboBox<>(new String[]{
                "Bulan","Jan","Feb","Mar","Apr","Mei","Jun",
                "Jul","Agu","Sep","Okt","Nov","Des"
        });
        blnbox.setBounds(150, 150, 80, 25);
        contentPane.add(blnbox);

        thnbox = new JComboBox<>(new String[]{"Tahun","2024","2025","2026"});
        thnbox.setBounds(240, 150, 80, 25);
        contentPane.add(thnbox);

        // Set tanggal sesuai TodoItem jika ada
        LocalDate dueDate = todo.getDueDate();
        if (dueDate != null) {
            haribox.setSelectedItem(dueDate.getDayOfMonth());
            blnbox.setSelectedIndex(dueDate.getMonthValue());
            thnbox.setSelectedItem(String.valueOf(dueDate.getYear()));
        }

        // ===== STATUS =====
        JLabel lblStatus = new JLabel("Status:");
        lblStatus.setBounds(10, 190, 60, 25);
        contentPane.add(lblStatus);

        statusbox = new JComboBox<>();
        statusbox.addItem(TaskStatus.Todo);
        statusbox.addItem(TaskStatus.InProgress);
        statusbox.addItem(TaskStatus.Done);
        statusbox.setSelectedItem(todo.getStatus());
        statusbox.setBounds(80, 190, 150, 25);
        contentPane.add(statusbox);

        // ===== BUTTON SAVE =====
        JButton btnSave = new JButton("Update");
        btnSave.setBounds(80, 240, 100, 30);
        contentPane.add(btnSave);
        todoId = todo.getId();

        btnSave.addActionListener(e -> {
            try {
                todo.setTitle(txtTitle.getText());
                todo.setTodo(txtDesc.getText());
                todo.setStatus((TaskStatus) statusbox.getSelectedItem());

                int day = (int) haribox.getSelectedItem();
                int month = blnbox.getSelectedIndex();
                int year = Integer.parseInt(thnbox.getSelectedItem().toString());

                if (day == 0 || month == 0 || thnbox.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(this, "Tanggal belum lengkap");
                    return;
                }

                todo.setDueDate(LocalDate.of(year, month, day));

                new TodoRepo().update(todo);
                JOptionPane.showMessageDialog(this, "Todo berhasil diupdate");
                new TodoListFrm().setVisible(true);
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        // ===== BUTTON CANCEL =====
        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(250, 240, 100, 30);
        btnCancel.addActionListener(e -> {
            new TodoListFrm().setVisible(true);
            dispose();
        });
        contentPane.add(btnCancel);
    }
}
