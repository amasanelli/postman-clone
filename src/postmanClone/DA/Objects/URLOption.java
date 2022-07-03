package postmanClone.DA.Objects;

public class URLOption {

	public static final Field ID = new Field("ID");
	public static final Field ID_RECORD = new Field("ID_RECORD");
	public static final Field VALUE = new Field("VALUE");

	private Integer id;
	private Integer idRecord;
	private String value;

	public URLOption(int id, Record record, String value) {
		this.id = id;
		this.idRecord = record.getId();
		this.value = value;
	}

	public URLOption(int id, int idRecord, String value) {
		this.id = id;
		this.idRecord = idRecord;
		this.value = value;
	}

	public URLOption(Record record, String value) {
		this.idRecord = record.getId();
		this.value = value;
	}
	
	public URLOption(int idRecord, String value) {
		this.idRecord = idRecord;
		this.value = value;
	}
	
	public URLOption(String value) {
		this.value = value;
	}

	public URLOption(URLOption requestFeatures) {
		this.id = requestFeatures.getId();
		this.idRecord = requestFeatures.getId();
		this.value = requestFeatures.getValue();
	}
	
	public URLOption() {
		
	}

	public Integer getId() {
		return this.id;
	}

	public Integer getIdRecord() {
		return this.idRecord;
	}

	public String getValue() {
		return this.value;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIdRecord(Record record) {
		this.idRecord = record.getId();
	}

	public void setValue(String value) {
		this.value = value;
	}

}
