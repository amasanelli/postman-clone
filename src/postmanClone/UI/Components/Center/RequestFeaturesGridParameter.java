package postmanClone.UI.Components.Center;

import java.util.List;

import postmanClone.DA.Objects.Parameter;
import postmanClone.DA.Objects.Record;
import postmanClone.UI.Common.Tables.TableModelParameter;
import postmanClone.UI.Frames.MainFrameRecordHandler;
import postmanClone.UI.Frames.MainFrameRecordAdapter;

@SuppressWarnings("serial")
public class RequestFeaturesGridParameter extends RequestFeaturesGrid<Parameter> {

	public RequestFeaturesGridParameter() {
		super(new TableModelParameter());
		MainFrameRecordHandler.addListener(new MainFrameRecordAdapter() {
			@Override
			public void parametersUpdate(Record record) {
				List<Parameter> list = record.getParameters();
				getModel().setData(list);
			}
			
			@Override
			public void recordUpdate(Record record) {
				parametersUpdate(record);
			}
		});
	}

	@Override
	public void addRow(String value, String name) {
		MainFrameRecordHandler.addParameter(value, name);
	}

	
	@Override
	public void deleteRow() {
		int viewRow = this.getTable().getSelectedRow();
		if (viewRow >= 0) {
			int modelRow = this.getTable().convertRowIndexToModel(viewRow);
			Parameter item = this.getModel().getData().get(modelRow);
			MainFrameRecordHandler.deleteParameter(item);
		}
	}
	
	@Override
	public void nameChanged(String value) {
		MainFrameRecordHandler.editUrlNewParameter(value, null);
	}
	
	@Override
	public void valueChanged(String value) {
		MainFrameRecordHandler.editUrlNewParameter(null, value);
	}
}


