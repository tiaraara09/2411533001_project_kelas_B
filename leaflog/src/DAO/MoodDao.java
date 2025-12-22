package DAO;

import java.util.List;

import model.MoodTracker;

public interface MoodDao {
	void save(MoodTracker mt);
	public List<MoodTracker> show();
	public void update(MoodTracker mt);
	

}
