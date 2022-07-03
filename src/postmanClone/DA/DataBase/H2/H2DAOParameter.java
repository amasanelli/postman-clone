package postmanClone.DA.DataBase.H2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import postmanClone.DA.DataBase.DataBaseCredentials;
import postmanClone.DA.DataBase.DataBaseDAO;
import postmanClone.DA.DataBase.DataBaseException;
import postmanClone.DA.Objects.Field;
import postmanClone.DA.Objects.Parameter;

public class H2DAOParameter extends DataBaseDAO<Parameter> {

	private final DataBaseCredentials credentials;
	private static final String storageName = Parameter.getStorageName();

	public H2DAOParameter(DataBaseCredentials credentials) {
		this.credentials = credentials;
	}

	@Override
	public int create(Parameter parameter) throws DataBaseException {
		// @formatter:off
		String sql = "INSERT INTO parameter (id_record, val, name) VALUES (" 
				+ parameter.getIdRecord() + ", " 
				+ "'" + parameter.getValue() + "', " 
				+ "'" + parameter.getName() + "'" 
				+ ")";
		// @formatter:on
		return DataBaseDAO.executeUpdate(this.credentials, sql);
	}

	@Override
	public void update(Parameter parameter) throws DataBaseException {
		// @formatter:off
		String sql = "UPDATE parameter SET " 
				+ "val = '" + parameter.getValue() + "', " 
				+ "name = '" + parameter.getName() + "'" 
				+ "WHERE id = " + parameter.getId();
		// @formatter:on
		DataBaseDAO.executeUpdate(this.credentials, sql);
	}

	private Parameter getParameter(Map<String, Object> map) {
		// @formatter:off
		return new Parameter(
				(int) map.get("ID"), 
				(int) map.get("ID_RECORD"), 
				(String) map.get("VAL"),
				(String) map.get("NAME"));
		// @formatter:on
	}

	@Override
	public Parameter getOneByID(int id) throws DataBaseException {
		Parameter parameter = new Parameter();

		List<Map<String, Object>> results = DataBaseDAO.selectFromWhereId(H2DAOParameter.storageName, id, this.credentials);

		if (results.size() > 0) {
			Map<String, Object> map = results.get(0);
			parameter = this.getParameter(map);
		}

		return parameter;
	}

	@Override
	public List<Parameter> getAll() throws DataBaseException {
		List<Parameter> list = new ArrayList<>();

		List<Map<String, Object>> results = DataBaseDAO.selectFrom(H2DAOParameter.storageName, this.credentials);

		for (Map<String, Object> map : results) {
			list.add(this.getParameter(map));
		}

		return list;
	}

	@Override
	public List<Parameter> getAllBy(Field field, Object value) throws DataBaseException {
		List<Parameter> list = new ArrayList<>();

		List<Map<String, Object>> results = DataBaseDAO.selectFromWhere(H2DAOParameter.storageName, field, value, credentials);

		for (Map<String, Object> map : results) {
			list.add(this.getParameter(map));
		}

		return list;
	}

	@Override
	public void delete(Parameter parameter) throws DataBaseException {
		DataBaseDAO.deleteFromWhereId(H2DAOParameter.storageName, parameter.getId(), this.credentials);
	}

	@Override
	public void deleteAll() throws DataBaseException {
		DataBaseDAO.deleteFrom(H2DAOParameter.storageName, this.credentials);
	}

	@Override
	public void deleteAllBy(Field field, Object value) throws DataBaseException {
		DataBaseDAO.deleteFromWhere(H2DAOParameter.storageName, field, value, this.credentials);
	}
}
