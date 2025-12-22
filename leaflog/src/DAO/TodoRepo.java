package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Config.database;
import Enum.TaskStatus;
import model.TodoItem;

public class TodoRepo implements TodoDao {

    private Connection conn;

    final String insert = "INSERT INTO todolist (title, todo, due_date, status) VALUES (?, ?, ?, ?);";
    final String select = "SELECT * FROM todolist;";
    final String selectbyStatus = "SELECT * FROM todolist"
    							+ " WHERE (? IS NULL or title LIKE ?) AND (? IS NULL or due_date =?);";
    final String delete = "DELETE FROM todolist WHERE id=?;";
    final String update = "UPDATE todolist SET title=?, todo=?, due_date=?, status=? WHERE id=?;";

    public TodoRepo() {
        conn = database.koneksi();
    }

    @Override
    public void save(TodoItem item) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(insert);
            st.setString(1, item.getTitle());
            st.setString(2, item.getTodo1());
            st.setDate(3, java.sql.Date.valueOf(item.getDueDate()));
            st.setString(4, item.getStatus().name());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<TodoItem> show() {
        List<TodoItem> ls = new ArrayList<>();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(select);

            while (rs.next()) {
                TodoItem item = new TodoItem(); 
                item.setId(rs.getInt("id"));
                item.setTitle(rs.getString("title"));
                item.setTodo(rs.getString("todo"));
                item.setDueDate(rs.getDate("due_date").toLocalDate());
                item.setStatus(TaskStatus.valueOf(rs.getString("status")));
                ls.add(item);
            }
        } catch (SQLException e) {
            Logger.getLogger(TodoDao.class.getName()).log(Level.SEVERE, null, e);
        }

        return ls;
    }

    @Override
    public void update(TodoItem todo) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(update);
            st.setString(1, todo.getTitle());
            st.setString(2, todo.getTodo1());
            st.setDate(3, java.sql.Date.valueOf(todo.getDueDate()));
            st.setString(4, todo.getStatus().name());
            st.setInt(5, todo.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(int id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(delete);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

	@Override
	public List<TodoItem> searchByTitle(String title, LocalDate date) {
	    List<TodoItem> list = new ArrayList<>();

	    try (PreparedStatement st = conn.prepareStatement(select)) {

	        // ---- TITLE ----
	        if (title == null || title.isEmpty()) {
	            st.setNull(1, Types.VARCHAR);
	            st.setNull(2, Types.VARCHAR);
	        } else {
	            st.setString(1, title);
	            st.setString(2, "%" + title + "%");
	        }

	        // ---- DATE ----
	        if (date == null) {
	            st.setNull(3, Types.DATE);
	            st.setNull(4, Types.DATE);
	        } else {
	            st.setDate(3, Date.valueOf(date));
	            st.setDate(4, Date.valueOf(date));
	        }

	        ResultSet rs = st.executeQuery();

	        while (rs.next()) {
	            TodoItem item = new TodoItem();
	            item.setId(rs.getInt("id"));
	            item.setTitle(rs.getString("title"));
	            item.setTodo(rs.getString("todo"));
	            item.setDueDate(rs.getDate("due_date").toLocalDate());
	            item.setStatus(TaskStatus.valueOf(rs.getString("status")));
	            list.add(item);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return list;
	}


	}
