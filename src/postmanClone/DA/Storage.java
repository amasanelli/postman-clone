package postmanClone.DA;

import postmanClone.DA.Objects.Body;
import postmanClone.DA.Objects.Bookmark;
import postmanClone.DA.Objects.Header;
import postmanClone.DA.Objects.History;
import postmanClone.DA.Objects.Parameter;
import postmanClone.DA.Objects.Record;

public interface Storage {
	
	/*
	 * Puede ser una carpeta o una base de datos
	 */

	public DAO<Record> getDAORecord();
	public Container<Record> getContainerRecord();
	
	public DAO<Bookmark> getDAOBookmark();
	public Container<Bookmark> getContainerBookmark();
	
	public DAO<History> getDAOHistory();
	public Container<History> getContainerHistory();
	
	public DAO<Parameter> getDAOParameter();
	public Container<Parameter> getContainerParameter();

	public DAO<Header> getDAOHeader();
	public Container<Header> getContainerHeader();
	
	public DAO<Body> getDAOBody();
	public Container<Body> getContainerBody();
	
}
