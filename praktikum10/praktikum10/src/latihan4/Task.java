package latihan4;

public class Task implements Runnable {

    private final int taskId;
    private final GUItugas4 gui;

    public Task(int taskId, GUItugas4 gui) {
        this.taskId = taskId;
        this.gui = gui;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();

        gui.appendLog("Task #" + taskId + " mulai dieksekusi");

        try {
            Thread.sleep(500 + (int) (Math.random() * 1500));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        gui.appendLog("Task #" + taskId + " selesai dalam " + duration + " ms");
        gui.updateTaskStatus(taskId, duration);
    }
}
