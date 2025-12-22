package model;

import java.time.LocalDate;
import Enum.TaskStatus;

public class TodoItem {

    private int id;
    private String title;
    private String todo;
    private LocalDate dueDate;
    private TaskStatus status;

    // constructor lengkap
    public TodoItem(int id, String title, String todo, LocalDate dueDate, TaskStatus status) {
        this.id = id;
        this.title = title;
        this.todo = todo;
        this.dueDate = dueDate;
        this.status = status;
    }

    public TodoItem() {
		// TODO Auto-generated constructor stub
	}

	public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getTodo1() { return todo; }
    public void setTodo(String todo) { this.todo = todo; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public TaskStatus getStatus() { return status; }
    public void setStatus(TaskStatus status) { this.status = status; }

    @Override
    public String toString() {
        return "TodoItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", todo='" + todo + '\'' +
                ", dueDate=" + dueDate +
                ", status=" + status +
                '}';
    }
}
