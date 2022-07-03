package postmanClone.DA;

import java.util.List;

import postmanClone.DA.Objects.Field;

public interface DAO<T> {
	
	/*
	 * Un registro de una tabla o una linea de un archivo
	 */

	public int create(T object) throws DataAccessLayerException;

	public void update(T object) throws DataAccessLayerException;

	public void delete(T object) throws DataAccessLayerException;

	public void deleteAll() throws DataAccessLayerException;
	
	public void deleteAllBy(Field field, Object value) throws DataAccessLayerException;

	public T getOneByID(int id) throws DataAccessLayerException;

	public List<T> getAllBy(Field field, Object value) throws DataAccessLayerException;

	public List<T> getAll() throws DataAccessLayerException;

}
