package postmanClone.UI.Common.Tables;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public abstract class TableModel<T> extends AbstractTableModel {

	private final String[] columnNames;
	
	@SuppressWarnings("rawtypes")
	private final Class[] columnTypes;
	
	private List<T> data;

	@SuppressWarnings("rawtypes")
	public TableModel(String[] columnNames, Class[] columnTypes, List<T> data) {
		this.columnNames = columnNames;
		this.columnTypes = columnTypes;
		this.data = data;
	}

	public List<T> getData() {
		return this.data;
	}

	public void setData(List<T> data) {
		this.data = data;
		this.fireTableDataChanged();
	}
	
	public abstract T getItemById(int id); 
	
	public void deleteItem(T item) {
		this.data.remove(item);
		this.fireTableDataChanged();
	}
	
	public void addItem(T item) {
		this.data.add(item);
		this.fireTableDataChanged();
	}

	public void clear() {
		this.data = new ArrayList<T>();
		this.fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Class getColumnClass(int col) {
		return columnTypes[col];
	}
}
