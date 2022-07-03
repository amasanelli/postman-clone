package postmanClone.DA.Objects;

public class Bookmark extends Record {

	public static final Field NAME = new Field("NAME");
	private static final String STORAGE_NAME = "bookmark";
	
	private String name;

	public Bookmark(int id, URL url, HTTPVerb method, String name) {
		super(id, url, method);
		this.name = name;
	}

	public Bookmark(URL url, HTTPVerb method, String name) {
		super(url, method);
		this.name = name;
	}

	public Bookmark(Bookmark bookmark) {
		super(bookmark);
		this.name = bookmark.getName();
	}

	public Bookmark() {

	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static String getStorageName() {
		return STORAGE_NAME;
	}

}
