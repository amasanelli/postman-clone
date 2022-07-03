package postmanClone.DA;

import java.util.List;

import postmanClone.DA.Objects.Field;

public interface DAO<T> {
	
	/*
	 * Un registro de una tabla o una linea de un archivo
	 */

	int create(T object) throws DataAccessLayerException;

	void update(T object) throws DataAccessLayerException;

	void delete(T object) throws DataAccessLayerException;

	void deleteAll() throws DataAccessLayerException;
	
	void deleteAllBy(Field field, Object value) throws DataAccessLayerException;

	T getOneByID(int id) throws DataAccessLayerException;

	List<T> getAllBy(Field field, Object value) throws DataAccessLayerException;

	List<T> getAll() throws DataAccessLayerException;

}
