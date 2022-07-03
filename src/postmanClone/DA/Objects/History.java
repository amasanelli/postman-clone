package postmanClone.DA.Objects;

import java.sql.Timestamp;

public class History extends Record {
	
	public static final Field TIMESTAMP = new Field("TIMESTAMP");
	private static final String STORAGE_NAME = "history";
	
	private Timestamp timestamp;
	
	public History(int id, URL url, HTTPVerb method, Timestamp timestamp) {
		super(id, url, method);
		this.timestamp = timestamp;
	}

	public History(URL url, HTTPVerb method, Timestamp timestamp) {
		super(url, method);
		this.timestamp = timestamp;
	}
	
	public History(History history) {
		super(history);
		this.timestamp = history.getTimestamp();
	}

	public History() {

	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public static String getStorageName() {
		return STORAGE_NAME;
	}

}
