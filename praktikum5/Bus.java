public class Bus extends Kendaraan implements TransportasiUmum {

    public Bus(String a, String b, String c) {
        super(a, b, c);
    }

    public void nyalakanMesin(){
        System.out.println("Nyalakan mesin : putar kunci untuk menyalakan");
    }

    @Override
    public String kapasitas() {
        return "45 penumpang";
    }
    
    @Override
    public String kapasitasPenumpang() {
        return kapasitas();
    }

    @Override
    public String bbm() {
        return "solar";
    }


    @Override
    public String jenisBahanBakar() {
        System.out.println("jenis bahan bakar : "+bbm());
        return bbm();
    }

    @Override
    public void infokonsumsi() {
    }

    public void fiturBus(){
        System.out.println("Fitur bus : bus dilengkapi dengan kursi nyaman dan fasilitas hiburan");
    }
    

    //inner
    public class JadwalPerjalanan{
        String rute, waktu;

        public JadwalPerjalanan(String a, String b) {
            this.rute = a;
            this.waktu = b;
        }

        public void infoPerjalananBus(){
            System.out.println("Perjalanan : "+rute+", Waktu berangkat : "+waktu);
        }

    }
}
