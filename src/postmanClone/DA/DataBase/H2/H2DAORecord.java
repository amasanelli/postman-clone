package postmanClone.DA.DataBase.H2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import postmanClone.DA.DataBase.DataBaseCredentials;
import postmanClone.DA.DataBase.DataBaseDAO;
import postmanClone.DA.DataBase.DataBaseException;
import postmanClone.DA.Objects.Field;
import postmanClone.DA.Objects.HTTPVerb;
import postmanClone.DA.Objects.Record;
import postmanClone.DA.Objects.URL;

public class H2DAORecord extends DataBaseDAO<Record> {

	private final DataBaseCredentials credentials;
	private static final String storageName = Record.getStorageName();

	public H2DAORecord(DataBaseCredentials credentials) {
		this.credentials = credentials;
	}

	@Override
	public int create(Record record) throws DataBaseException {
		// @formatter:off
		String sql = "INSERT INTO record (url, method) VALUES (" 
				+ "'" + record.getUrl() + "', " 
				+ "'" + record.getMethod().toString() + "'" 
				+ ")";
		// @formatter:on
		return DataBaseDAO.executeUpdate(this.credentials, sql);
	}

	@Override
	public void update(Record record) throws DataBaseException {
		// @formatter:off
		String sql = "UPDATE record SET " 
				+ "url = '" + record.getUrl() + "', " 
				+ "method = '" + record.getMethod().toString() + "' " 
				+ "WHERE id = " + record.getId();
		// @formatter:on
		DataBaseDAO.executeUpdate(this.credentials, sql);
	}

	private Record getRecord(Map<String, Object> map) {
		// @formatter:off
		return new Record(
				(int) map.get("ID"), 
				new URL((String) map.get("URL")),  
				HTTPVerb.valueOf((String) map.get("METHOD")));
		// @formatter:on
	}

	@Override
	public Record getOneByID(int id) throws DataBaseException {
		Record record = new Record();

		List<Map<String, Object>> results = DataBaseDAO.selectFromWhereId(H2DAORecord.storageName, id, this.credentials);

		if (results.size() > 0) {
			Map<String, Object> map = results.get(0);
			record = this.getRecord(map);
		}

		return record;
	}

	@Override
	public List<Record> getAll() throws DataBaseException {
		List<Record> list = new ArrayList<>();

		List<Map<String, Object>> results = DataBaseDAO.selectFrom(H2DAORecord.storageName, this.credentials);

		for (Map<String, Object> map : results) {
			list.add(this.getRecord(map));
		}

		return list;
	}

	@Override
	public List<Record> getAllBy(Field field, Object value) throws DataBaseException {
		List<Record> list = new ArrayList<>();

		List<Map<String, Object>> results = DataBaseDAO.selectFromWhere(H2DAORecord.storageName, field, value, credentials);

		for (Map<String, Object> map : results) {
			list.add(this.getRecord(map));
		}

		return list;
	}
	
	@Override
	public void delete(Record record) throws DataBaseException {
		DataBaseDAO.deleteFromWhereId(H2DAORecord.storageName, record.getId(), this.credentials);
	}

	@Override
	public void deleteAll() throws DataBaseException {
		DataBaseDAO.deleteFrom(H2DAORecord.storageName, this.credentials);
	}

	@Override
	public void deleteAllBy(Field field, Object value) throws DataBaseException {
		DataBaseDAO.deleteFromWhere(H2DAORecord.storageName, field, value, this.credentials);
	}
}
