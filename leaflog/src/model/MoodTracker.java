package model;

import Enum.MoodType;
import java.time.LocalDate;

public abstract class MoodTracker {
	public int id;
    protected LocalDate tnggl;
    public MoodType mood;
    
    public MoodTracker() {
    	
    }
    public MoodTracker(MoodType mood, LocalDate tnggl) {
    	this.id = id;
        this.mood = mood;
        this.tnggl = LocalDate.now();
    }
    
    public int getId() {
    	return id;
    }
    
    public void setId(int id) {
    	this.id = id;
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
