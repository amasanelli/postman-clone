package postmanClone.UI.Components;

import javax.swing.BoxLayout;

import postmanClone.UI.Common.CustomPanel;
import postmanClone.UI.Components.Top.RequestMain;

@SuppressWarnings("serial")
public class TopPanel extends CustomPanel {

	public TopPanel() {
		super();
		this.build();
	}

	private void build() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.add(new RequestMain(null));
	}
}
