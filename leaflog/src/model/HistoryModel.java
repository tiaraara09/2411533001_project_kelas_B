package model;

import java.time.LocalDate;

import Enum.MoodType;

public class HistoryModel {

    private LocalDate tanggal;
    private MoodType mood;
    private String judulJurnal;
    private String isiJurnal;
    private String todo;

    public HistoryModel(LocalDate tanggal, MoodType mood,
                        String judulJurnal, String isiJurnal, String todo) {
        this.tanggal = tanggal;
        this.mood = mood;
        this.judulJurnal = judulJurnal;
        this.isiJurnal = isiJurnal;
        this.todo = todo;
    }

    public HistoryModel(BuilderHistory builderHistory) {
		// TODO Auto-generated constructor stub
	}

	public LocalDate getTanggal() {
        return tanggal;
    }

    public MoodType getMood() {
        return mood;
    }

    public String getJudulJurnal() {
        return judulJurnal;
    }

    public String getIsiJurnal() {
        return isiJurnal;
    }

    public String getTodo() {
        return todo;
    }
}
