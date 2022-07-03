package postmanClone.BL;

import java.sql.Timestamp;
import java.util.List;

import postmanClone.DA.DataAccessLayerException;
import postmanClone.DA.Storage;
import postmanClone.DA.DataBase.H2.H2;
import postmanClone.DA.Objects.Body;
import postmanClone.DA.Objects.BodyType;
import postmanClone.DA.Objects.Bookmark;
import postmanClone.DA.Objects.HTTPVerb;
import postmanClone.DA.Objects.Header;
import postmanClone.DA.Objects.History;
import postmanClone.DA.Objects.Parameter;
import postmanClone.DA.Objects.Record;
import postmanClone.DA.Objects.URL;

public class StorageServices {

	private static final Storage storage  = new H2();

	public static void createContainers() throws BusinessLogicLayerException {
		try {
			storage.getContainerRecord().create();
			storage.getContainerBookmark().create();
			storage.getContainerHistory().create();
			storage.getContainerParameter().create();
			storage.getContainerHeader().create();
			storage.getContainerBody().create();
		} catch (DataAccessLayerException e) {
			throw new BusinessLogicLayerException(e);
		}
	}

	public static void addTestRecords() throws BusinessLogicLayerException {

		Bookmark bookmark = null;

		try {
			bookmark = new Bookmark(new URL("https://httpbin.org/get?q=test"), HTTPVerb.GET, "get");
			bookmark.setId(storage.getDAORecord().create(bookmark));
			storage.getDAOBookmark().create(bookmark);

			addURLOptions(bookmark);
			
			bookmark = new Bookmark(new URL("https://httpbin.org/xml"), HTTPVerb.GET, "xml");
			bookmark.setId(storage.getDAORecord().create(bookmark));
			storage.getDAOBookmark().create(bookmark);

			bookmark = new Bookmark(new URL("https://i.stack.imgur.com/a0aTc.jpg?s=256&g=1"), HTTPVerb.GET, "image");
			bookmark.setId(storage.getDAORecord().create(bookmark));
			storage.getDAOBookmark().create(bookmark);

			bookmark = new Bookmark(new URL("https://httpbin.org/post?q=test"), HTTPVerb.POST, "post");
			bookmark.setId(storage.getDAORecord().create(bookmark));
			storage.getDAOBookmark().create(bookmark);

			addURLOptions(bookmark);

			bookmark = new Bookmark(new URL("https://httpbin.org/put?q=test"), HTTPVerb.PUT, "put");
			bookmark.setId(storage.getDAORecord().create(bookmark));
			storage.getDAOBookmark().create(bookmark);

			addURLOptions(bookmark);

			bookmark = new Bookmark(new URL("https://httpbin.org/delete?q=test"), HTTPVerb.DELETE, "delete");
			bookmark.setId(storage.getDAORecord().create(bookmark));
			storage.getDAOBookmark().create(bookmark);

			addURLOptions(bookmark);
		} catch (DataAccessLayerException e) {
			throw new BusinessLogicLayerException(e);
		}

	}

	private static void addURLOptions(Bookmark bookmark) throws BusinessLogicLayerException {

		Parameter parameter = null;
		Body body = null;
		Header header = null;

		try {
			body = new Body(bookmark, "{\"body\": \"test\"}", BodyType.JSON);
			storage.getDAOBody().create(body);

			parameter = new Parameter(bookmark, "test", "q");
			storage.getDAOParameter().create(parameter);

			header = new Header(bookmark, "test", "cookie");
			storage.getDAOHeader().create(header);

		} catch (DataAccessLayerException e) {
			throw new BusinessLogicLayerException(e);
		}
	}

	private static List<Parameter> getParameters(Record record) throws BusinessLogicLayerException {
		try {
			return storage.getDAOParameter().getAllBy(Parameter.ID_RECORD, record.getId());
		} catch (DataAccessLayerException e) {
			throw new BusinessLogicLayerException(e);
		}
	}

	private static Body getBody(Record record) throws BusinessLogicLayerException {
		try {
			return storage.getDAOBody().getOneByID(record.getId());
		} catch (DataAccessLayerException e) {
			throw new BusinessLogicLayerException(e);
		}
	}

