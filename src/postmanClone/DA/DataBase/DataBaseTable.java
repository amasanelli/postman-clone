package postmanClone.DA.DataBase;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import postmanClone.DA.Container;

public abstract class DataBaseTable<T> implements Container<T> {

	protected static void execute(DataBaseCredentials credentials, String sql) throws DataBaseException {

		Connection connection = null;

		try {
			connection = DataBase.getConnection(credentials);
			Statement statement = connection.createStatement();
			statement.execute(sql);
		} catch (DataBaseException | SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
					throw new DataBaseException("Error executing SQL", e);
				} catch (SQLException e1) {
					throw new DataBaseException("Error rolling back changes", e1);
				}
			}
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new DataBaseException("Error closing connection", e);
				}
			}
		}
	}
	
	protected static void dropTable(String tableName, DataBaseCredentials credentials) throws DataBaseException {
		String sql = "DROP TABLE " + tableName;
		DataBaseTable.execute(credentials, sql);
	}
}
