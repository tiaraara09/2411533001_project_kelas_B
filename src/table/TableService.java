package table;

import model.Service;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TableService extends AbstractTableModel {
	List<Service> ls;
	private String[] columnNames = {"id", "jenis", "harga", "status"};
	public TableService(List<Service> ls) {
		this.ls = ls;
	}

	@Override
	public int getRowCount() {
		return ls.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		switch (columnIndex) {
		case 0:
		return ls.get(rowIndex).getId();
		case 1:
			return ls.get(rowIndex).getjenis();
		case 2:
			return ls.get(rowIndex).getharga();
		case 3:
			return ls.get(rowIndex).getstatus();
			default:
				return null;
		}
	}
	

}