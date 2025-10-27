public class Main {
    public static void main(String[] args) {
        Mobil a = new Mobil("Toyota", "Avanza", "2021");
        Bus b = new Bus("Mercedes-Benz", "Bus Pariwisata", "2021");
        a.tampilkanInfo();
        a.nyalakanMesin();
        a.jenisBahanBakar();
        a.infokonsumsi();
        a.fiturMobil();
        System.out.println();
        b.tampilkanInfo();
        b.nyalakanMesin();
        b.jenisBahanBakar();
        b.infokonsumsi();
        b.fiturBus();
        Bus.JadwalPerjalanan x = b.new JadwalPerjalanan("Jakarta-bandung", "08.00");
        x.infoPerjalananBus();
        System.out.println();
        Pesawat f = new Pesawat("Garuda", "Boeing 737", "100");
        f.tampilkanInfo();
        f.nyalakanMesin();
        f.jenisBahanBakar();
    }
}
