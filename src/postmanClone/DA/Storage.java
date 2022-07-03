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

	DAO<Record> getDAORecord();
	Container<Record> getContainerRecord();
	
	DAO<Bookmark> getDAOBookmark();
	Container<Bookmark> getContainerBookmark();
	
	DAO<History> getDAOHistory();
	Container<History> getContainerHistory();
	
	DAO<Parameter> getDAOParameter();
	Container<Parameter> getContainerParameter();

	DAO<Header> getDAOHeader();
	Container<Header> getContainerHeader();
	
	DAO<Body> getDAOBody();
	Container<Body> getContainerBody();
	
}
