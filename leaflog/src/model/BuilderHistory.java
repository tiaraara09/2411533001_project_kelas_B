package model;

import java.time.LocalDate;
import Enum.MoodType;

public class BuilderHistory {
    LocalDate tanggal;
    MoodType mood;
    String judulJurnal;
    String isiJurnal;
    String todo;

    public BuilderHistory fromMoodTracker(Mood moodTracker) {
        this.tanggal = moodTracker.getTnggl();
        this.mood = moodTracker.getMood();
        return this;
    }

    public BuilderHistory fromJurnal(Jurnal jurnal) {
        this.judulJurnal = jurnal.getJudul();
        this.isiJurnal = jurnal.getKonten();
        return this;
    }

    public BuilderHistory fromTodo(TodoItem todoItem) {
        this.todo = todoItem.getTodo1(); 
        return this;
    }

    public HistoryModel build() {
        return new HistoryModel(this);
    }
}
