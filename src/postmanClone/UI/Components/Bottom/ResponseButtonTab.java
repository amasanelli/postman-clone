package postmanClone.UI.Components.Bottom;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;

import postmanClone.UI.Common.CustomPanel;
import postmanClone.UI.Common.TabButtons;

@SuppressWarnings("serial")
public abstract class ResponseButtonTab extends CustomPanel {

	public ResponseButtonTab() {
		super();
		this.build();
	}

	private void build() {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		List<String> names = new ArrayList<>();

		for (ResponseButtonTabOption option : ResponseButtonTabOption.values()) {
			names.add(option.toString());
		}

		this.add(new TabButtons(names) {
			@Override
			public void buttonClicked(String buttonName) {
				buttonActionPerformed(ResponseButtonTabOption.valueOf(buttonName));
			}
		});

	}

	public abstract void buttonActionPerformed(ResponseButtonTabOption optionSelected);

}
