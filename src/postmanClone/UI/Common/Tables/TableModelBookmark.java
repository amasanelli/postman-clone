package postmanClone.UI.Common.Tables;

import java.util.ArrayList;
import postmanClone.DA.Objects.Bookmark;

@SuppressWarnings("serial")
public class TableModelBookmark extends TableModel<Bookmark> {

	private static final int COLUMN_NAME = 0;

	private static final String[] columnNames = { "Bookmarks" };

	@SuppressWarnings("rawtypes")
	private static final Class[] columnTypes = { String.class };

	public TableModelBookmark() {
		super(columnNames, columnTypes, new ArrayList<Bookmark>());
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Bookmark bookmark = this.getData().get(rowIndex);

		Object result = null;
		switch (columnIndex) {
		case COLUMN_NAME:
			result = bookmark.getName();
			break;
		default:
			result = new String("");
		}

		return result;
	}

	@Override
	public Bookmark getItemById(int id) {
		for (Bookmark item : this.getData()) {
			if (item.getId() == id) {
				return item;
			}
		}
		return null;

	}

}
