package postmanClone.DA.DataBase.H2;

import postmanClone.DA.Container;
import postmanClone.DA.DAO;
import postmanClone.DA.DataBase.DataBase;
import postmanClone.DA.DataBase.DataBaseCredentials;
import postmanClone.DA.Objects.Body;
import postmanClone.DA.Objects.Bookmark;
import postmanClone.DA.Objects.Header;
import postmanClone.DA.Objects.History;
import postmanClone.DA.Objects.Parameter;
import postmanClone.DA.Objects.Record;

public class H2 extends DataBase {

	private static final DataBaseCredentials credentials = new H2Credentials();

	@Override
	public Container<Body> getContainerBody() {
		return new H2TableBody(H2.credentials);
	}

	@Override
	public DAO<Body> getDAOBody() {
		return new H2DAOBody(H2.credentials);
	}

	@Override
	public Container<Bookmark> getContainerBookmark() {
		return new H2TableBookmark(H2.credentials);
	}

	@Override
	public DAO<Bookmark> getDAOBookmark() {
		return new H2DAOBookmark(H2.credentials);
	}

	@Override
	public Container<Header> getContainerHeader() {
		return new H2TableHeader(H2.credentials);
	}

	@Override
	public DAO<Header> getDAOHeader() {
		return new H2DAOHeader(H2.credentials);
	}

	@Override
	public Container<History> getContainerHistory() {
		return new H2TableHistory(H2.credentials);
	}

	@Override
	public DAO<History> getDAOHistory() {
		return new H2DAOHistory(H2.credentials);
	}

	@Override
	public Container<Parameter> getContainerParameter() {
		return new H2TableParameter(H2.credentials);
	}

	@Override
	public DAO<Parameter> getDAOParameter() {
		return new H2DAOParameter(H2.credentials);
	}

	@Override
	public Container<Record> getContainerRecord() {
		return new H2TableRecord(H2.credentials);
	}

	@Override
	public DAO<Record> getDAORecord() {
		return new H2DAORecord(H2.credentials);
	}

}
