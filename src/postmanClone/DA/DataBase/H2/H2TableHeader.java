package postmanClone.DA.DataBase.H2;

import postmanClone.DA.DataBase.DataBaseCredentials;
import postmanClone.DA.DataBase.DataBaseException;
import postmanClone.DA.DataBase.DataBaseTable;
import postmanClone.DA.Objects.Header;

public class H2TableHeader extends DataBaseTable<Header> {

	private final DataBaseCredentials credentials;

	public H2TableHeader(DataBaseCredentials credentials) {
		this.credentials = credentials;
	}

	@Override
	public void create() throws DataBaseException {
		// @formatter:off
		String sql = "CREATE TABLE IF NOT EXISTS header "
				+ "(id INTEGER AUTO_INCREMENT, "
				+ "id_record INTEGER, "
				+ "val VARCHAR(MAX), "
				+ "name VARCHAR(100), "
				+ "PRIMARY KEY (id), "
				+ "FOREIGN KEY (id_record) REFERENCES record(id) ON DELETE CASCADE)";
		// @formatter:on
		DataBaseTable.execute(this.credentials, sql);
	}

	@Override
	public void drop() throws DataBaseException {
		DataBaseTable.dropTable(Header.getStorageName(), this.credentials);
	}
}
