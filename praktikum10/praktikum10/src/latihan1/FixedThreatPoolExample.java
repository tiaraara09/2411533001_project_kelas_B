package latihan1;
import  java.util.concurrent.ExecutorService;
import  java.util.concurrent.Executors;

public class FixedThreatPoolExample {
    public static void main(String[] args) {
        int threatCount = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(threatCount);
        int taskCount = 5;

        ExecutorService service = Executors.newFixedThreadPool(taskCount);

        for (int i = 0; i < taskCount; i++) {
            service.execute(() -> {
                System.out.println("printing document by threat : "
                        + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        service.shutdown();
    }
}
