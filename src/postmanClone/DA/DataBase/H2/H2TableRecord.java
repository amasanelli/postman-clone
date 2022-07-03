package postmanClone.DA.DataBase.H2;

import postmanClone.DA.DataBase.DataBaseCredentials;
import postmanClone.DA.DataBase.DataBaseException;
import postmanClone.DA.DataBase.DataBaseTable;
import postmanClone.DA.Objects.Record;

public class H2TableRecord extends DataBaseTable<Record> {

	private final DataBaseCredentials credentials;

	public H2TableRecord(DataBaseCredentials credentials) {
		this.credentials = credentials;
	}

	@Override
	public void create() throws DataBaseException {
		// @formatter:off
		String sql = "CREATE TABLE IF NOT EXISTS record "
				+ "(id INTEGER AUTO_INCREMENT, "
				+ "url VARCHAR(MAX), "
				+ "method VARCHAR(100), "
				+ "PRIMARY KEY(id))";
		// @formatter:on
		DataBaseTable.execute(this.credentials, sql);
	}

	@Override
	public void drop() throws DataBaseException {
		DataBaseTable.dropTable(Record.getStorageName(), this.credentials);
	}
}
