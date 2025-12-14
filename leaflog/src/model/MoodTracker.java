package model;

import Enum.MoodType;
import java.time.LocalDate;

public abstract class MoodTracker {
    private LocalDate tnggl;
    private MoodType mood;

    public MoodTracker(MoodType mood, LocalDate tnggl) {
        this.mood = mood;
        this.tnggl = LocalDate.now();
    }

    public LocalDate getTnggl() {
        return tnggl;
    }

    public void setTnggl(LocalDate tnggl) {
        this.tnggl = tnggl;
    }

    public MoodType getMood() {
        return mood;
    }

    public void setMood(MoodType mood) {
        this.mood = mood;
    }

  public void displayMoodInfo() {
      System.out.println("Mood hari ini" + tnggl + mood);
    }

}
