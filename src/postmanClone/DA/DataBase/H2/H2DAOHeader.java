package postmanClone.DA.DataBase.H2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import postmanClone.DA.DataBase.DataBaseCredentials;
import postmanClone.DA.DataBase.DataBaseDAO;
import postmanClone.DA.DataBase.DataBaseException;
import postmanClone.DA.Objects.Field;
import postmanClone.DA.Objects.Header;

public class H2DAOHeader extends DataBaseDAO<Header> {

	private final DataBaseCredentials credentials;
	private static final String storageName = Header.getStorageName();

	public H2DAOHeader(DataBaseCredentials credentials) {
		this.credentials = credentials;
	}

	@Override
	public int create(Header header) throws DataBaseException {
		// @formatter:off
		String sql = "INSERT INTO header (id_record, val, name) VALUES (" 
				+ header.getIdRecord() + ", " 
				+ "'" + header.getValue() + "', " 
				+ "'" + header.getName() + "'" 
				+ ")";
		// @formatter:on
		return DataBaseDAO.executeUpdate(this.credentials, sql);
	}

	@Override
	public void update(Header header) throws DataBaseException {
		// @formatter:off
		String sql = "UPDATE header SET " 
				+ "val = '" + header.getValue() + "', " 
				+ "name = '" + header.getName() + "'"
				+ "WHERE id = " + header.getId();
		// @formatter:on
		DataBaseDAO.executeUpdate(this.credentials, sql);
	}

	private Header getHeader(Map<String, Object> map) {
		// @formatter:off
		return new Header(
				(int) map.get("ID"), 
				(int) map.get("ID_RECORD"), 
				(String) map.get("VAL"),
				(String) map.get("NAME"));
		// @formatter:on
	}

	@Override
	public Header getOneByID(int id) throws DataBaseException {
		Header header = new Header();

		List<Map<String, Object>> results = DataBaseDAO.selectFromWhereId(H2DAOHeader.storageName, id, this.credentials);

		if (results.size() > 0) {
			Map<String, Object> map = results.get(0);
			header = this.getHeader(map);
		}

		return header;
	}

	@Override
	public List<Header> getAll() throws DataBaseException {
		List<Header> list = new ArrayList<>();

		List<Map<String, Object>> results = DataBaseDAO.selectFrom(H2DAOHeader.storageName, this.credentials);

		for (Map<String, Object> map : results) {
			list.add(this.getHeader(map));
		}

		return list;
	}

	@Override
	public List<Header> getAllBy(Field field, Object value) throws DataBaseException {
		List<Header> list = new ArrayList<>();

		List<Map<String, Object>> results = DataBaseDAO.selectFromWhere(H2DAOHeader.storageName, field, value, credentials);

		for (Map<String, Object> map : results) {
			list.add(this.getHeader(map));
		}

		return list;
	}

	@Override
	public void delete(Header header) throws DataBaseException {
		DataBaseDAO.deleteFromWhereId(H2DAOHeader.storageName, header.getId(), this.credentials);
	}

	@Override
	public void deleteAll() throws DataBaseException {
		DataBaseDAO.deleteFrom(H2DAOHeader.storageName, this.credentials);
	}

	@Override
	public void deleteAllBy(Field field, Object value) throws DataBaseException {
		DataBaseDAO.deleteFromWhere(H2DAOHeader.storageName, field, value, this.credentials);
	}
}
