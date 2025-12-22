package UI;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DAO.TodoRepo;
import model.TodoItem;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TodoListFrm extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tablelist;
    private JTextField txtSearch;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TodoListFrm frame = new TodoListFrm();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TodoListFrm() {
        setTitle("Todo List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 650, 466);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        contentPane.setLayout(null);
        contentPane.setBackground(Theme.BG_CALENDAR); 
        setContentPane(contentPane);

        JLabel lblTitle = new JLabel("To-Do List");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTitle.setForeground(Theme.TEXT_WHITE);
        lblTitle.setBounds(180, 10, 250, 30);
        contentPane.add(lblTitle);

        // Tombol Add
        JButton btnAdd = new JButton("Add");
        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnAdd.setBackground(Theme.BTN_DATE); 
        btnAdd.setBounds(20, 60, 80, 30);
        btnAdd.addActionListener(e -> {
            Todolist addForm = new Todolist();
            addForm.setVisible(true);
            dispose();
        });
        contentPane.add(btnAdd);

        // Tombol Update
        JButton btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnUpdate.setBackground(Theme.BTN_DATE);
        btnUpdate.setBounds(110, 60, 90, 30);
        btnUpdate.addActionListener(e -> updateTodo());
        contentPane.add(btnUpdate);

        // Tombol Delete
        JButton btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnDelete.setBackground(Theme.BTN_DATE);
        btnDelete.setBounds(210, 60, 100, 30);
        btnDelete.addActionListener(e -> deleteTodo());
        contentPane.add(btnDelete);

        // Search
        JLabel lblSearch = new JLabel("Search Todo:");
        lblSearch.setForeground(Theme.TEXT_WHITE);
        lblSearch.setBounds(20, 110, 100, 25);
        contentPane.add(lblSearch);

        txtSearch = new JTextField();
        txtSearch.setBounds(130, 110, 200, 25);
        contentPane.add(txtSearch);

        JButton btnSearch = new JButton("Search");
        btnSearch.setBackground(Theme.BTN_DATE);
        btnSearch.setBounds(340, 110, 90, 25);
        btnSearch.addActionListener(e -> searchTodo());
        contentPane.add(btnSearch);

        // Tabel
        tablelist = new JTable();
            DefaultTableModel model = new DefaultTableModel(
            new Object[]{"ID", "Title", "Todo", "DueDate", "Status"}, 0
        );
        tablelist.setModel(model);
        JScrollPane scrollPane = new JScrollPane(tablelist);
        scrollPane.setBounds(20, 150, 590, 155);
        contentPane.add(scrollPane);
        
        JButton btnBackMenu = new JButton("Back Menu");
        btnBackMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Menu ayam = new Menu();
        		ayam.setVisible(true);
        		dispose();
        	}
        });
        btnBackMenu.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnBackMenu.setBackground(new Color(232, 220, 193));
        btnBackMenu.setBounds(476, 371, 134, 30);
        contentPane.add(btnBackMenu);

        loadAllTodo();
    }

    private void loadAllTodo() {
        try {
            TodoRepo repo = new TodoRepo();
            java.util.List<TodoItem> todos = repo.show();
            loadTable(todos);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error load todo: " + ex.getMessage());
        }
    }

    private void loadTable(java.util.List<TodoItem> todos) {
        DefaultTableModel model = (DefaultTableModel) tablelist.getModel();
        model.setRowCount(0);
        for (TodoItem todo : todos) {
            model.addRow(new Object[]{
                todo.getId(),
                todo.getTitle(),
                todo.getTodo1(),
                todo.getDueDate(),
                todo.getStatus()
            });
        }
    }

    private void searchTodo() {
        String keyword = txtSearch.getText().trim();
        if (keyword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Masukkan kata kunci untuk mencari todo!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            TodoRepo repo = new TodoRepo();
            java.util.List<TodoItem> allTodos = repo.show();
            java.util.List<TodoItem> filtered = new java.util.ArrayList<>();
            for (TodoItem todo : allTodos) {
                if ((todo.getTitle() != null && todo.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                        || (todo.getTodo1() != null && todo.getTodo1().toLowerCase().contains(keyword.toLowerCase()))) {
                    filtered.add(todo);
                }
            }
            loadTable(filtered);

            if (filtered.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todo tidak ditemukan!", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error saat mencari todo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteTodo() {
        int selectedRow = tablelist.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih todo yang ingin dihapus terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Integer id = (Integer) tablelist.getValueAt(selectedRow, 0);
        String title = (String) tablelist.getValueAt(selectedRow, 1);
        String todoDesc = (String) tablelist.getValueAt(selectedRow, 2);

        int confirm = JOptionPane.showConfirmDialog(this,
                "Apakah Anda yakin ingin menghapus todo berikut?\n\n" +
                        "Judul: " + title + "\n" +
                        "Deskripsi: " + todoDesc,
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                TodoRepo repo = new TodoRepo();
                repo.delete(id);
                ((DefaultTableModel) tablelist.getModel()).removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Todo berhasil dihapus!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Gagal menghapus todo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateTodo() {
        int selectedRow = tablelist.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih todo yang ingin diupdate terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Integer id = (Integer) tablelist.getValueAt(selectedRow, 0);
        TodoRepo repo = new TodoRepo();
        TodoItem selectedTodo = repo.show().stream()
                .filter(todo -> todo.getId() == id)
                .findFirst()
                .orElse(null);

        if (selectedTodo == null) {
            JOptionPane.showMessageDialog(this, "Data tidak ditemukan!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        TodoUpdate updateForm = new TodoUpdate(selectedTodo);
        updateForm.setVisible(true);
        dispose();
    }
}
