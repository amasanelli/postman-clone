package postmanClone.DA;

public interface Container<T> {
	
	/*
	 * Puede ser un archivo o una tabla
	 */

	public void create() throws DataAccessLayerException;

	public void drop() throws DataAccessLayerException;
	
}
