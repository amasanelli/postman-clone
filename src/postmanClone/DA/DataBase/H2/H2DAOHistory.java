package postmanClone.DA.DataBase.H2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.Timestamp;

import postmanClone.DA.DataAccessLayerException;
import postmanClone.DA.DataBase.DataBaseCredentials;
import postmanClone.DA.DataBase.DataBaseDAO;
import postmanClone.DA.DataBase.DataBaseException;
import postmanClone.DA.Objects.Field;
import postmanClone.DA.Objects.HTTPVerb;
import postmanClone.DA.Objects.History;
import postmanClone.DA.Objects.URL;

public class H2DAOHistory extends DataBaseDAO<History> {

	private final DataBaseCredentials credentials;
	private static final String storageName = History.getStorageName();

	public H2DAOHistory(DataBaseCredentials credentials) {
		this.credentials = credentials;
	}

	@Override
	public int create(History history) throws DataBaseException {
		// @formatter:off
		String sql = "INSERT INTO history (id, timestamp) VALUES (" 
				+ history.getId() + ", " 
				+ "{ts '" + history.getTimestamp() + "'}" 
				+ ")";
		// @formatter:on
		return DataBaseDAO.executeUpdate(this.credentials, sql);
	}

	@Override
	public void update(History history) throws DataBaseException {
		// @formatter:off
		String sql = "UPDATE history SET " 
				+ "timestamp = " + "{ts '" + history.getTimestamp() + "'}" 
				+ "WHERE id = " + history.getId();
		// @formatter:on
		DataBaseDAO.executeUpdate(this.credentials, sql);
	}

	private History getHistory(Map<String, Object> map) {
		// @formatter:off
		return new History(
				(int) map.get("ID"), 
				new URL((String) map.get("URL")), 
				HTTPVerb.valueOf((String) map.get("METHOD")),
				(Timestamp) map.get("TIMESTAMP"));
		// @formatter:on
	}

	@Override
	public History getOneByID(int id) throws DataBaseException {
		History history = new History();

		String sql = "SELECT * FROM record JOIN history ON (record.id = history.id) WHERE id = " + id;
		List<Map<String, Object>> results = DataBaseDAO.executeQuery(this.credentials, sql);

		if (results.size() > 0) {
			Map<String, Object> map = results.get(0);
			history = this.getHistory(map);
		}

		return history;
	}

	@Override
	public List<History> getAll() throws DataBaseException {
		List<History> list = new ArrayList<>();

		String sql = "SELECT * FROM record JOIN history ON (record.id = history.id) ORDER BY timestamp DESC";
		List<Map<String, Object>> results = DataBaseDAO.executeQuery(this.credentials, sql);

		for (Map<String, Object> map : results) {
			list.add(this.getHistory(map));
		}

		return list;
	}

	@Override
	public List<History> getAllBy(Field field, Object value) throws DataAccessLayerException {
		List<History> list = new ArrayList<>();

		String sql = null;
		if (value instanceof String) {
			sql = "SELECT * FROM record JOIN history ON (record.id = history.id) WHERE " + field.getName() + " = '"
					+ value + "'";
		} else {
			sql = "SELECT * FROM record JOIN history ON (record.id = history.id) WHERE " + field.getName() + " = "
					+ value.toString();
		}
		List<Map<String, Object>> results = DataBaseDAO.executeQuery(this.credentials, sql);

		for (Map<String, Object> map : results) {
			list.add(this.getHistory(map));
		}

		return list;
	}

	@Override
	public void delete(History history) throws DataBaseException {
		DataBaseDAO.deleteFromWhereId(H2DAOHistory.storageName, history.getId(), this.credentials);
	}

	@Override
	public void deleteAll() throws DataBaseException {
		DataBaseDAO.deleteFrom(H2DAOHistory.storageName, this.credentials);
	}

	@Override
	public void deleteAllBy(Field field, Object value) throws DataBaseException {
		DataBaseDAO.deleteFromWhere(H2DAOHistory.storageName, field, value, this.credentials);
	}
}
