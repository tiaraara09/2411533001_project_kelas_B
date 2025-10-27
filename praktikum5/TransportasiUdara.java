public interface TransportasiUdara extends BahanBakar {
    String jenis();
    public default void jenisPenerbangan(){
        System.out.println("jenis penerbangan : "+jenis());
    }
}
