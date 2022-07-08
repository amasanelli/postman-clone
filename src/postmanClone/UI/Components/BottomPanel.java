package postmanClone.UI.Components;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JComponent;

import postmanClone.UI.Common.CustomPanel;
import postmanClone.UI.Common.TabButtons;
import postmanClone.UI.Components.Bottom.ResponseButtonTabOption;
import postmanClone.UI.Components.Bottom.ResponseImage;
import postmanClone.UI.Components.Bottom.ResponseTextPretty;
import postmanClone.UI.Components.Bottom.ResponseTextRaw;

@SuppressWarnings("serial")
public class BottomPanel extends CustomPanel  {
	
	private ResponseTextRaw responseTextRaw;
	private ResponseTextPretty responseTextPretty;
	private ResponseImage responseImage;
	private JComponent component;

	public BottomPanel() {
		this.build();
	}

	private void build() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.responseTextRaw = new ResponseTextRaw();
		this.responseTextPretty = new ResponseTextPretty();
		this.responseImage = new ResponseImage();
		this.component = this.responseTextRaw;
		
		List<String> names = new ArrayList<>();
		for (ResponseButtonTabOption option : ResponseButtonTabOption.values()) {
			names.add(option.toString());
		}

		this.add(new TabButtons(names) {
			@Override
			public void buttonClicked(String buttonName) {
				responseTabClicked(ResponseButtonTabOption.valueOf(buttonName));
			}
		});
		this.add(this.responseTextRaw);
	}
	
	public void responseTabClicked(ResponseButtonTabOption evt) {
		this.remove(this.component);
		switch (evt) {
		case RAW:
			this.component = this.responseTextRaw;
			break;
		case PRETTY:
			this.component = this.responseTextPretty;
			break;
		case PREVIEW:
			this.component = this.responseImage;
			break;
		}
		this.add(this.component);
		this.revalidate();
		this.repaint();
	}
}
