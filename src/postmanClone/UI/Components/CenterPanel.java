package postmanClone.UI.Components;

import javax.swing.BoxLayout;
import javax.swing.JComponent;

import postmanClone.UI.Common.CustomPanel;
import postmanClone.UI.Components.Center.RequestFeaturesButtonTab;
import postmanClone.UI.Components.Center.RequestFeaturesButtonTabOption;
import postmanClone.UI.Components.Center.RequestFeaturesBody;
import postmanClone.UI.Components.Center.RequestFeaturesGridHeader;
import postmanClone.UI.Components.Center.RequestFeaturesGridParameter;

@SuppressWarnings("serial")
public class CenterPanel extends CustomPanel {

	private RequestFeaturesGridParameter params;
	private RequestFeaturesGridHeader headers;
	private RequestFeaturesBody body;
	private JComponent component;

	public CenterPanel() {
		this.build();
	}

	private void build() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.params = new RequestFeaturesGridParameter();
		this.headers = new RequestFeaturesGridHeader();
		this.body = new RequestFeaturesBody();
		this.component = this.params;

		this.add(new RequestFeaturesButtonTab() {
			@Override
			public void buttonActionPerformed(RequestFeaturesButtonTabOption optionSelected) {
				requestFeaturesTabClicked(optionSelected);
			}

		});
		this.add(this.component);

	}

	public void requestFeaturesTabClicked(RequestFeaturesButtonTabOption evt) {
		this.remove(this.component);
		switch (evt) {
		case PARAMS:
			this.component = this.params;
			break;
		case HEADERS:
			this.component = this.headers;
			break;
		case BODY:
			this.component = this.body;
			break;
		}
		this.add(this.component);
		this.revalidate();
		this.repaint();
	}
}
