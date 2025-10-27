public interface TransportasiUmum extends BahanBakar {
    String kapasitas();
    public default String kapasitasPenumpang(){
        return kapasitas();
    }
}
