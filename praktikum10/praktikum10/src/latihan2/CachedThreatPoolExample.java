package latihan2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreatPoolExample {
    public static void main(String[] args) {
        int TaskCount = 10;

        ExecutorService service = Executors.newCachedThreadPool();

        for (int i = 0; i < TaskCount; i++) {
            service.execute(() -> {
                System.out.println(
                        "Printing Document by Thrat : "
                                + Thread.currentThread().getName()
                );

                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
