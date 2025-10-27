public class Mobil extends Kendaraan implements BahanBakar{

    public Mobil(String a, String b, String c) {
        super(a, b, c);
    }
    @Override
    public String bbm(){
        return "bensin";
    }

    public void nyalakanMesin(){
        System.out.println("Nyalakan Meain : Tekan Tombol Start");
    }

    @Override
    public String jenisBahanBakar() {
        System.out.println("jenis bahan bakar : "+bbm());
        return bbm();
    }

    public void fiturMobil(){
        System.out.println("Fitur Mobil : Memiliki AC dan Audio Premium");
    }
}
