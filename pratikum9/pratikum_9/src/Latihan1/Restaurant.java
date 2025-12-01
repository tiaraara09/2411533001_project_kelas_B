package Latihan1;

public class Restaurant {
    public static void main(String[] args) {
        Cooking T1 = new Cooking("Pasta");
        Cooking T2 = new Cooking("Salad");
        Cooking T3 = new Cooking("Dessert");
        Cooking T4 = new Cooking("Rice");

        T1.run();
        T2.run();
        T3.run();
        T4.run();
        Thread t1 = new CookingTask("Pasta");
        Thread t2 = new CookingTask("Salad");
        Thread t3 = new CookingTask("Dessert");
        Thread t4 = new CookingTask("Rice");

        t1.run();
        t2.run();
        t3.run();
        t4.run();
    }
}
