package tabel;

import model.TodoItem;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TabelTodo extends AbstractTableModel {
	List<TodoItem> ls;
	private String [] columnNames = { "id", "title", "todo", "duedate", "taskstatus"};
	public TabelTodo(List<TodoItem> ls) {
		this.ls=ls;
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return ls.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 5;
	}
	
	public String getColumnCount(int column) {
		// TODO Auto-generated method stub
		return columnNames[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		switch(columnIndex) {
		case 0:
			return ls.get(rowIndex).getId();
		case 1:
			return ls.get(rowIndex).getTitle();
		case 2: 
			return ls.get(rowIndex).getTodo1();
		case 3:
			return ls.get(rowIndex).getDueDate();
		case 4:
			return ls.get(rowIndex).getStatus();
		default:
				return null;
		
		}
	}

}
