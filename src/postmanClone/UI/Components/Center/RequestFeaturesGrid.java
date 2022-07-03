package postmanClone.UI.Components.Center;

import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import postmanClone.UI.Common.CustomPanel;
import postmanClone.UI.Common.Tables.TableModel;

@SuppressWarnings("serial")
public abstract class RequestFeaturesGrid<T> extends CustomPanel {

	private JTable table;
	private TableModel<T> model;

	public RequestFeaturesGrid(TableModel<T> model) {
		super();
		this.model = model;
		this.build();
	}

	private void build() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.table = new JTable(this.model);
		JScrollPane scrollPane = new JScrollPane(this.table);

		this.add(scrollPane);
		RequestFeaturesGridActions requestFeaturesGridActions = new RequestFeaturesGridActions() {
			@Override
			public void deleteButtonClicked() {
				deleteRow();
			}

			@Override
			public void addButtonClicked(String value, String name) {
				addRow(value, name);
			}

			@Override
			public void nameItemChanged(String value) {
				nameChanged(value);
			}

			@Override
			public void valueItemChanged(String value) {
				valueChanged(value);
			}
		};
		this.add(requestFeaturesGridActions);
	}

	public JTable getTable() {
		return this.table;
	}

	public TableModel<T> getModel() {
		return this.model;
	}

	public abstract void deleteRow();

	public abstract void addRow(String value, String name);

	public void nameChanged(String value) {

	}

	public void valueChanged(String value) {

	}

}
