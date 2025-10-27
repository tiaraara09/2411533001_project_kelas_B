public abstract class Kendaraan {
    String merk, model, tahunProduksi;

    public Kendaraan(String a, String b, String c){
        this.merk = a;
        this.model = b;
        this.tahunProduksi = c;
    }

    public final void tampilkanInfo(){
        System.out.println("Merk : "+merk);
        System.out.println("Model : "+model);
        System.out.println("Tahun Poduksi : "+tahunProduksi);
    }
}
