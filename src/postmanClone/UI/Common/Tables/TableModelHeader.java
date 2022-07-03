package postmanClone.UI.Common.Tables;

import java.util.ArrayList;

import postmanClone.DA.Objects.Header;

@SuppressWarnings("serial")
public class TableModelHeader extends TableModel<Header> {

	private static final int COLUMN_NAME = 0;
	private static final int COLUMN_VALUE = 1;

	private static final String[] columnNames = { "Name", "Value" };

	@SuppressWarnings("rawtypes")
	private static final Class[] columnTypes = { String.class, String.class };


	public TableModelHeader() {
		super(columnNames, columnTypes, new ArrayList<Header>());
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Header header = this.getData().get(rowIndex);

		Object result = null;
		switch (columnIndex) {
		case COLUMN_NAME:
			result = header.getName();
			break;
		case COLUMN_VALUE:
			result = header.getValue();
			break;
		default:
			result = new String("");
		}

		return result;
	}

	@Override
	public Header getItemById(int id) {
		for (Header item : this.getData()) {
			if (item.getId() == id) {
				return item;
			}
		}
		return null;
	}

}
