public interface BahanBakar {
    String bbm();

    default String jenisBahanBakar(){
        return bbm();
    }

    public default void infokonsumsi(){
        System.out.println("Info Konsumsi : Konsumsi bahan bakar tengantung kapasitas mesin");
    }
}
