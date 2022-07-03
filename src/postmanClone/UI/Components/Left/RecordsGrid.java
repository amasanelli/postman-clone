package postmanClone.UI.Components.Left;

import javax.swing.BoxLayout;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import postmanClone.DA.Objects.Record;
import postmanClone.UI.Common.CustomPanel;
import postmanClone.UI.Common.Tables.TableModel;
import postmanClone.UI.Frames.MainFrameRecords;
import postmanClone.UI.Frames.MainFrameRecord;
import postmanClone.UI.Frames.MainFrameRecordsListener;

@SuppressWarnings("serial")
public abstract class RecordsGrid<T> extends CustomPanel
		implements ListSelectionListener, MainFrameRecordsListener {

	private JTable table;
	private TableModel<T> model;
	private JProgressBar progressBar;

	public RecordsGrid(TableModel<T> model) {
		this.model = model;
		this.build();
	}

	private void build() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.table = new JTable(this.model);
		this.table.getSelectionModel().addListSelectionListener(this);
		JScrollPane scrollPane = new JScrollPane(this.table);

		this.progressBar = new JProgressBar();
		this.progressBar.setIndeterminate(true);
		this.progressBar.setVisible(false);

		MainFrameRecords.addListener(this);

		this.add(progressBar);
		this.add(scrollPane);

	}

	@Override
	public void valueChanged(ListSelectionEvent evt) {
		int viewRow = this.table.getSelectedRow();

		if (viewRow >= 0) {
			int modelRow = this.table.convertRowIndexToModel(viewRow);
			MainFrameRecord.setRecord((Record) this.model.getData().get(modelRow));
		}

	}

	public JTable getTable() {
		return table;
	}

	public TableModel<T> getModel() {
		return model;
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}

	@Override
	public void bookmarkSaved() {

	}

	@Override
	public void bookmarkDeleted() {

	}

	@Override
	public void historySaved() {

	}

}
