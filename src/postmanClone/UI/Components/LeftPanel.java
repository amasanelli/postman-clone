package postmanClone.UI.Components;

import javax.swing.BoxLayout;

import postmanClone.UI.Common.CustomPanel;
import postmanClone.UI.Components.Left.RecordsGridBookmark;
import postmanClone.UI.Components.Left.RecordsGridHistory;

@SuppressWarnings("serial")
public class LeftPanel extends CustomPanel {

	public LeftPanel() {
		super();
		this.build();
	}

	private void build() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.add(new RecordsGridBookmark());
		this.add(new RecordsGridHistory());

	}
}
