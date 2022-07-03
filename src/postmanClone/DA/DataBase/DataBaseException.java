package postmanClone.DA.DataBase;

import postmanClone.DA.DataAccessLayerException;

@SuppressWarnings("serial")
public class DataBaseException extends DataAccessLayerException {

	public DataBaseException() {
	}

	public DataBaseException(String message) {
		super(message);
	}

	public DataBaseException(Throwable cause) {
		super(cause);
	}

	public DataBaseException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
