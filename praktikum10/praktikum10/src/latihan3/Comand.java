package latihan3;

import java.util.Calendar;

public class Comand implements Runnable{
    String taskName;
    public Comand(String taskName){
        this.taskName=taskName;
    }
    @Override
    public void run() {
        try {
            System.out.println("Task name : " + this.taskName
            + " Current time : "+ Calendar.getInstance().get(Calendar.SECOND));
            Thread.sleep(2000);
            System.out.println("Executed : "+ this.taskName + " Current time : "+ Calendar.getInstance().get(Calendar.SECOND));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
