package postmanClone.DA.Objects;

public class Header extends URLOption {

	public static final Field NAME = new Field("NAME");
	private static final String STORAGE_NAME = "header";
	
	private String name;

	public Header(int id, Record record, String value, String name) {
		super(id, record, value);
		this.name = name;
	}

	public Header(int id, int idRecord, String value, String name) {
		super(id, idRecord, value);
		this.name = name;
	}

	public Header(Record record, String value, String name) {
		super(record, value);
		this.name = name;
	}

	public Header(int idRecord, String value, String name) {
		super(idRecord, value);
		this.name = name;
	}

	public Header(String value, String name) {
		super(value);
		this.name = name;
	}
	
	public Header(Header header) {
		super(header);
		this.name = header.getName();
	}
	
	public Header() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static String getStorageName() {
		return STORAGE_NAME;
	}

}