	private static List<Header> getHeaders(Record record) throws BusinessLogicLayerException {
		try {
			return storage.getDAOHeader().getAllBy(Header.ID_RECORD, record.getId());
		} catch (DataAccessLayerException e) {
			throw new BusinessLogicLayerException(e);
		}
	}

	public static List<Bookmark> getBookmarks() throws BusinessLogicLayerException {
		try {
			List<Bookmark> list = storage.getDAOBookmark().getAll();
			for (Bookmark item : list) {
				item.setHeaders(getHeaders(item));
				item.setParameters(getParameters(item));
				item.setBody(getBody(item));
			}
			return list;
		} catch (DataAccessLayerException e) {
			throw new BusinessLogicLayerException(e);
		}
	}

	public static List<History> getHistory() throws BusinessLogicLayerException {
		try {
			List<History> list = storage.getDAOHistory().getAll();
			for (History item : list) {
				item.setHeaders(getHeaders(item));
				item.setParameters(getParameters(item));
				item.setBody(getBody(item));
			}
			return list;
		} catch (DataAccessLayerException e) {
			throw new BusinessLogicLayerException(e);
		}
	}

	public static void saveBookmark(String name, Record record) throws BusinessLogicLayerException {
		try {

			Bookmark bookmark = null;
			if (record instanceof Bookmark) {
				bookmark = (Bookmark) record;
			} else {
				bookmark = new Bookmark(record.getUrl(), record.getMethod(), name);
			}

			if (name.length() > 0) {
				bookmark.setName(name);
			}

			Body body = record.getBody();

			if (bookmark.getId() != null) {
				storage.getDAORecord().update(bookmark);
				storage.getDAOBookmark().update(bookmark);

				storage.getDAOParameter().deleteAllBy(Parameter.ID_RECORD, bookmark.getId());
				storage.getDAOHeader().deleteAllBy(Parameter.ID_RECORD, bookmark.getId());

				storage.getDAOBody().update(body);
			} else {
				bookmark.setId(storage.getDAORecord().create(bookmark));
				storage.getDAOBookmark().create(bookmark);

				body.setIdRecord(bookmark);
				storage.getDAOBody().create(body);
			}

			for (Parameter parameter : record.getParameters()) {
				parameter.setIdRecord(bookmark);
				storage.getDAOParameter().create(parameter);
			}

			for (Header header : record.getHeaders()) {
				header.setIdRecord(bookmark);
				storage.getDAOHeader().create(header);
			}

		} catch (DataAccessLayerException e) {
			throw new BusinessLogicLayerException(e);
		}
	}

	public static void saveHistory(Record record) throws BusinessLogicLayerException {
		try {
			History history = new History(record.getUrl(), record.getMethod(),
					new Timestamp(System.currentTimeMillis()));

			history.setId(storage.getDAORecord().create(history));
			storage.getDAOHistory().create(history);

			Body body = record.getBody();

			body.setIdRecord(history);
			storage.getDAOBody().create(body);

			for (Parameter parameter : record.getParameters()) {
				parameter.setIdRecord(history);
				storage.getDAOParameter().create(parameter);
			}

			for (Header header : record.getHeaders()) {
				header.setIdRecord(history);
				storage.getDAOHeader().create(header);
			}
		} catch (DataAccessLayerException e) {
			throw new BusinessLogicLayerException(e);
		}

	}

	public static void deleteBookmark(Bookmark bookmark) throws BusinessLogicLayerException {
		try {
			storage.getDAORecord().delete(bookmark);
		} catch (DataAccessLayerException e) {
			throw new BusinessLogicLayerException(e);
		}
	}

	public static void cleanBookmarks() throws BusinessLogicLayerException {
		try {
			storage.getDAOBookmark().deleteAll();
		} catch (DataAccessLayerException e) {
			throw new BusinessLogicLayerException(e);
		}
	}

	public static void cleanHistory() throws BusinessLogicLayerException {
		try {
			storage.getDAOHistory().deleteAll();
		} catch (DataAccessLayerException e) {
			throw new BusinessLogicLayerException(e);
		}
	}
}
