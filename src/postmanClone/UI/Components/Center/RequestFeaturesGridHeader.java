package postmanClone.UI.Components.Center;

import java.util.List;

import postmanClone.DA.Objects.Header;
import postmanClone.DA.Objects.Record;
import postmanClone.UI.Common.Tables.TableModelHeader;
import postmanClone.UI.Frames.MainFrameRecord;
import postmanClone.UI.Frames.MainFrameRecordAdapter;

@SuppressWarnings("serial")
public class RequestFeaturesGridHeader extends RequestFeaturesGrid<Header> {

	public RequestFeaturesGridHeader() {
		super(new TableModelHeader());
		MainFrameRecord.addListener(new MainFrameRecordAdapter() {
			@Override
			public void headersUpdate(Record record) {
				List<Header> list = record.getHeaders();
				getModel().setData(list);
			}
			
			@Override
			public void recordUpdate(Record record) {
				headersUpdate(record);
			}
		});
	}

	@Override
	public void addRow(String value, String name) {
		MainFrameRecord.addHeader(value, name);
	}

	@Override
	public void deleteRow() {
		int viewRow = this.getTable().getSelectedRow();
		if (viewRow >= 0) {
			int modelRow = this.getTable().convertRowIndexToModel(viewRow);
			Header item = this.getModel().getData().get(modelRow);
			MainFrameRecord.deleteHeader(item);
		}
	}

}
