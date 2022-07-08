package postmanClone.UI.Components.Left;

import postmanClone.BL.BusinessLogicLayerException;
import postmanClone.BL.StorageServices;
import postmanClone.DA.Objects.Bookmark;
import postmanClone.DA.Objects.History;
import postmanClone.DA.Objects.Record;
import postmanClone.UI.Common.BackgroundWorker;
import postmanClone.UI.Common.Tables.TableModel;
import postmanClone.UI.Common.Tables.TableModelHistory;
import postmanClone.UI.Frames.MainFrameRecordHandler;
import postmanClone.UI.Frames.MainFrameRecordAdapter;

@SuppressWarnings("serial")
public class RecordsGridHistory extends RecordsGrid<History> {

	public RecordsGridHistory() {
		super(new TableModelHistory());
		MainFrameRecordHandler.addListener(new MainFrameRecordAdapter() {
			@Override
			public void recordUpdate(Record record) {
				if (record instanceof Bookmark) {
					getTable().getSelectionModel().clearSelection();
				}
			}
		});
		this.loadData();
	}

	private void loadData() {

		TableModel<History> model = this.getModel();

		BackgroundWorker worker = new BackgroundWorker(this.getProgressBar()) {
			@Override
			public void toDo() throws BusinessLogicLayerException {
				model.setData(StorageServices.getHistory());
			}
		};

		worker.execute();
	}
	
	@Override
	public void historySaved() {
		this.loadData();
	}

}
