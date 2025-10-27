public class Pesawat extends Kendaraan implements TransportasiUdara, Maskapai{

    public Pesawat(String a, String b, String c) {
        super(a, b, c);
    }
    
    public void nyalakanMesin(){
        System.out.println("Nyalakan Mesin : Bersiap lepas landas");
    }
    @Override
    public String jenis() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String bbm() {
        return "Avtur";
    }

    public String jenisBahanBakar() {
        System.out.println("jenis bahan bakar : "+bbm());
        return bbm();
    }

    @Override
    public String nama() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
