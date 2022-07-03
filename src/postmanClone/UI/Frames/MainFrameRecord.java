package postmanClone.UI.Frames;

import java.util.ArrayList;
import java.util.List;

import postmanClone.DA.Objects.BodyType;
import postmanClone.DA.Objects.Bookmark;
import postmanClone.DA.Objects.HTTPVerb;
import postmanClone.DA.Objects.Header;
import postmanClone.DA.Objects.Parameter;
import postmanClone.DA.Objects.Record;
import postmanClone.DA.Objects.Response;
import postmanClone.DA.Objects.URL;

public class MainFrameRecord {

	private static Record record = new Record();
	private static final List<MainFrameRecordListener> listeners = new ArrayList<>();

	public static void addListener(MainFrameRecordListener listener) {
		MainFrameRecord.listeners.add(listener);
	}

	public static void recordUpdated(Record record) {
		for (MainFrameRecordListener listener : MainFrameRecord.listeners)
			listener.recordUpdate(MainFrameRecord.record);
	}
	
	public static void headersUpdated(Record record) {
		for (MainFrameRecordListener listener : MainFrameRecord.listeners)
			listener.headersUpdate(MainFrameRecord.record);
	}

	public static void parametersUpdated(Record record) {
		for (MainFrameRecordListener listener : MainFrameRecord.listeners)
			listener.parametersUpdate(MainFrameRecord.record);
	}
	
	public static void urlUpdated(Record record) {
		for (MainFrameRecordListener listener : MainFrameRecord.listeners)
			listener.urlUpdate(MainFrameRecord.record);
	}
	
	public static void responseUpdated(Record record) {
		for (MainFrameRecordListener listener : MainFrameRecord.listeners)
			listener.responseUpdate(MainFrameRecord.record);
	}
	
	/*
	 * RECORD
	 */

	public static Record getRecord() {
		return MainFrameRecord.record;
	}

	public static void setRecord(Record record) {
		if (record instanceof Bookmark) {
			MainFrameRecord.record = new Bookmark((Bookmark) record);
		} else {
			MainFrameRecord.record = new Record(record);
		}
		MainFrameRecord.recordUpdated(MainFrameRecord.record);
	}

	public static void newRecord() {
		MainFrameRecord.record = new Record();
		MainFrameRecord.recordUpdated(MainFrameRecord.record);
	}
	
	/*
	 * HEADER
	 */

	public static void addHeader(String value, String name) {
		List<Header> list = MainFrameRecord.record.getHeaders();
		list.add(new Header(value, name));
		MainFrameRecord.headersUpdated(MainFrameRecord.record);
	}

	public static void deleteHeader(Header item) {
		List<Header> list = MainFrameRecord.record.getHeaders();
		list.remove(item);
		MainFrameRecord.headersUpdated(MainFrameRecord.record);
	}
	
	/*
	 * PARAMETER
	 */

	public static void addParameter(String value, String name) {
		List<Parameter> list = MainFrameRecord.record.getParameters();
		list.add(new Parameter(value, name));

		URL url = MainFrameRecord.record.getUrl();
		url.setParameters(list);

		MainFrameRecord.urlUpdated(MainFrameRecord.record);
		MainFrameRecord.parametersUpdated(MainFrameRecord.record);
	}

	public static void deleteParameter(Parameter item) {
		List<Parameter> list = MainFrameRecord.record.getParameters();
		list.remove(item);

		URL url = MainFrameRecord.record.getUrl();
		url.setParameters(list);

		MainFrameRecord.urlUpdated(MainFrameRecord.record);
		MainFrameRecord.parametersUpdated(MainFrameRecord.record);
	}

	/*
	 * BODY
	 */

	public static void setBody(BodyType bodyType, String value) {
		MainFrameRecord.record.getBody().setType(bodyType);
		MainFrameRecord.record.getBody().setValue(value);
	}
	
	/*
	 * URL
	 */
	
	public static void editUrlNewParameter(String name, String value) {

		URL url = MainFrameRecord.record.getUrl();

		if (name != null) {
			url.setNewParameter(name);
		}
		if (value != null) {
			url.setNewValue(value);
		}

		MainFrameRecord.urlUpdated(MainFrameRecord.record);
	}

	public static void setUrl(String fullUrl) {
		URL url = new URL(fullUrl);
		MainFrameRecord.record.setUrl(url);
		
		List<Parameter> parameters = url.getParemeters();
		MainFrameRecord.record.setParameters(parameters);
		
		MainFrameRecord.urlUpdated(MainFrameRecord.record);
		MainFrameRecord.parametersUpdated(MainFrameRecord.record);
	}
	
	/*
	 * METHOD
	 */

	public static void setMethod(HTTPVerb method) {
		MainFrameRecord.record.setMethod(method);
	}
	
	/*
	 * RESPONSE
	 */

	public static void setResponse(Response response) {
		MainFrameRecord.record.setResponse(response);
		MainFrameRecord.responseUpdated(MainFrameRecord.record);
	}

}
