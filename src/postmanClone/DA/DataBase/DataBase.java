package postmanClone.DA.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import postmanClone.DA.Storage;

public abstract class DataBase implements Storage {

	protected static Connection getConnection(DataBaseCredentials credentials) throws DataBaseException {

		Connection connection = null;

		try {
			Class.forName(credentials.getDriver());
			connection = DriverManager.getConnection(credentials.getURI(), credentials.getUser(),
					credentials.getPass());
			connection.setAutoCommit(false);
		} catch (SQLException | ClassNotFoundException e) {
			throw new DataBaseException("Error getting connection", e);
		}

		return connection;
	}
	
}
