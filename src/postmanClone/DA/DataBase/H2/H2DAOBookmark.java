package postmanClone.DA.DataBase.H2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import postmanClone.DA.DataAccessLayerException;
import postmanClone.DA.DataBase.DataBaseCredentials;
import postmanClone.DA.DataBase.DataBaseDAO;
import postmanClone.DA.DataBase.DataBaseException;
import postmanClone.DA.Objects.Bookmark;
import postmanClone.DA.Objects.Field;
import postmanClone.DA.Objects.HTTPVerb;
import postmanClone.DA.Objects.URL;

public class H2DAOBookmark extends DataBaseDAO<Bookmark> {

	private final DataBaseCredentials credentials;
	private static final String storageName = Bookmark.getStorageName();

	public H2DAOBookmark(DataBaseCredentials credentials) {
		this.credentials = credentials;
	}

	@Override
	public int create(Bookmark bookmark) throws DataBaseException {
		// @formatter:off
		String sql = "INSERT INTO bookmark (id, name) VALUES (" 
				+ bookmark.getId() + ", " 
				+ "'" + bookmark.getName() + "'" 
				+ ")";
		// @formatter:on
		return DataBaseDAO.executeUpdate(this.credentials, sql);
	}

	@Override
	public void update(Bookmark bookmark) throws DataBaseException {
		// @formatter:off
		String sql = "UPDATE bookmark SET " 
				+ "name = '" + bookmark.getName() + "' " 
				+ "WHERE id = " + bookmark.getId();
		// @formatter:on
		DataBaseDAO.executeUpdate(this.credentials, sql);
	}

	private Bookmark getBookmark(Map<String, Object> map) {
		// @formatter:off
		return new Bookmark(
				(int) map.get("ID"), 
				new URL((String) map.get("URL")), 
				HTTPVerb.valueOf((String) map.get("METHOD")),
				(String) map.get("NAME"));
		// @formatter:on
	}

	@Override
	public Bookmark getOneByID(int id) throws DataBaseException {
		Bookmark bookmark = new Bookmark();

		String sql = "SELECT * FROM record JOIN bookmark ON (record.id = bookmark.id) WHERE id = " + id;
		List<Map<String, Object>> results = DataBaseDAO.executeQuery(this.credentials, sql);

		if (results.size() > 0) {
			Map<String, Object> map = results.get(0);
			bookmark = this.getBookmark(map);
		}

		return bookmark;
	}

	@Override
	public List<Bookmark> getAll() throws DataBaseException {
		List<Bookmark> list = new ArrayList<>();

		String sql = "SELECT * FROM record JOIN bookmark ON (record.id = bookmark.id)";
		List<Map<String, Object>> results = DataBaseDAO.executeQuery(this.credentials, sql);

		for (Map<String, Object> map : results) {
			list.add(this.getBookmark(map));
		}

		return list;
	}

	@Override
	public List<Bookmark> getAllBy(Field field, Object value) throws DataAccessLayerException {
		List<Bookmark> list = new ArrayList<>();

		String sql = null;
		if (value instanceof String) {
			sql = "SELECT * FROM record JOIN bookmark ON (record.id = bookmark.id) WHERE " + field.getName() + " = '"
					+ value + "'";
		} else {
			sql = "SELECT * FROM record JOIN bookmark ON (record.id = bookmark.id) WHERE " + field.getName() + " = "
					+ value.toString();
		}
		List<Map<String, Object>> results = DataBaseDAO.executeQuery(this.credentials, sql);

		for (Map<String, Object> map : results) {
			list.add(this.getBookmark(map));
		}

		return list;
	}

	@Override
	public void delete(Bookmark bookmark) throws DataBaseException {
		DataBaseDAO.deleteFromWhereId(H2DAOBookmark.storageName, bookmark.getId(), this.credentials);
	}

	@Override
	public void deleteAll() throws DataBaseException {
		DataBaseDAO.deleteFrom(H2DAOBookmark.storageName, this.credentials);
	}

	@Override
	public void deleteAllBy(Field field, Object value) throws DataBaseException {
		DataBaseDAO.deleteFromWhere(H2DAOBookmark.storageName, field, value, this.credentials);
	}
}
