package model;

import java.time.LocalDate;
import Enum.MoodType;

public class Jurnal extends MoodTracker{
    public int id;
    public String judul;
    public String konten;

    public Jurnal(int id, String judul, String konten, MoodType mood, LocalDate tnggl) {
        super(mood, LocalDate.now());
        this.id = id;
        this.judul = judul;
        this.konten = konten;
    }
    
    public void displayMoodInfo(MoodType mood, LocalDate tnggl) {
      System.out.println("Mood hari ini" + tnggl + mood);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
