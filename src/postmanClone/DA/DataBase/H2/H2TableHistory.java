package postmanClone.DA.DataBase.H2;

import postmanClone.DA.DataBase.DataBaseCredentials;
import postmanClone.DA.DataBase.DataBaseException;
import postmanClone.DA.DataBase.DataBaseTable;
import postmanClone.DA.Objects.History;

public class H2TableHistory extends DataBaseTable<History> {

	private final DataBaseCredentials credentials;

	public H2TableHistory(DataBaseCredentials credentials) {
		this.credentials = credentials;
	}

	@Override
	public void create() throws DataBaseException {
		// @formatter:off
		String sql = "CREATE TABLE IF NOT EXISTS history "
				+ "(id INTEGER, "
				+ "timestamp TIMESTAMP, "
				+ "PRIMARY KEY(id), "
				+ "FOREIGN KEY (id) REFERENCES record(id) ON DELETE CASCADE)";
		// @formatter:on
		DataBaseTable.execute(this.credentials, sql);
	}

	@Override
	public void drop() throws DataBaseException {
		DataBaseTable.dropTable(History.getStorageName(), this.credentials);
	}
}
