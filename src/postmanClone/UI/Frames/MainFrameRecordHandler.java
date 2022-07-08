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

public class MainFrameRecordHandler {

	private static Record record = new Record();
	private static final List<MainFrameRecordListener> listeners = new ArrayList<>();

	public static void addListener(MainFrameRecordListener listener) {
		MainFrameRecordHandler.listeners.add(listener);
	}

	public static void recordUpdated(Record record) {
		for (MainFrameRecordListener listener : MainFrameRecordHandler.listeners)
			listener.recordUpdate(MainFrameRecordHandler.record);
	}
	
	public static void headersUpdated(Record record) {
		for (MainFrameRecordListener listener : MainFrameRecordHandler.listeners)
			listener.headersUpdate(MainFrameRecordHandler.record);
	}

	public static void parametersUpdated(Record record) {
		for (MainFrameRecordListener listener : MainFrameRecordHandler.listeners)
			listener.parametersUpdate(MainFrameRecordHandler.record);
	}
	
	public static void urlUpdated(Record record) {
		for (MainFrameRecordListener listener : MainFrameRecordHandler.listeners)
			listener.urlUpdate(MainFrameRecordHandler.record);
	}
	
	public static void responseUpdated(Record record) {
		for (MainFrameRecordListener listener : MainFrameRecordHandler.listeners)
			listener.responseUpdate(MainFrameRecordHandler.record);
	}
	
	/*
	 * RECORD
	 */

	public static Record getRecord() {
		return MainFrameRecordHandler.record;
	}

	public static void setRecord(Record record) {
		if (record instanceof Bookmark) {
			MainFrameRecordHandler.record = new Bookmark((Bookmark) record);
		} else {
			MainFrameRecordHandler.record = new Record(record);
		}
		MainFrameRecordHandler.recordUpdated(MainFrameRecordHandler.record);
	}

	public static void newRecord() {
		MainFrameRecordHandler.record = new Record();
		MainFrameRecordHandler.recordUpdated(MainFrameRecordHandler.record);
	}
	
	/*
	 * HEADER
	 */

	public static void addHeader(String value, String name) {
		List<Header> list = MainFrameRecordHandler.record.getHeaders();
		list.add(new Header(value, name));
		MainFrameRecordHandler.headersUpdated(MainFrameRecordHandler.record);
	}

	public static void deleteHeader(Header item) {
		List<Header> list = MainFrameRecordHandler.record.getHeaders();
		list.remove(item);
		MainFrameRecordHandler.headersUpdated(MainFrameRecordHandler.record);
	}
	
	/*
	 * PARAMETER
	 */

	public static void addParameter(String value, String name) {
		List<Parameter> list = MainFrameRecordHandler.record.getParameters();
		list.add(new Parameter(value, name));

		URL url = MainFrameRecordHandler.record.getUrl();
		url.setParameters(list);

		MainFrameRecordHandler.urlUpdated(MainFrameRecordHandler.record);
		MainFrameRecordHandler.parametersUpdated(MainFrameRecordHandler.record);
	}

	public static void deleteParameter(Parameter item) {
		List<Parameter> list = MainFrameRecordHandler.record.getParameters();
		list.remove(item);

		URL url = MainFrameRecordHandler.record.getUrl();
		url.setParameters(list);

		MainFrameRecordHandler.urlUpdated(MainFrameRecordHandler.record);
		MainFrameRecordHandler.parametersUpdated(MainFrameRecordHandler.record);
	}

	/*
	 * BODY
	 */

	public static void setBody(BodyType bodyType, String value) {
		MainFrameRecordHandler.record.getBody().setType(bodyType);
		MainFrameRecordHandler.record.getBody().setValue(value);
	}
	
	/*
	 * URL
	 */
	
	public static void editUrlNewParameter(String name, String value) {

		URL url = MainFrameRecordHandler.record.getUrl();

		if (name != null) {
			url.setNewParameter(name);
		}
		if (value != null) {
			url.setNewValue(value);
		}

		MainFrameRecordHandler.urlUpdated(MainFrameRecordHandler.record);
	}

	public static void setUrl(String fullUrl) {
		URL url = new URL(fullUrl);
		MainFrameRecordHandler.record.setUrl(url);
		
		List<Parameter> parameters = url.getParemeters();
		MainFrameRecordHandler.record.setParameters(parameters);
		
		MainFrameRecordHandler.urlUpdated(MainFrameRecordHandler.record);
		MainFrameRecordHandler.parametersUpdated(MainFrameRecordHandler.record);
	}
	
	/*
	 * METHOD
	 */

	public static void setMethod(HTTPVerb method) {
		MainFrameRecordHandler.record.setMethod(method);
	}
	
	/*
	 * RESPONSE
	 */

	public static void setResponse(Response response) {
		MainFrameRecordHandler.record.setResponse(response);
		MainFrameRecordHandler.responseUpdated(MainFrameRecordHandler.record);
	}

}
