package postmanClone.UI.Frames;

import postmanClone.DA.Objects.Record;

public interface MainFrameRecordListener {

	public void recordUpdate(Record record);
	
	public void responseUpdate(Record record);
	
	public void methodUpdate(Record record);
	
	public void urlUpdate(Record record);
	
	public void bodyUpdate(Record record);
	
	public void parametersUpdate(Record record);
	
	public void headersUpdate(Record record);
	
}
