package postmanClone.DA;

public interface Container<T> {
	
	/*
	 * Puede ser un archivo o una tabla
	 */

	void create() throws DataAccessLayerException;

	void drop() throws DataAccessLayerException;
	
}
