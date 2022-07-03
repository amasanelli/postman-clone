package postmanClone.DA.DataBase.H2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import postmanClone.DA.DataBase.DataBaseCredentials;
import postmanClone.DA.DataBase.DataBaseDAO;
import postmanClone.DA.DataBase.DataBaseException;
import postmanClone.DA.Objects.Body;
import postmanClone.DA.Objects.BodyType;
import postmanClone.DA.Objects.Field;

public class H2DAOBody extends DataBaseDAO<Body> {

	private final DataBaseCredentials credentials;
	private static final String storageName = Body.getStorageName();

	public H2DAOBody(DataBaseCredentials credentials) {
		this.credentials = credentials;
	}

	@Override
	public int create(Body body) throws DataBaseException {
		// @formatter:off
		String sql = "INSERT INTO body (id_record, val, type) VALUES (" 
				+ body.getIdRecord() + ", "
				+ "'" + body.getValue() + "', "
				+ "'" + body.getType().toString() + "'"
				+ ")";
		// @formatter:on
		return DataBaseDAO.executeUpdate(this.credentials, sql);
	}

	@Override
	public void update(Body body) throws DataBaseException {
		// @formatter:off
		String sql = "UPDATE body SET "
				+ "val = '" + body.getValue() + "', "
				+ "type = '" + body.getType().toString() + "'"
				+ "WHERE id = " + body.getId();
		// @formatter:on
		DataBaseDAO.executeUpdate(this.credentials, sql);
	}

	private Body getBody(Map<String, Object> map) {
		// @formatter:off
		return new Body(
				(int) map.get("ID"), 
				(int) map.get("ID_RECORD"), 
				(String) map.get("VAL"),
				BodyType.valueOf((String) map.get("TYPE")));
		// @formatter:on
	}

	@Override
	public Body getOneByID(int id) throws DataBaseException {
		Body body = new Body();

		List<Map<String, Object>> results = DataBaseDAO.selectFromWhereId(H2DAOBody.storageName, id, this.credentials);

		if (results.size() > 0) {
			Map<String, Object> map = results.get(0);
			body = this.getBody(map);
		}

		return body;
	}

	@Override
	public List<Body> getAll() throws DataBaseException {
		List<Body> list = new ArrayList<>();

		List<Map<String, Object>> results = DataBaseDAO.selectFrom(H2DAOBody.storageName, this.credentials);

		for (Map<String, Object> map : results) {
			list.add(this.getBody(map));
		}

		return list;
	}

	@Override
	public List<Body> getAllBy(Field field, Object value) throws DataBaseException {
		List<Body> list = new ArrayList<>();

		List<Map<String, Object>> results = DataBaseDAO.selectFromWhere(H2DAOBody.storageName, field, value, credentials);

		for (Map<String, Object> map : results) {
			list.add(this.getBody(map));
		}

		return list;
	}

	@Override
	public void delete(Body body) throws DataBaseException {
		DataBaseDAO.deleteFromWhereId(H2DAOBody.storageName, body.getId(), this.credentials);
	}

	@Override
	public void deleteAll() throws DataBaseException {
		DataBaseDAO.deleteFrom(H2DAOBody.storageName, this.credentials);
	}

	@Override
	public void deleteAllBy(Field field, Object value) throws DataBaseException {
		DataBaseDAO.deleteFromWhere(H2DAOBody.storageName, field, value, this.credentials);
	}
}
