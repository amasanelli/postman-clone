package postmanClone.DA.DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import postmanClone.DA.DAO;
import postmanClone.DA.Objects.Field;

public abstract class DataBaseDAO<T> implements DAO<T> {

	protected static int executeUpdate(DataBaseCredentials credentials, String sql) throws DataBaseException {

		Connection connection = null;
		int newId = -1;

		try {
			connection = DataBase.getConnection(credentials);
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.executeUpdate();
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				newId = resultSet.getInt(1);
			}
			connection.commit();
		} catch (SQLException e) {
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

		return newId;
	}

	protected static List<Map<String, Object>> executeQuery(DataBaseCredentials credentials, String sql)
			throws DataBaseException {

		List<Map<String, Object>> list = new ArrayList<>();
		Connection connection = null;

		try {
			connection = DataBase.getConnection(credentials);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Map<String, Object> map = new HashMap<>();
				for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
					map.put(resultSet.getMetaData().getColumnName(i), resultSet.getObject(i));
				}
				list.add(map);
			}
		} catch (SQLException e) {
			throw new DataBaseException("Error executing SQL", e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new DataBaseException("Error closing connections", e);
				}
			}
		}

		return list;
	}

	protected static void deleteFromWhere(String tableName, Field field, Object value,
			DataBaseCredentials credentials) throws DataBaseException {
		String sql = null;
		if (value instanceof String) {
			sql = "DELETE FROM " + tableName + " WHERE " + field.getName() + " = '" + value + "'";
		} else {
			sql = "DELETE FROM " + tableName + " WHERE " + field.getName() + " = " + value.toString();
		}
		DataBaseDAO.executeUpdate(credentials, sql);
	}

	protected static void deleteFrom(String tableName, DataBaseCredentials credentials)
			throws DataBaseException {
		String sql = "DELETE FROM " + tableName;
		DataBaseDAO.executeUpdate(credentials, sql);
	}
	
	protected static void deleteFromWhereId(String tableName, int id,
			DataBaseCredentials credentials) throws DataBaseException {
		String sql = "DELETE FROM " + tableName + " WHERE id = " + id;
		DataBaseDAO.executeUpdate(credentials, sql);
	}

	protected static List<Map<String, Object>> selectFromWhere(String tableName, Field field, Object value,
			DataBaseCredentials credentials) throws DataBaseException {
		String sql = null;
		if (value instanceof String) {
			sql = "SELECT * FROM " + tableName + " WHERE " + field.getName() + " = '" + value + "'";
		} else {
			sql = "SELECT * FROM " + tableName + " WHERE " + field.getName() + " = " + value.toString();
		}
		return DataBaseDAO.executeQuery(credentials, sql);
	}

	protected static List<Map<String, Object>> selectFrom(String tableName, DataBaseCredentials credentials)
			throws DataBaseException {
		String sql = "SELECT * FROM " + tableName;
		return DataBaseDAO.executeQuery(credentials, sql);
	}
	
	protected static List<Map<String, Object>> selectFromWhereId(String tableName, int id, DataBaseCredentials credentials)
			throws DataBaseException {
		String sql = "SELECT * FROM " + tableName + " WHERE id = " + id;
		return DataBaseDAO.executeQuery(credentials, sql);
	}
}
