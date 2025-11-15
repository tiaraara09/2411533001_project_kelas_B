package model;

public class CostumerBuilder {
    private String id;
    private String nama;
    private String alamat;
    private String nomorHP;
    private String email;

    public CostumerBuilder() {
    }

    public CostumerBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public CostumerBuilder setNama(String nama) {
        this.nama = nama;
        return this;
    }

    public CostumerBuilder setAlamat(String alamat) {
        this.alamat = alamat;
        return this;
    }

    public CostumerBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public CostumerBuilder setNomorHP(String nomorHP) {
        this.nomorHP = nomorHP;
        return this;
    }

    public Costumer build() {
        return new Costumer(id, nama, alamat,email, nomorHP);
    }
}
