public interface Maskapai {
    String nama();
    public default void namaMaskapai(){
        System.out.println("nama maskapai : "+nama());
    }
}
