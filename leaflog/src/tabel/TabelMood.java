package tabel;

import model.MoodTracker;

import java.time.LocalDate;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TabelMood extends AbstractTableModel{
	List<MoodTracker> ls;
	private String[] columnNames = { "id", "tnggl", "mood"};
	public TabelMood(List<MoodTracker> ls) {
		this.ls = ls;
	}
	

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return ls.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return columnNames[column];
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		switch (columnIndex) {
		case 0:
			return ls.get(rowIndex).getId();
		case 1: 
			return ls.get(rowIndex).getTnggl();
		case 2: 
			return ls.get(rowIndex).getMood();
			default:
				return null;
		}
		
	}

}
