package postmanClone.DA.DataBase.H2;

import postmanClone.DA.DataBase.DataBaseCredentials;
import postmanClone.DA.DataBase.DataBaseException;
import postmanClone.DA.DataBase.DataBaseTable;
import postmanClone.DA.Objects.Bookmark;

public class H2TableBookmark extends DataBaseTable<Bookmark> {

	private final DataBaseCredentials credentials;

	public H2TableBookmark(DataBaseCredentials credentials) {
		this.credentials = credentials;
	}

	@Override
	public void create() throws DataBaseException {
		// @formatter:off
		String sql = "CREATE TABLE IF NOT EXISTS bookmark "
				+ "(id INTEGER, "
				+ "name VARCHAR(100), "
				+ "PRIMARY KEY(id), "
				+ "FOREIGN KEY (id) REFERENCES record(id) ON DELETE CASCADE)";
		// @formatter:on
		DataBaseTable.execute(this.credentials, sql);
	}

	@Override
	public void drop() throws DataBaseException {
		DataBaseTable.dropTable(Bookmark.getStorageName(), this.credentials);
	}
}
