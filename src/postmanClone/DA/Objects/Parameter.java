package postmanClone.DA.Objects;

public class Parameter extends URLOption {

	public static final Field NAME = new Field("NAME");
	private static final String STORAGE_NAME = "parameter";
	
	private String name;

	public Parameter(int id, Record record, String value, String name) {
		super(id, record, value);
		this.name = name;
	}

	public Parameter(int id, int idRecord, String value, String name) {
		super(id, idRecord, value);
		this.name = name;
	}

	public Parameter(Record record, String value, String name) {
		super(record, value);
		this.name = name;
	}
	
	public Parameter(int idRecord, String value, String name) {
		super(idRecord, value);
		this.name = name;
	}

	public Parameter(String value, String name) {
		super(value);
		this.name = name;
	}
	
	public Parameter(Parameter parameter) {
		super(parameter);
		this.name = parameter.getName();
	}
	
	public Parameter() {

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
