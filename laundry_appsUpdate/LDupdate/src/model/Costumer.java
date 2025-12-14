package model;

import javax.swing.JTextField;

public class Costumer {
    String id;
    String nama;
    String alamat;
    String email;
    String nomor_hp;

    public Costumer(String id, String nama,String email, String alamat, String nomor_hp) {
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.alamat = alamat;
        this.nomor_hp = nomor_hp;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getEmail() {
        return email;
    }
    public String getAlamat() {
        return alamat;
    }

    public String getNomor_hp() {
        return nomor_hp;
    }

    /*
    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setNomor_hp(String nomor_hp) {
        this.nomor_hp = nomor_hp;
    }
    public void setId(String id) {
        this.id = id;
    }
     */

}