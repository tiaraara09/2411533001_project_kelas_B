package UI;

import java.awt.Component;
import java.awt.EventQueue;
import java.time.LocalDate;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import DAO.TodoRepo;
import Enum.TaskStatus;
import model.TodoItem;

public class Todolist extends JFrame {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Todolist frame = new Todolist();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private JPanel contentPane;
    private JTextField txtTitle;
    private JTextPane txtDesc;
    private JComboBox<Integer> haribox;
    private JComboBox<String> blnbox;
    private JComboBox<String> thnbox;
    private JComboBox<TaskStatus> statusbox;
    
    public Todolist() {
        setTitle("Add To-Do List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 522, 375);

        contentPane = new JPanel();
        contentPane.setBackground(Theme.BG_CALENDAR);
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // ===== TITLE =====
        JLabel lblTitle = new JLabel("Judul");
        lblTitle.setBounds(10, 30, 60, 20);
        contentPane.add(lblTitle);

        txtTitle = new JTextField();
        txtTitle.setBounds(80, 30, 350, 22);
        contentPane.add(txtTitle);

        // ===== DESKRIPSI =====
        JLabel lblDesc = new JLabel("List");
        lblDesc.setBounds(10, 70, 60, 20);
        contentPane.add(lblDesc);

        txtDesc = new JTextPane();
        txtDesc.setBounds(80, 70, 350, 70);
        contentPane.add(txtDesc);

        //  DATE 
        JLabel lblDate = new JLabel("Due Date");
        lblDate.setBounds(10, 160, 60, 20);
        contentPane.add(lblDate);

        haribox = new JComboBox<>();
        haribox.addItem(0);
        for(int i=1;i<=31;i++) haribox.addItem(i);
        haribox.setBounds(80, 160, 80, 22);
        contentPane.add(haribox);

        blnbox = new JComboBox<>(new String[]{
            "Bulan","Jan","Feb","Mar","Apr","Mei","Jun",
            "Jul","Agu","Sep","Okt","Nov","Des"
        });
        blnbox.setBounds(170, 160, 80, 22);
        contentPane.add(blnbox);

        thnbox = new JComboBox<>(new String[]{"Tahun","2024","2025","2026"});
        thnbox.setBounds(260, 160, 80, 22);
        contentPane.add(thnbox);

        // ===== STATUS =====
        JLabel lblStatus = new JLabel("Status");
        lblStatus.setBounds(10, 200, 60, 20);
        contentPane.add(lblStatus);

         statusbox = new JComboBox<>();
        		statusbox.addItem(null);
        		statusbox.addItem(TaskStatus.Todo);
        		statusbox.addItem(TaskStatus.InProgress);
        		statusbox.addItem(TaskStatus.Done);
        
        statusbox.setBounds(80, 200, 170, 22);
        contentPane.add(statusbox);

        //  BUTTON SAVE 
        JButton btnSave = new JButton("Save");
        btnSave.setBounds(80, 260, 90, 25);
        btnSave.addActionListener(e -> saveTodo());
        contentPane.add(btnSave);

        // ===== BUTTON CANCEL =====
        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(340, 260, 90, 25);
        btnCancel.addActionListener(e -> {
            new TodoListFrm().setVisible(true);
            dispose();
        });
        contentPane.add(btnCancel);
    }

    // SAVE LOGIC 
    private void saveTodo() {
        try {
            int day = (int) haribox.getSelectedItem();
            int month = blnbox.getSelectedIndex();
            int year = Integer.parseInt(thnbox.getSelectedItem().toString());

            if(day==0 || month==0 || thnbox.getSelectedIndex()==0){
                JOptionPane.showMessageDialog(this,"Tanggal belum lengkap");
                return;
            }

            LocalDate date = LocalDate.of(year, month, day);

            TodoItem item = new TodoItem();
            item.setTitle(txtTitle.getText());
            item.setTodo(txtDesc.getText());
            TaskStatus status = (TaskStatus) statusbox.getSelectedItem();
            item.setStatus(status);
            item.setDueDate(date);

            new TodoRepo().save(item);

            JOptionPane.showMessageDialog(this,"Todo berhasil disimpan");
            new TodoListFrm().setVisible(true);
            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Error: "+e.getMessage());
        }
    }
}
