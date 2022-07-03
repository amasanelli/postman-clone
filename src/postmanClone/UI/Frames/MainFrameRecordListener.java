package postmanClone.UI.Frames;

import postmanClone.DA.Objects.Record;

public interface MainFrameRecordListener {

	void recordUpdate(Record record);
	
	void responseUpdate(Record record);
	
	void methodUpdate(Record record);
	
	void urlUpdate(Record record);
	
	void bodyUpdate(Record record);
	
	void parametersUpdate(Record record);
	
	void headersUpdate(Record record);
	
}
