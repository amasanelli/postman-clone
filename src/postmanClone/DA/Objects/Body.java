package postmanClone.DA.Objects;

public class Body extends URLOption {

	public static final Field TYPE = new Field("TYPE");
	private static final String STORAGE_NAME = "body";

	private BodyType type = BodyType.RAW;

	public Body(int id, Record record, String value, BodyType type) {
		super(id, record, value);
		this.type = type;
	}

	public Body(int id, int idRecord, String value, BodyType type) {
		super(id, idRecord, value);
		this.type = type;
	}

	public Body(Record record, String value, BodyType type) {
		super(record, value);
		this.type = type;
	}

	public Body(int idRecord, String value, BodyType type) {
		super(idRecord, value);
		this.type = type;
	}

	public Body(String value, BodyType type) {
		super(value);
		this.type = type;
	}

	public Body(Body body) {
		super(body);
		this.type = body.getType();
	}

	public Body() {

	}

	public BodyType getType() {
		return type;
	}

	public void setType(BodyType type) {
		this.type = type;
	}

	public static String getStorageName() {
		return STORAGE_NAME;
	}

}
