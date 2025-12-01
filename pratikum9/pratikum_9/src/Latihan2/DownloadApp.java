package Latihan2;

public class DownloadApp {
    public static void main(String[] args) throws InterruptedException {
        Thread f1 = new Thread(new DownloadTask("File-1"));
        Thread f2 = new Thread(new DownloadTask("File-2"));
        Thread f3 = new Thread(new DownloadTask("File-3"));

        f1.start();
        f2.start();
        f3.start();

        System.out.println("Download Started");
        try {
            f1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        f2.join();
        f3.join();
        System.out.println("Download Done");

        System.out.println("Status akhir");
        System.out.println(f1.getName()+ " : "+f1.getState());
        System.out.println(f2.getName()+ " : "+f2.getState());
        System.out.println(f3.getName()+ " : "+f3.getState());
    }
}
