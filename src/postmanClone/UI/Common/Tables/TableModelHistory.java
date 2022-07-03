package postmanClone.UI.Common.Tables;

import java.util.ArrayList;
import postmanClone.DA.Objects.History;

@SuppressWarnings("serial")
public class TableModelHistory extends TableModel<History> {

	private static final int COLUMN_URL = 0;

	private static final String[] columnNames = { "History" };

	@SuppressWarnings("rawtypes")
	private static final Class[] columnTypes = { String.class };

	public TableModelHistory() {
		super(columnNames, columnTypes,  new ArrayList<History>());
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		History history = this.getData().get(rowIndex);

		Object result = null;
		switch (columnIndex) {
		case COLUMN_URL:
			result = history.getUrl();
			break;
		default:
			result = new String("");
		}

		return result;
	}

	@Override
	public History getItemById(int id) {
		for (History item : this.getData()) {
			if (item.getId() == id) {
				return item;
			}
		}
		return null;
	}

}
