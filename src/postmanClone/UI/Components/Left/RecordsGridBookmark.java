package postmanClone.UI.Components.Left;

import postmanClone.BL.BusinessLogicLayerException;
import postmanClone.BL.StorageServices;
import postmanClone.DA.Objects.Bookmark;
import postmanClone.DA.Objects.Record;
import postmanClone.UI.Common.BackgroundWorker;
import postmanClone.UI.Common.Tables.TableModel;
import postmanClone.UI.Common.Tables.TableModelBookmark;
import postmanClone.UI.Frames.MainFrameRecordHandler;
import postmanClone.UI.Frames.MainFrameRecordAdapter;

@SuppressWarnings("serial")
public class RecordsGridBookmark extends RecordsGrid<Bookmark> {

	public RecordsGridBookmark() {
		super(new TableModelBookmark());
		MainFrameRecordHandler.addListener(new MainFrameRecordAdapter() {
			@Override
			public void recordUpdate(Record record) {
				if (record instanceof Bookmark) {
					return;
				}
				getTable().getSelectionModel().clearSelection();
			}
		});
		this.loadData();
	}

	private void loadData() {
		TableModel<Bookmark> model = this.getModel();

		BackgroundWorker worker = new BackgroundWorker(this.getProgressBar()) {
			@Override
			public void toDo() throws BusinessLogicLayerException {
				model.setData(StorageServices.getBookmarks());
			}
		};

		worker.execute();
	}

	@Override
	public void bookmarkSaved() {
		this.loadData();
	}

	@Override
	public void bookmarkDeleted() {
		this.loadData();
	}

}
