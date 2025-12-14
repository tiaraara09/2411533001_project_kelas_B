package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import Enum.TaskStatus;


public class TodoList {
   
public class ToDoList {

    private List<TodoItem> items;

    public ToDoList() {
        items = new ArrayList<>();
    }

    public void addItem(TodoItem item) {
        items.add(item);
    }

    public void removeItem(int id) {
        items.removeIf(item -> item.getId() == id);
    }

    public List<TodoItem> getItems() {
        return items;
    }

    public TodoItem getItemById(int id) {
        return items.stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<TodoItem> getItemsByDate(LocalDate date) {
        return items.stream()
                .filter(item -> item.getDueDate().equals(date))
                .collect(Collectors.toList());
    }

    public List<TodoItem> getInProgressItems() {
        return items.stream()
                .filter(item -> item.getStatus() == TaskStatus.InProgress)
                .collect(Collectors.toList());
    }

    public List<TodoItem> getDoneItems() {
        return items.stream()
                .filter(item -> item.getStatus() == TaskStatus.Done)
                .collect(Collectors.toList());
    }

    public List<TodoItem> searchByDueDate(LocalDate duedate) {
        return items.stream()
                .filter(item -> item.getDueDate().equals(duedate))
                .collect(Collectors.toList());
    }
}


}
