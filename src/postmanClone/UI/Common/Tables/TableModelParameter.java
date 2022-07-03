package postmanClone.UI.Common.Tables;

import java.util.ArrayList;
import postmanClone.DA.Objects.Parameter;

@SuppressWarnings("serial")
public class TableModelParameter extends TableModel<Parameter> {

	private static final int COLUMN_NAME = 0;
	private static final int COLUMN_VALUE = 1;

	private static final String[] columnNames = { "Name", "Value" };

	@SuppressWarnings("rawtypes")
	private static final Class[] columnTypes = { String.class, String.class };

	public TableModelParameter() {
		super(columnNames, columnTypes, new ArrayList<Parameter>());
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Parameter parameter = this.getData().get(rowIndex);

		Object result = null;
		switch (columnIndex) {
		case COLUMN_NAME:
			result = parameter.getName();
			break;
		case COLUMN_VALUE:
			result = parameter.getValue();
			break;
		default:
			result = new String("");
		}

		return result;
	}

	@Override
	public Parameter getItemById(int id) {
		for (Parameter item : this.getData()) {
			if (item.getId() == id) {
				return item;
			}
		}
		return null;
	}

}
