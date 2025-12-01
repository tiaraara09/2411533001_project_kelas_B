package Latihan3;

import javax.swing.*;
import java.awt.*;

public class DownloadManagerApp extends JFrame {

    private JProgressBar bar1, bar2, bar3;
    private JButton downloadBtn;

    public DownloadManagerApp() {

        setTitle("Download Manager App");
        setSize(450, 280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel Judul
        JLabel title = new JLabel("Download Manager App", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        // Panel Progress
        JPanel panel = new JPanel(new GridLayout(6, 1, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        panel.add(new JLabel("File 1"));
        bar1 = new JProgressBar(0, 100);
        panel.add(bar1);

        panel.add(new JLabel("File 2"));
        bar2 = new JProgressBar(0, 100);
        panel.add(bar2);

        panel.add(new JLabel("File 3"));
        bar3 = new JProgressBar(0, 100);
        panel.add(bar3);

        add(panel, BorderLayout.CENTER);

        // Tombol
        JPanel bottomPanel = new JPanel();
        downloadBtn = new JButton("Downloading");
        bottomPanel.add(downloadBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        downloadBtn.addActionListener(e -> startDownloads());
    }

    private void startDownloads() {

        // Thread untuk File 1
        new Thread(() -> simulateDownload(bar1, "File-1")).start();

        // Thread untuk File 2
        new Thread(() -> simulateDownload(bar2, "File-2")).start();

        // Thread untuk File 3
        new Thread(() -> simulateDownload(bar3, "File-3")).start();
    }

    private void simulateDownload(JProgressBar bar, String name) {
        try {
            for (int i = 10; i <= 100; i += 10) {
                bar.setValue(i);
                System.out.println(name + " progress: " + i + "%");
                Thread.sleep(300); // simulasi waktu
            }
            System.out.println(name + " selesai diunduh!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DownloadManagerApp().setVisible(true);
        });
    }
}
