package tabel;

import model.Jurnal;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TabelJurnal extends AbstractTableModel {
	List<Jurnal> ls;
	private String[] columnNames = {"id", "judul", "konten", "tnggl", "mood"};
	public TabelJurnal (List<Jurnal> ls) {
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
		return 5;
	}
	
	public String getColumnNames (int column) {
		return columnNames [column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		switch (columnIndex) {
		case 0:
			return ls.get(rowIndex).getId();
		case 1:
			return ls.get(rowIndex).getJudul();
		case 2:
			return ls.get(rowIndex).getKonten();
		case 3:
			default:		
		return null;
		}
		
	}

}
