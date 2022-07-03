package postmanClone.UI.Components.Center;

import java.util.List;

import postmanClone.DA.Objects.Parameter;
import postmanClone.DA.Objects.Record;
import postmanClone.UI.Common.Tables.TableModelParameter;
import postmanClone.UI.Frames.MainFrameRecord;
import postmanClone.UI.Frames.MainFrameRecordAdapter;

@SuppressWarnings("serial")
public class RequestFeaturesGridParameter extends RequestFeaturesGrid<Parameter> {

	public RequestFeaturesGridParameter() {
		super(new TableModelParameter());
		MainFrameRecord.addListener(new MainFrameRecordAdapter() {
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
		MainFrameRecord.addParameter(value, name);
	}

	
	@Override
	public void deleteRow() {
		int viewRow = this.getTable().getSelectedRow();
		if (viewRow >= 0) {
			int modelRow = this.getTable().convertRowIndexToModel(viewRow);
			Parameter item = this.getModel().getData().get(modelRow);
			MainFrameRecord.deleteParameter(item);
		}
	}
	
	@Override
	public void nameChanged(String value) {
		MainFrameRecord.editUrlNewParameter(value, null);
	}
	
	@Override
	public void valueChanged(String value) {
		MainFrameRecord.editUrlNewParameter(null, value);
	}
}


