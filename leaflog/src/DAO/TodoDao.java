package DAO;

import java.time.LocalDate;
import java.util.List;

import Enum.TaskStatus;
import model.TodoItem;

public interface TodoDao {
	void save(TodoItem item);
	public List<TodoItem> show();
	public List<TodoItem> searchByTitle(String title, LocalDate date);
	public void delete(int id);
	public void update(TodoItem item);
	

	

}
