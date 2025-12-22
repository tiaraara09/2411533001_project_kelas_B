package model;

import java.sql.Date;
import java.time.LocalDate;
import Enum.MoodType;

public class Jurnal {
	private String id;
	private Date tanggal;
    private String judul;
    private String konten;

   
    public Jurnal() {
    	
    }
	    
     
    public Jurnal(Date tanggal, String id, String judul, String konten) {
    	this.id = id;
    	this.tanggal=tanggal;
    	this.judul = judul;
    	this.konten = konten;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    

    public Date getTanggal() {
		return tanggal;
	}


	public void setTanggal(Date tanggal) {
		this.tanggal = tanggal;
	}


	public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getKonten() {
        return konten;
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }
    }
