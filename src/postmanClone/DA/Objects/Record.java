package postmanClone.DA.Objects;

import java.util.ArrayList;
import java.util.List;

public class Record {

	public static final Field ID = new Field("ID");
	public static final Field URL = new Field("URL");
	public static final Field METHOD = new Field("METHOD");
	private static final String STORAGE_NAME = "record";
	
	private Integer id;
	private URL url = new URL();
	private HTTPVerb method = HTTPVerb.GET;

	private List<Parameter> parameters = new ArrayList<Parameter>();
	private List<Header> headers = new ArrayList<Header>();
	private Body body = new Body();
	private Response response;

	public Record(int id, URL url, HTTPVerb method) {
		this.id = id;
		this.url = url;
		this.method = method;
	}

	public Record(URL url, HTTPVerb method) {
		this.url = url;
		this.method = method;
	}
	
	public Record(Record record) {
		this.id = record.getId();
		this.url = new URL(record.getUrl());
		this.method = record.getMethod();
		this.parameters = new ArrayList<Parameter>();
		for(Parameter parameter: record.getParameters()) {
			this.parameters.add(new Parameter(parameter));
		}
		this.headers = new ArrayList<Header>();
		for(Header header: record.getHeaders()) {
			this.headers.add(new Header(header));
		}
		this.body = new Body(record.getBody());
		this.response = null;
	}

	public Record() {
		
	}

	public Integer getId() {
		return this.id;
	}

	public URL getUrl() {
		return this.url;
	}

	public List<Parameter> getParameters() {
		return this.parameters;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}

	public HTTPVerb getMethod() {
		return method;
	}

	public void setMethod(HTTPVerb method) {
		this.method = method;
	}

	public List<Header> getHeaders() {
		return headers;
	}

	public void setHeaders(List<Header> headers) {
		this.headers = headers;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public static String getStorageName() {
		return STORAGE_NAME;
	}

}
