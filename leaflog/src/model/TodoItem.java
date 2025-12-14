package model;
 
  import java.time.LocalDate;

import Enum.TaskStatus;

public class TodoItem {
  

    private int id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private TaskStatus status;


    public TodoItem(int id, String title, String description, LocalDate dueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = TaskStatus.InProgress; 
    }

    
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public void markAsDone() {
        this.status = TaskStatus.Done;
    }

    public void markAsInProgress() {
        this.status = TaskStatus.InProgress;
    }

    @Override
    public String toString() {
        return "TodoItem {" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", status=" + status +
                '}';
    }
}

