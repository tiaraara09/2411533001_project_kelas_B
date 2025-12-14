package latihan4;

import java.awt.EventQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GUItugas4 extends JFrame {

    private JPanel contentPane;
    private JTextField txtThread;
    private JTextField txtTask;
    private JButton btnProses;
    private JTextArea logArea;
    private JTextArea taskArea;
    private JLabel lblStatus;

    private ExecutorService threadPool;
    private int taskCount;
    private int finishedTask = 0;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                GUItugas4 frame = new GUItugas4();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public GUItugas4() {
        setTitle("Aplikasi Thread Pool dengan GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 850, 580);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("Aplikasi Thread Pool dengan GUI", SwingConstants.CENTER);
        lblTitle.setBounds(10, 10, 810, 20);
        contentPane.add(lblTitle);

        JLabel lblThread = new JLabel("Jumlah Thread");
        lblThread.setBounds(20, 45, 100, 15);
        contentPane.add(lblThread);

        txtThread = new JTextField();
        txtThread.setBounds(120, 42, 60, 20);
        contentPane.add(txtThread);

        JLabel lblTask = new JLabel("Jumlah Tugas");
        lblTask.setBounds(200, 45, 100, 15);
        contentPane.add(lblTask);

        txtTask = new JTextField();
        txtTask.setBounds(300, 42, 60, 20);
        contentPane.add(txtTask);

        btnProses = new JButton("Mulai Proses");
        btnProses.setBounds(380, 42, 150, 22);
        btnProses.addActionListener(e -> startProcessing());
        contentPane.add(btnProses);

        JButton btnClear = new JButton("Bersihkan Log");
        btnClear.setBounds(550, 42, 160, 22);
        btnClear.addActionListener(e -> clearLog());
        contentPane.add(btnClear);

        taskArea = new JTextArea();
        taskArea.setEditable(false);
        JScrollPane spTask = new JScrollPane(taskArea);
        spTask.setBounds(20, 80, 300, 400);
        contentPane.add(spTask);

        logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane spLog = new JScrollPane(logArea);
        spLog.setBounds(340, 80, 480, 400);
        contentPane.add(spLog);

        lblStatus = new JLabel("");
        lblStatus.setBounds(20, 500, 500, 20);
        contentPane.add(lblStatus);
    }

    private void startProcessing() {
        try {
            int threadCount = Integer.parseInt(txtThread.getText());
            taskCount = Integer.parseInt(txtTask.getText());
            finishedTask = 0;

            if (threadCount <= 0 || taskCount <= 0) {
                JOptionPane.showMessageDialog(
                        this,
                        "Jumlah thread dan tugas harus lebih dari 0",
                        "Input Tidak Valid",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            btnProses.setEnabled(false);
            taskArea.setText("");
            logArea.setText("");
            logArea.append("=== Memulai Proses Baru ===\n");
            lblStatus.setText("Memproses " + taskCount + " tugas dengan " + threadCount + " thread");

            threadPool = Executors.newFixedThreadPool(threadCount);

            for (int i = 0; i < taskCount; i++) {
                taskArea.append("Task #" + i + " - Waiting\n");
                threadPool.execute(new Task(i, this));
            }

            threadPool.shutdown();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Masukkan angka yang valid",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void clearLog() {
        logArea.setText("");
        taskArea.setText("");
        lblStatus.setText("Log dibersihkan");
        btnProses.setEnabled(true);
    }

    public synchronized void updateTaskStatus(int taskId, long durationMs) {
        SwingUtilities.invokeLater(() -> {
            String[] lines = taskArea.getText().split("\n");
            lines[taskId] = "Task #" + taskId + " - Completed (" + durationMs + " ms)";
            taskArea.setText(String.join("\n", lines));

            finishedTask++;
            if (finishedTask == taskCount) {
                lblStatus.setText("Semua tugas selesai");
                logArea.append("=== Semua tugas telah selesai ===\n");
                btnProses.setEnabled(true);
            }
        });
    }

    public void appendLog(String message) {
        SwingUtilities.invokeLater(() -> logArea.append(message + "\n"));
    }
}
