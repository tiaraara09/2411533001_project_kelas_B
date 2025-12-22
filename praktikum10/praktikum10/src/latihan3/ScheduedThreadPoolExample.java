package latihan3;

import java.util.Calendar;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduedThreadPoolExample {
    public static void main(String[] args) {
        int corePoolSize = 2;

        try(ScheduledThreadPoolExecutor treadPool = new ScheduledThreadPoolExecutor(corePoolSize)){
            Runnable task1 = new Comand("Task1");
            Runnable task2 = new Comand("Task2");

            System.out.println("Current time : "+ Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+
            ":"+Calendar.getInstance().get(Calendar.SECOND));

            treadPool.scheduleAtFixedRate(task1, 2, 8, TimeUnit.SECONDS);
            treadPool.scheduleWithFixedDelay(task2, 5, 5, TimeUnit.SECONDS);

            try{
                Thread.sleep(3000);
            }catch(Exception e){
                e.printStackTrace();
            }
            treadPool.shutdown();
        }
    }
}
