package postmanClone.UI.Components.Center;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import postmanClone.UI.Common.CustomPanel;
import postmanClone.UI.Common.TabButtons;

@SuppressWarnings("serial")
public abstract class RequestFeaturesButtonTab extends CustomPanel {

	public RequestFeaturesButtonTab() {
		super();
		this.build();
	}

	private void build() {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		List<String> names = new ArrayList<>();

		for (RequestFeaturesButtonTabOption option : RequestFeaturesButtonTabOption.values()) {
			names.add(option.toString());
		}

		this.add(new TabButtons(names) {
			@Override
			public void buttonClicked(String buttonName) {
				buttonActionPerformed(RequestFeaturesButtonTabOption.valueOf(buttonName));
			}
		});

	}

	public abstract void buttonActionPerformed(RequestFeaturesButtonTabOption optionSelected);

}
