package postmanClone.BL;

@SuppressWarnings("serial")
public class BusinessLogicLayerException extends Exception {

	public BusinessLogicLayerException() {
	}

	public BusinessLogicLayerException(String message) {
		super(message);
	}

	public BusinessLogicLayerException(Throwable cause) {
		super(cause);
	}

	public BusinessLogicLayerException(String message, Throwable cause) {
		super(message, cause);
	}
}
